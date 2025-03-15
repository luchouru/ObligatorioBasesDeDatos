package persistencia.fabrica;

import persistencia.ConexionBD.IPoolConexiones;
import persistencia.ConexionBD.PoolConexionesArchivo;
import persistencia.DAOs.DAOFoliosArchivo;
import persistencia.DAOs.DAORevisionesArchivo;
import persistencia.DAOs.IDAOFolios;
import persistencia.DAOs.IDAORevisiones;
import persistencia.excepciones.ErrorPersistencia;

public class FabricaArchivo implements FabricaAbstracta {

	@Override
	public IDAOFolios crearFolios() {
		return new DAOFoliosArchivo();
	}

	@Override
	public IDAORevisiones crearRevisiones(String codigo) {
		return new DAORevisionesArchivo(codigo);
	}

	@Override
	public IPoolConexiones crearPoolConexiones() throws ErrorPersistencia {
		return new PoolConexionesArchivo();
	}

}
