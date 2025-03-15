package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.panels.JPanelBorrarFolioRevisiones;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class BorrarFolioControlador extends ControladorPantalla {

	public BorrarFolioControlador(JPanelBorrarFolioRevisiones borrarfolioVentana) {
		super(borrarfolioVentana);
	}

	public void eliminaFolio(String codigo) {
		if (codigo.isBlank()) {
			((JPanelBorrarFolioRevisiones) this.getVentana()).mostrarMensajeError("Uno o mas campos estan en blanco.");
		} else {
			try {
				this.getModel().borrarFolioRevisiones(codigo);
				((JPanelBorrarFolioRevisiones) this.getVentana()).mostrarMensajeExito("Folio eliminado con exito!");
			} catch (ErrorLogica e) {
				((JPanelBorrarFolioRevisiones) this.getVentana()).mostrarMensajeError(e.getMessage());
			} catch (RemoteException | ErrorPersistencia e) {
				((JPanelBorrarFolioRevisiones) this.getVentana())
						.mostrarMensajeError("Ocurrio un error inesperado, intente nuevamente en unos minutos.");
				e.printStackTrace();
			}
		}

	}
}
