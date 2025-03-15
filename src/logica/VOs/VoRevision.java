package logica.VOs;

import java.io.Serializable;

public class VoRevision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int numero;
	String descripcion;
	String codFolio;

	public VoRevision(int numero, String descripcion, String codFolio) {
		super();
		this.numero = numero;
		this.descripcion = descripcion;
		this.codFolio = codFolio;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCodFolio() {
		return codFolio;
	}

}
