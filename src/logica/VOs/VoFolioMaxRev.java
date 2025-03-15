package logica.VOs;

public class VoFolioMaxRev extends VoFolio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int cantRev;

	public VoFolioMaxRev(String codigo, String caratula, int paginas, int cantRev) {
		super(codigo, caratula, paginas);
		this.cantRev = cantRev;
	}

	public int getCantRev() {
		return cantRev;
	}

}
