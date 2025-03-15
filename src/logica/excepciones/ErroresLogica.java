package logica.excepciones;

public enum ErroresLogica {
	FOLIO_YA_EXISTE("El folio ya existe en el sistema"),
	FOLIO_NO_EXISTE("El folio no se encuentra registrado en el sistema"),
	NO_HAY_FOLIOS("No hay folios registrados en el sistema"), FOLIO_NO_TIENE_REVISION("El folio no tiene esa revision"),
	ERROR_500("Houston tenemos un problema, intentelo de nuevo"), ERROR_CREAR_FOLIO("Error al crear el folio"),
	ERROR_CONEXION("Ocurrio un error al conectarse con el servidor.");

	private String msg;

	ErroresLogica(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
