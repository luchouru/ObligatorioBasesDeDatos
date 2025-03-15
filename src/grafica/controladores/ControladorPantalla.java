package grafica.controladores;

import java.rmi.Naming;

import javax.swing.JPanel;

import logica.IFachada;
import logica.conexion.ArchivoConfiguracion;
import logica.conexion.Connector;
import logica.excepciones.ErrorLogica;
import logica.excepciones.ErroresLogica;

public class ControladorPantalla {
	private JPanel ventana;
	private IFachada model;

	public ControladorPantalla(JPanel jPanelBase) {
		this.ventana = jPanelBase;
	}

	protected JPanel getVentana() {
		return ventana;
	}

	protected IFachada getModel() throws ErrorLogica {
		try {
			this.model = (IFachada) Connector.createConnection();
		} catch (ErrorLogica e) {
			throw new ErrorLogica(e.getMessage());
		}
		return model;
	}
}
