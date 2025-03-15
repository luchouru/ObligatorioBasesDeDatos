package persistencia.DAOs;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logica.Folio;
import logica.VOs.VoFolio;
import logica.VOs.VoFolioMaxRev;
import persistencia.ConexionBD.ConexionArchivo;
import persistencia.ConexionBD.IConexion;
import persistencia.excepciones.ErrorPersistencia;
import persistencia.excepciones.ErroresPersistencia;

public class DAOFoliosArchivo implements IDAOFolios, Serializable {
	private static final long serialVersionUID = -37018615966885528L;

	public DAOFoliosArchivo() {
	}

	@Override
	public boolean member(IConexion con, String nroFolio) throws ErrorPersistencia {
		String fileName = ((ConexionArchivo) con).getRutaRespaldos() + "folio-" + nroFolio + ".txt";
		File file = new File(fileName);
		return file.exists();
	}

	@Override
	public void insert(IConexion con, Folio folio) throws ErrorPersistencia {
		String fileName = "folio-" + folio.getCodigo() + ".txt";

		try {
			((ConexionArchivo) con).guardar(fileName, folio);
		} catch (ErrorPersistencia e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}
	}

	@Override
	public Folio find(IConexion con, String nroFolio) throws ErrorPersistencia {
		String fileName = "folio-" + nroFolio + ".txt";
		Folio folio = null;

		try {
			folio = (Folio) ((ConexionArchivo) con).leer(fileName);
		} catch (ErrorPersistencia e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return folio;
	}

	@Override
	public void delete(IConexion con, String nroFolio) throws ErrorPersistencia {
		String fileName = ((ConexionArchivo) con).getRutaRespaldos() + "folio-" + nroFolio + ".txt";
		try {
			new File(fileName).delete();
		} catch (Exception e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}
	}

	@Override
	public List<VoFolio> listarFolios(IConexion con) throws ErrorPersistencia {
		List<VoFolio> folios = new ArrayList<>();
		File folder = new File(((ConexionArchivo) con).getRutaRespaldos());

		try {
			for (File file : folder.listFiles((dir, name) -> name.startsWith("folio-") && name.endsWith(".txt"))) {
				Folio folio = (Folio) ((ConexionArchivo) con).leer(file.getName());
				folios.add(new VoFolio(folio.getCodigo(), folio.getCaratula(), folio.getPaginas()));
			}
		} catch (ErrorPersistencia e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return folios;
	}

	@Override
	public boolean esVacio(IConexion con) throws ErrorPersistencia {
		File folder = new File(((ConexionArchivo) con).getRutaRespaldos());
		return folder.listFiles().length == 0;
	}

	@Override
	public VoFolioMaxRev folioMasRevisado(IConexion con) throws ErrorPersistencia {
		VoFolioMaxRev folioMax = null;
		int mayorCant = -1;

		try {
			for (VoFolio voFolio : listarFolios(con)) {
				Folio folio = (Folio) ((ConexionArchivo) con).leer("folio-" + voFolio.getCodigo() + ".txt");
				int revs = folio.cantidadRevisiones(con);

				if (revs > mayorCant) {
					mayorCant = revs;
					folioMax = new VoFolioMaxRev(folio.getCodigo(), folio.getCaratula(), folio.getPaginas(), revs);
				}
			}
		} catch (ErrorPersistencia e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return folioMax;
	}

}
