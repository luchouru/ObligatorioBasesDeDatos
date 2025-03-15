package persistencia.consultas;

public class Consultas {

	public String listarFolios() {
		return "Select * from folios";
	}

	public String existeFolio() {
		return "Select * from folios where codigo = ?";
	}

	public String eliminaFolio() {
		return "Delete from folios where codigo = ?";
	}

	public String eliminaRevisionesDeFolio() {
		return "Delete from revisiones where codFolio = ?";
	}

	public String listarRevisiones() {
		return "Select * from revisiones where codFolio = ?";
	}

	public String cantRevisionesDeFolio() {
		return "Select count(*) as cantidad from revisiones where codFolio = ?";
	}

	public String insertarFolio() {
		return "insert into folios (codigo, caratula, paginas) values (?, ?, ?)";
	}

	public String insertarRevision() {
		return "insert into revisiones (numero, codFolio, descripcion) values (?, ?, ?)";
	}

}
