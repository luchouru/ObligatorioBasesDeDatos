package persistencia.ConexionBD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import persistencia.excepciones.ErrorPersistencia;

public class PoolConexiones implements IPoolConexiones {

	private String driver;
	private String url;
	private String user;
	private String pwd;
	private int nivelTransaccionalidad;
	private int tamanio = 3;
	private int creadas = 0;
	private int tope = 0;
	private ArrayList<IConexion> conexiones;

	public PoolConexiones() throws ErrorPersistencia {
		super();
		String nomArch = "config.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(nomArch));
			this.url = p.getProperty("bdUrl");
			this.user = p.getProperty("bdUser");
			this.pwd = p.getProperty("bdPwd");
			this.driver = p.getProperty("driver");
			this.tamanio = Integer.parseInt(p.getProperty("cantConexiones"));
			this.nivelTransaccionalidad = Integer.parseInt(p.getProperty("transaccionalidad"));
			this.conexiones = new ArrayList<IConexion>(tamanio);

			Class.forName(driver);
		} catch (IOException | ClassNotFoundException e) {
			throw new ErrorPersistencia("Error al iniciar gestor de conexiones");
		}

	}

	@Override
	public synchronized IConexion getConnection(boolean escritura) throws ErrorPersistencia {
		IConexion con = null;
		while (con == null) {
			if (!this.conexiones.isEmpty()) {
				tope--;
				con = this.conexiones.get(tope);
				this.conexiones.remove(tope);
			} else {
				if (creadas < tamanio) {
					try {
						Conexion newCon = new Conexion(DriverManager.getConnection(url, user, pwd));
						newCon.getConnection().setAutoCommit(false);
						newCon.getConnection().setTransactionIsolation(nivelTransaccionalidad);
						con = newCon;
						creadas++;
					} catch (SQLException e) {
						e.printStackTrace();
						throw new ErrorPersistencia("Error inesperado al conectarse con el server");
					}
				} else {
					try {
						wait();
					} catch (InterruptedException e) {
						throw new ErrorPersistencia("Error inesperado al conectarse con el server");
					}
				}
			}
		}

		return con;
	}

	@Override
	public synchronized void liberarConnection(IConexion con, boolean estado) throws ErrorPersistencia {
		if (tope < tamanio) {
			try {
				if (estado) {
					((Conexion) con).getConnection().commit();
				} else {
					((Conexion) con).getConnection().rollback();
				}
				this.conexiones.add(con);
				tope++;
				notify();
			} catch (SQLException e) {
				throw new ErrorPersistencia("Error inesperado al conectarse con el server");
			}
		}
	}
}
