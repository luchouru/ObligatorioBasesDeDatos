package grafica.ventanas.panels;

import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ListarFoliosControlador;
import logica.VOs.VoFolio;

public class JPanelListarFolios extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private ListarFoliosControlador controlador;
	private JTable tableFolios;

	public JPanelListarFolios() {
		controlador = new ListarFoliosControlador(this);
		JScrollPane scrollPane = new JScrollPane();

		JLabel lblListaLosFolios = new JLabel("Listar todos los folios");
		lblListaLosFolios.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaLosFolios.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblListaLosFolios, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430,
								Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblListaLosFolios, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE).addContainerGap()));

		tableFolios = new JTable();
		scrollPane.setViewportView(tableFolios);
		setLayout(groupLayout);

		controlador.listarFolios();
		scrollPane.setViewportView(tableFolios);
	}

	public void cargarListadoFolios(List<VoFolio> listadoFolios) {
		String header[] = { "Codigo", "Caratula", "Paginas" };
		DefaultTableModel modeloTable = new DefaultTableModel();
		modeloTable.setColumnIdentifiers(header);

		for (VoFolio folio : listadoFolios) {
			Object[] objeto = { folio.getCodigo(), folio.getCaratula(), folio.getPaginas() };
			modeloTable.addRow(objeto);
		}

		tableFolios.setModel(modeloTable);
		tableFolios.setDefaultEditor(Object.class, null);
	}

	@Override
	public void mostrarMensajeExito(String mensaje) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error!", JOptionPane.WARNING_MESSAGE);
	}

}
