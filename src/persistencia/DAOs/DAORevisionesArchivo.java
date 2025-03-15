package persistencia.DAOs;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import logica.Revision;
import logica.VOs.VoRevision;
import persistencia.ConexionBD.ConexionArchivo;
import persistencia.ConexionBD.IConexion;
import persistencia.excepciones.ErrorPersistencia;
import persistencia.excepciones.ErroresPersistencia;

public class DAORevisionesArchivo implements IDAORevisiones, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193790394196347303L;
	private String codigoFolio;
	private String fileName;

	public DAORevisionesArchivo(String codigoFolio) {
		super();
		this.codigoFolio = codigoFolio;
		this.fileName = "revisiones-" + this.codigoFolio + ".txt";
	}

	@Override
	public void insback(IConexion con, Revision rev) throws ErrorPersistencia {

		try {
			List<Revision> revisiones = new ArrayList<>();
			File file = new File(((ConexionArchivo) con).getRutaRespaldos() + fileName);
			if (!file.exists()) {
				file.createNewFile();
			} else {
				revisiones = (List<Revision>) ((ConexionArchivo) con).leer(fileName);
			}
			revisiones.add(rev);
			((ConexionArchivo) con).guardar(fileName, revisiones);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}
	}

	@Override
	public int largo(IConexion con) throws ErrorPersistencia {
		int count = 0;

		try {
			List<Revision> revisiones = new ArrayList<>();
			File file = new File(((ConexionArchivo) con).getRutaRespaldos() + fileName);
			if (!file.exists()) {
				return count;
			} else {
				revisiones = (List<Revision>) ((ConexionArchivo) con).leer(fileName);
			}

			count = revisiones.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return count;
	}

	@Override
	public Revision kesimo(IConexion con, int numRev) throws ErrorPersistencia {

		try {
			List<Revision> revisiones = new ArrayList<>();
			File file = new File(((ConexionArchivo) con).getRutaRespaldos() + fileName);
			if (!file.exists()) {
				return null;
			}

			revisiones = (List<Revision>) ((ConexionArchivo) con).leer(fileName);
			return revisiones.get(numRev - 1);

		} catch (Exception e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

	}

	@Override
	public List<VoRevision> listarRevisiones(IConexion con) throws ErrorPersistencia {
		List<VoRevision> revisiones = new ArrayList<>();

		try {
			List<Revision> revisionesFolio = new ArrayList<>();
			File file = new File(((ConexionArchivo) con).getRutaRespaldos() + fileName);

			if (!file.exists()) {
				return revisiones;
			}
			revisionesFolio = (List<Revision>) ((ConexionArchivo) con).leer(fileName);
			for (Revision rev : revisionesFolio) {
				revisiones.add(new VoRevision(rev.getNumero(), rev.getDescripcion(), codigoFolio));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return revisiones;
	}

	@Override
	public void borrarRevisiones(IConexion con) throws ErrorPersistencia {
		try {
			File file = new File(((ConexionArchivo) con).getRutaRespaldos() + fileName);
			file.delete();

		} catch (Exception e) {
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}
	}

}
