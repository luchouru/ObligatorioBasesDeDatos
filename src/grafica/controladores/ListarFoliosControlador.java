package grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;

import grafica.ventanas.panels.JPanelListarFolios;
import logica.VOs.VoFolio;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public class ListarFoliosControlador extends ControladorPantalla {

	public ListarFoliosControlador(JPanelListarFolios listarFoliosVentana) {
		super(listarFoliosVentana);
	}

	public void listarFolios() {
		List<VoFolio> folios = null;
		try {
			folios = this.getModel().listarFolios();
			if (folios.isEmpty()) {
				((JPanelListarFolios) this.getVentana()).mostrarMensajeError("No hay folios");
			} else {
				((JPanelListarFolios) this.getVentana()).cargarListadoFolios(folios);
			}
		} catch (RemoteException | ErrorLogica | ErrorPersistencia e) {
			((JPanelListarFolios) this.getVentana()).mostrarMensajeError(e.getMessage());
		}
	}

}
