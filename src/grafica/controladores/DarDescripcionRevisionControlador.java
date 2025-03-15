package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.panels.JPanelDarDescripcion;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class DarDescripcionRevisionControlador extends ControladorPantalla {

	public DarDescripcionRevisionControlador(JPanelDarDescripcion jPanelDarDescripcion) {
		super(jPanelDarDescripcion);
	}

	public void darDescripcion(String codigoFolio, String numeroRevisionString) {
		String descripcion = "";
		if (codigoFolio.isBlank() | numeroRevisionString.isBlank()) {
			((JPanelDarDescripcion) this.getVentana()).mostrarMensajeError("Uno o mas campos estan en blanco.");
		} else {
			int numeroRevision = Integer.parseInt(numeroRevisionString);
			try {
				descripcion = this.getModel().darDescripcion(codigoFolio, numeroRevision);
				((JPanelDarDescripcion) this.getVentana()).cargarDescripcion(descripcion);
				((JPanelDarDescripcion) this.getVentana()).mostrarMensajeExito("Descripcion obtenida correctamente.");
			} catch (ErrorLogica e1) {
				((JPanelDarDescripcion) this.getVentana()).mostrarMensajeError(e1.getMessage());
			} catch (RemoteException | ErrorPersistencia e2) {
				((JPanelDarDescripcion) this.getVentana())
						.mostrarMensajeError("Ocurrio un error inesperado, intente nuevamente en unos minutos.");
				e2.printStackTrace();
			}
		}
	}
}
