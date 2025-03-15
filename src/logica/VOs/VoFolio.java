package logica.VOs;

import java.io.Serializable;

public class VoFolio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codigo;
	String caratula;
	int paginas;

	public VoFolio(String codigo, String caratula, int paginas) {
		super();
		this.codigo = codigo;
		this.caratula = caratula;
		this.paginas = paginas;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getCaratula() {
		return caratula;
	}

	public int getPaginas() {
		return paginas;
	}

}
