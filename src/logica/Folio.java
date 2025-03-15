package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import logica.VOs.VoRevision;
import persistencia.ConexionBD.IConexion;
import persistencia.DAOs.IDAORevisiones;
import persistencia.excepciones.ErrorPersistencia;
import persistencia.excepciones.ErroresPersistencia;
import persistencia.fabrica.FabricaAbstracta;

public class Folio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String caratula;
	private int paginas;
	private IDAORevisiones revisiones;

	public Folio(String codigo, String caratula, int paginas) throws ErrorPersistencia {
		super();
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;

		try {
			String nomArch = "config.properties";
			Properties p = new Properties();
			p.load(new FileInputStream(nomArch));
			String nomFabrica = p.getProperty("fabrica");
			FabricaAbstracta fabrica = (FabricaAbstracta) Class.forName(nomFabrica).getDeclaredConstructor()
					.newInstance();
			this.revisiones = fabrica.crearRevisiones(codigo);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException | IOException e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

	}

	public String getCodigo() {
		return codigo;
	}

	public String getCaratula() {
		return caratula;
	}

	public int getPaginas() {
		return paginas;
	}

	public boolean tieneRevision(IConexion con, int numRev) throws ErrorPersistencia {
		return this.revisiones.kesimo(con, numRev) != null;
	}

	public int cantidadRevisiones(IConexion con) throws ErrorPersistencia {
		return this.revisiones.largo(con);
	}

	public void addRevision(IConexion con, Revision rev) throws ErrorPersistencia {
		this.revisiones.insback(con, rev);
	}

	public Revision obtenerRevision(IConexion con, int numRev) throws ErrorPersistencia {
		return this.revisiones.kesimo(con, numRev);
	}

	public List<VoRevision> listarRevisiones(IConexion con) throws ErrorPersistencia {
		return this.revisiones.listarRevisiones(con);
	}

	public void borrarRevisiones(IConexion con) throws ErrorPersistencia {
		this.revisiones.borrarRevisiones(con);
	}

}
