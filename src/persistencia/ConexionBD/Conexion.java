package persistencia.ConexionBD;

import java.sql.Connection;

public class Conexion implements IConexion {

	private Connection con;

	public Conexion(Connection connection) {
		super();
		this.con = connection;
	}

	public Connection getConnection() {
		return con;
	}

}
