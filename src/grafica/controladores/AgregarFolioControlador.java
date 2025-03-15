package grafica.controladores;

import java.rmi.RemoteException;

import grafica.ventanas.panels.JPanelAgregarFolio;
import logica.VOs.VoFolio;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class AgregarFolioControlador extends ControladorPantalla {

	public AgregarFolioControlador(JPanelAgregarFolio agregarFolioVentanta) {
		super(agregarFolioVentanta);
	}

	public void agregarFolio(String codigo, String caratula, String paginaString) {
		if (codigo.isBlank() | caratula.isBlank() | paginaString.isBlank()) {
			((JPanelAgregarFolio) this.getVentana()).mostrarMensajeError("Uno o mas campos estan en blanco.");
		} else {
			int pags = Integer.parseInt(paginaString);
			VoFolio folio = new VoFolio(codigo, caratula, pags);
			try {
				this.getModel().agregarFolio(folio);
				((JPanelAgregarFolio) this.getVentana()).mostrarMensajeExito("Folio agregado correctamente");
			} catch (ErrorLogica e1) {
				((JPanelAgregarFolio) this.getVentana()).mostrarMensajeError(e1.getMessage());
				e1.printStackTrace();
			} catch (RemoteException | ErrorPersistencia e2) {
				((JPanelAgregarFolio) this.getVentana())
						.mostrarMensajeError("Ocurrio un error inesperado, intente nuevamente en unos minutos.");
				e2.printStackTrace();
			}
		}

	}

}
