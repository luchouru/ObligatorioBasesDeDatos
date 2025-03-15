package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import logica.VOs.VoFolio;
import logica.VOs.VoFolioMaxRev;
import logica.VOs.VoRevision;
import logica.excepciones.ErrorLogica;
import logica.excepciones.ErroresLogica;
import persistencia.ConexionBD.IConexion;
import persistencia.ConexionBD.IPoolConexiones;
import persistencia.DAOs.IDAOFolios;
import persistencia.excepciones.ErrorPersistencia;
import persistencia.fabrica.FabricaAbstracta;

public class Fachada extends UnicastRemoteObject implements IFachada {
	private static Fachada instancia = null;
	private IPoolConexiones conexiones;
	private IDAOFolios diccioFolios;

	private Fachada() throws RemoteException, ErrorPersistencia {
		try {
			String nomArch = "config.properties";
			Properties p = new Properties();
			p.load(new FileInputStream(nomArch));
			String nomFabrica = p.getProperty("fabrica");
			FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).getDeclaredConstructor()
					.newInstance();
			diccioFolios = fabrica.crearFolios();
			conexiones = fabrica.crearPoolConexiones();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Fachada getInstancia() throws RemoteException, ErrorPersistencia {
		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}

	public void agregarFolio(VoFolio voF) throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			if (diccioFolios.member(con, voF.getCodigo())) {
				this.conexiones.liberarConnection(con, true);
				throw new ErrorLogica(ErroresLogica.FOLIO_YA_EXISTE.getMsg());
			} else {
				diccioFolios.insert(con, new Folio(voF.getCodigo(), voF.getCaratula(), voF.getPaginas()));
				this.conexiones.liberarConnection(con, true);
			}
		} catch (ErrorPersistencia | ErrorLogica e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

	public void agregarRevision(String codFolio, String desc) throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			if (!diccioFolios.member(con, codFolio)) {
				this.conexiones.liberarConnection(con, true);
				throw new ErrorLogica(ErroresLogica.FOLIO_NO_EXISTE.getMsg());
			} else {
				Folio folio = this.diccioFolios.find(con, codFolio);
				int cant = folio.cantidadRevisiones(con) + 1;
				folio.addRevision(con, new Revision(cant, desc));
				this.conexiones.liberarConnection(con, true);
			}
		} catch (ErrorPersistencia | ErrorLogica e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

	public void borrarFolioRevisiones(String codFolio) throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			if (!diccioFolios.member(con, codFolio)) {
				this.conexiones.liberarConnection(con, true);
				throw new ErrorLogica(ErroresLogica.FOLIO_NO_EXISTE.getMsg());
			} else {
				Folio folio = this.diccioFolios.find(con, codFolio);
				folio.borrarRevisiones(con);
				diccioFolios.delete(con, codFolio);
				this.conexiones.liberarConnection(con, true);
			}
		} catch (ErrorPersistencia | ErrorLogica e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

	public String darDescripcion(String codFolio, int numRevision)
			throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			if (!diccioFolios.member(con, codFolio)) {
				this.conexiones.liberarConnection(con, true);
				throw new ErrorLogica(ErroresLogica.FOLIO_NO_EXISTE.getMsg());
			} else {
				Folio folio = this.diccioFolios.find(con, codFolio);
				List<VoRevision> revisiones = folio.listarRevisiones(con);
				this.conexiones.liberarConnection(con, true);
				VoRevision revision = revisiones.stream().filter(rev -> rev.getNumero() == numRevision).findFirst()
						.orElse(null);
				if (revision == null) {
					throw new ErrorLogica(ErroresLogica.FOLIO_NO_TIENE_REVISION.getMsg());
				}
				return revision.getDescripcion();
			}
		} catch (ErrorPersistencia | ErrorLogica e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

	public List<VoFolio> listarFolios() throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			List<VoFolio> folios = this.diccioFolios.listarFolios(con);
			this.conexiones.liberarConnection(con, true);
			return folios.stream().sorted(Comparator.comparing(VoFolio::getCodigo)).collect(Collectors.toList());
		} catch (ErrorPersistencia e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

	public List<VoRevision> listarRevisiones(String codFolio) throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			if (!diccioFolios.member(con, codFolio)) {
				this.conexiones.liberarConnection(con, true);
				throw new ErrorLogica(ErroresLogica.FOLIO_NO_EXISTE.getMsg());
			} else {
				Folio folio = this.diccioFolios.find(con, codFolio);
				List<VoRevision> revisiones = folio.listarRevisiones(con);
				this.conexiones.liberarConnection(con, true);
				revisiones.sort((rev1, rev2) -> Integer.compare(rev1.getNumero(), rev2.getNumero()));
				return revisiones;
			}
		} catch (ErrorPersistencia | ErrorLogica e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

	public VoFolioMaxRev folioMasRevisado() throws RemoteException, ErrorLogica, ErrorPersistencia {
		IConexion con = this.conexiones.getConnection(true);

		try {
			List<VoFolio> folios = this.diccioFolios.listarFolios(con);
			if (folios.isEmpty()) {
				this.conexiones.liberarConnection(con, true);
				throw new ErrorLogica(ErroresLogica.NO_HAY_FOLIOS.getMsg());
			} else {
				VoFolioMaxRev resu = this.diccioFolios.folioMasRevisado(con);
				this.conexiones.liberarConnection(con, true);
				return resu;
			}
		} catch (ErrorPersistencia | ErrorLogica e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			throw e;
		} catch (Exception e) {
			if (con != null) {
				this.conexiones.liberarConnection(con, false);
			}
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}

}
