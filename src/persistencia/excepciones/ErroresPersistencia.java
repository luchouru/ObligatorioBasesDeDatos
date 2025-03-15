package persistencia.excepciones;

public enum ErroresPersistencia {
	ERROR_CONEXION("Error al establecer la conexion con el servidor");

	private String msg;

	ErroresPersistencia(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
