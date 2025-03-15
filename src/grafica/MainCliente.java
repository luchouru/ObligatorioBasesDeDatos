package grafica;

import grafica.ventanas.PrincipalVentanaFrame;
import logica.conexion.ArchivoConfiguracion;
import logica.conexion.Connector;
import logica.excepciones.ErrorLogica;

public class MainCliente {

	public static void main(String[] args) throws ErrorLogica {
		
		String ip = ArchivoConfiguracion.getInstancia().getIpServidor();
		int port = ArchivoConfiguracion.getInstancia().getPuertoServidor();

		String ruta = "//" + ip + ":" + port + "/server";
		
		Connector.setConnectorRuta(ruta);
		
		PrincipalVentanaFrame ventana = new PrincipalVentanaFrame();
		ventana.setVisible(true);

	}

}
