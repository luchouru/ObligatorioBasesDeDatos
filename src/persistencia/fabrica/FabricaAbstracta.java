package persistencia.fabrica;

import persistencia.ConexionBD.IPoolConexiones;
import persistencia.DAOs.IDAOFolios;
import persistencia.DAOs.IDAORevisiones;
import persistencia.excepciones.ErrorPersistencia;

public interface FabricaAbstracta {
	public IDAOFolios crearFolios();

	public IDAORevisiones crearRevisiones(String codigo);

	public IPoolConexiones crearPoolConexiones() throws ErrorPersistencia;
}
