package grafica.ventanas.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import grafica.controladores.ListarRevisionesControlador;
import logica.VOs.VoRevision;

public class JPanelListarRevisiones extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private ListarRevisionesControlador controlador;
	private JTextField txtFolio;
	private JTable tableRevisiones;

	/**
	 * Create the panel.
	 */
	public JPanelListarRevisiones() {
		controlador = new ListarRevisionesControlador(this);
		JScrollPane scrollPane = new JScrollPane();

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codFolio = txtFolio.getText();

				controlador.listarRevisiones(codFolio);
				scrollPane.setViewportView(tableRevisiones);
			}
		});

		txtFolio = new JTextField();
		txtFolio.setColumns(10);

		JLabel labelCodigoFolio = new JLabel("Código de folio");
		labelCodigoFolio.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel labelListarRevisiones = new JLabel("Listar todas las revisiones");
		labelListarRevisiones.setHorizontalAlignment(SwingConstants.CENTER);
		labelListarRevisiones.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(labelListarRevisiones, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(labelCodigoFolio, GroupLayout.PREFERRED_SIZE, 99,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txtFolio, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnBuscar)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(labelListarRevisiones, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnBuscar)
								.addComponent(labelCodigoFolio, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFolio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		tableRevisiones = new JTable();
		scrollPane.setViewportView(tableRevisiones);
		setLayout(groupLayout);
	}

	public void cargarListadoRev(List<VoRevision> listadoRevisiones) {
		String header[] = { "Numero", "Descripcion", "Codigo Folio" };
		DefaultTableModel modeloTable = new DefaultTableModel();
		modeloTable.setColumnIdentifiers(header);

		for (VoRevision revision : listadoRevisiones) {
			Object[] objeto = { revision.getNumero(), revision.getDescripcion(), revision.getCodFolio() };
			modeloTable.addRow(objeto);
		}

		tableRevisiones.setModel(modeloTable);
		tableRevisiones.setDefaultEditor(Object.class, null);
	}

	@Override
	public void mostrarMensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Exito!", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error!", JOptionPane.WARNING_MESSAGE);
	}
}
