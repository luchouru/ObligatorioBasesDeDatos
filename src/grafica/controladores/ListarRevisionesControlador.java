package grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;

import grafica.ventanas.panels.JPanelListarRevisiones;
import logica.VOs.VoRevision;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class ListarRevisionesControlador extends ControladorPantalla {

	public ListarRevisionesControlador(JPanelListarRevisiones jPanelListarRevisiones) {
		super(jPanelListarRevisiones);
	}

	public void listarRevisiones(String codigoFolio) {
		List<VoRevision> revisiones = null;

		if (codigoFolio.isBlank()) {
			((JPanelListarRevisiones) this.getVentana()).mostrarMensajeError("Uno o mas campos estan en blanco.");
		} else {

			try {
				revisiones = this.getModel().listarRevisiones(codigoFolio);
				if (revisiones.isEmpty()) {
					((JPanelListarRevisiones) this.getVentana()).mostrarMensajeError("No hay revisiones para el folio");
				} else {
					((JPanelListarRevisiones) this.getVentana()).cargarListadoRev(revisiones);
				}
			} catch (ErrorLogica e) {
				((JPanelListarRevisiones) this.getVentana()).mostrarMensajeError(e.getMessage());
			} catch (RemoteException | ErrorPersistencia e1) {
				((JPanelListarRevisiones) this.getVentana()).mostrarMensajeError(e1.getMessage());
			}

		}

	}
}
