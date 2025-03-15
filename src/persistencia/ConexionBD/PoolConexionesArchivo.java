package persistencia.ConexionBD;

import persistencia.excepciones.ErrorPersistencia;

public class PoolConexionesArchivo implements IPoolConexiones {

	private int lectoresActivos = 0;
	private boolean escritorActivo = false;

	public PoolConexionesArchivo() throws ErrorPersistencia {

	}

	@Override
	public synchronized IConexion getConnection(boolean escritura) throws ErrorPersistencia {
		try {
			if (escritura) {
				while (lectoresActivos > 0 || escritorActivo) {
					wait();
				}
				escritorActivo = true;
			} else {
				while (escritorActivo) {
					wait();
				}
				lectoresActivos++;
			}

			ConexionArchivo nuevaCon = new ConexionArchivo(escritura);
			return nuevaCon;

		} catch (InterruptedException e) {
			throw new ErrorPersistencia("Error al obtener la conexión");
		}
	}

	@Override
	public synchronized void liberarConnection(IConexion con, boolean estado) throws ErrorPersistencia {

		if (((ConexionArchivo) con).esEscritura()) {
			escritorActivo = false;
		} else {
			lectoresActivos--;
		}

		notify();

	}

}
