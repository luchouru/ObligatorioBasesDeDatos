package logica;

import java.io.Serializable;

public class Revision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private String descripcion;

	public Revision(int numero, String descripcion) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
