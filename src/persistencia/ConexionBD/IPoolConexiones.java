package persistencia.ConexionBD;

import persistencia.excepciones.ErrorPersistencia;

public interface IPoolConexiones {
	public IConexion getConnection(boolean escritura) throws ErrorPersistencia;

	public void liberarConnection(IConexion con, boolean estado) throws ErrorPersistencia;
}
