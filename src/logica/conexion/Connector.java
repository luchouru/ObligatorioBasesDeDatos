package logica.conexion;

import java.rmi.Naming;
import java.rmi.Remote;

import logica.excepciones.ErrorLogica;
import logica.excepciones.ErroresLogica;

public class Connector {
	private static String ruta = "";
	
	public static Remote createConnection() throws ErrorLogica{
		
		try {
			if(ruta.isEmpty()) {
				throw new ErrorLogica(ErroresLogica.ERROR_CONEXION.getMsg());
			}
			return Naming.lookup(ruta);
		}catch(Exception e) {
			throw new ErrorLogica(ErroresLogica.ERROR_CONEXION.getMsg());
		}
	}
	
	public static void setConnectorRuta(String route) {
		if (ruta.isEmpty()) {
			ruta = route;
		}
	}
	
}
