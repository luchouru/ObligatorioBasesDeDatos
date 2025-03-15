package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.panels.JPanelFolioMasRevisado;
import logica.VOs.VoFolioMaxRev;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class FolioMasRevisadoControlador extends ControladorPantalla {

	public FolioMasRevisadoControlador(JPanelFolioMasRevisado folioMasRevisado) {

		super(folioMasRevisado);
	}

	public void folioMasRevisado() {
		VoFolioMaxRev folioMasRev = null;
		try {
			folioMasRev = this.getModel().folioMasRevisado();
			((JPanelFolioMasRevisado) this.getVentana()).cargarFolioMaxRev(folioMasRev);
		} catch (ErrorLogica e) {
			// e.printStackTrace();
			((JPanelFolioMasRevisado) this.getVentana()).mostrarMensajeError(e.getMessage());
		} catch (RemoteException | ErrorPersistencia e) {
			e.printStackTrace();
			((JPanelFolioMasRevisado) this.getVentana())
					.mostrarMensajeError("Ocurrio un error inesperado, intente nuevamente en unos minutos.");
		}
	}

}