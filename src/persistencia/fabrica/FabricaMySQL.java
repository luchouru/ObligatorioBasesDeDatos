package persistencia.fabrica;

import persistencia.ConexionBD.IPoolConexiones;
import persistencia.ConexionBD.PoolConexiones;
import persistencia.DAOs.DAOFolios;
import persistencia.DAOs.DAORevisiones;
import persistencia.DAOs.IDAOFolios;
import persistencia.DAOs.IDAORevisiones;
import persistencia.excepciones.ErrorPersistencia;

public class FabricaMySQL implements FabricaAbstracta {

	@Override
	public IDAOFolios crearFolios() {
		return new DAOFolios();
	}

	@Override
	public IDAORevisiones crearRevisiones(String codigo) {
		return new DAORevisiones(codigo);
	}

	@Override
	public IPoolConexiones crearPoolConexiones() throws ErrorPersistencia {
		return new PoolConexiones();
	}
}
