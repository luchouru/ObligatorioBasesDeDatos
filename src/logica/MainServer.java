package logica;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import logica.conexion.ArchivoConfiguracion;

public class MainServer {

	public static void main(String[] args) {

		try {
			String ip = ArchivoConfiguracion.getInstancia().getIpServidor();
			int port = ArchivoConfiguracion.getInstancia().getPuertoServidor();

			LocateRegistry.createRegistry(port);

			String ruta = "//" + ip + ":" + port + "/server";

			Fachada fachada = Fachada.getInstancia();

			System.out.println("Antes de publicar");
			Naming.rebind(ruta, fachada);
			System.out.println("Luego de publicar ");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
