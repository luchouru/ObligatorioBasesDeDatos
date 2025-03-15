package logica.conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import logica.excepciones.ErrorLogica;
import logica.excepciones.ErroresLogica;

public class ArchivoConfiguracion {
	private static ArchivoConfiguracion instancia;
	private String ipServidor;
	private int puertoServidor;
	
	public static ArchivoConfiguracion getInstancia() throws ErrorLogica{
		if(instancia == null) {
			instancia = new ArchivoConfiguracion();
		}
		return instancia;
	}
	
	private ArchivoConfiguracion() throws ErrorLogica{
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("config.properties"));
			puertoServidor = Integer.parseInt(p.getProperty("portServer"));
			ipServidor = p.getProperty("ipServer");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorLogica(ErroresLogica.ERROR_500.getMsg());
		}
	}
	
	public String getIpServidor() {
		return ipServidor;
	}
	
	public int getPuertoServidor() {
		return puertoServidor;
	}
}
