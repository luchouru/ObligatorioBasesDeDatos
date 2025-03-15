package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.panels.JPanelAgregarRevision;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class AgregarRevisionControlador extends ControladorPantalla {

	public AgregarRevisionControlador(JPanelAgregarRevision jPanelBase) {
		super(jPanelBase);
	}

	public void agregarRevision(String codigo, String desc) {
		try {
			if (codigo.isBlank() | desc.isBlank()) {
				((JPanelAgregarRevision) this.getVentana()).mostrarMensajeError("Uno o mas campos estan en blanco.");
			} else {
				this.getModel().agregarRevision(codigo, desc);
				((JPanelAgregarRevision) this.getVentana()).mostrarMensajeExito("Revision agregado correctamente");
			}
		} catch (ErrorLogica e1) {
			((JPanelAgregarRevision) this.getVentana()).mostrarMensajeError(e1.getMessage());
		} catch (ErrorPersistencia | RemoteException e2) {
			((JPanelAgregarRevision) this.getVentana())
					.mostrarMensajeError("Ocurrio un error inesperado, intente nuevamente en unos minutos.");
			e2.printStackTrace();
		}
	}
}
