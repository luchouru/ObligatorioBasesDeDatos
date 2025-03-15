package persistencia.ConexionBD;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import persistencia.excepciones.ErrorPersistencia;

public class ConexionArchivo implements IConexion {

	private boolean esEscritura;
	private String rutaRespaldos = "";

	public ConexionArchivo(boolean esEscritura) {
		super();
		this.esEscritura = esEscritura;

		String nomArch = "config.properties";
		Properties p = new Properties();

		try {
			p.load(new FileInputStream(nomArch));
			this.rutaRespaldos = p.getProperty("directorioPersistencia");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean esEscritura() {
		return esEscritura;
	}

	public String getRutaRespaldos() {
		return this.rutaRespaldos;
	}

	public void guardar(String nomArch, Object datos) throws ErrorPersistencia {
		try {
			FileOutputStream f = new FileOutputStream(this.rutaRespaldos + nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);

			try {
				o.writeObject(datos);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ErrorPersistencia("Error inesperado al conectarse con el server");
			} finally {
				o.close();
				f.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new ErrorPersistencia("Error inesperado al conectarse con el server");
		}
	}

	public Object leer(String nomArch) throws ErrorPersistencia {
		try {
			FileInputStream f = new FileInputStream(this.rutaRespaldos + nomArch);
			ObjectInputStream o = new ObjectInputStream(f);
			Object datos = o.readObject();
			o.close();
			f.close();
			return datos;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ErrorPersistencia("Error inesperado al conectarse con el server");
		}

	}

}
