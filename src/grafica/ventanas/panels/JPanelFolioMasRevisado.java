package grafica.ventanas.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import grafica.controladores.FolioMasRevisadoControlador;
import logica.VOs.VoFolioMaxRev;

public class JPanelFolioMasRevisado extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private FolioMasRevisadoControlador controlador;
	private JLabel lblCodigo;
	private JLabel lblCaratula;
	private JLabel lblPaginas;
	private JLabel lblCantRev;
	private JLabel txtCodigo;
	private JLabel txtCaratula;
	private JLabel txtPaginas;
	private JLabel txtCantRev;

	public JPanelFolioMasRevisado() {
		controlador = new FolioMasRevisadoControlador(this);

		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.folioMasRevisado();
			}
		});

		lblCodigo = new JLabel("Código: ");
		lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblCaratula = new JLabel("Carátula: ");
		lblCaratula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaratula.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblPaginas = new JLabel("Páginas: ");
		lblPaginas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginas.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblCantRev = new JLabel("Cant. de Revisiones: ");
		lblCantRev.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantRev.setFont(new Font("Tahoma", Font.PLAIN, 11));

		txtCodigo = new JLabel();
		txtCodigo.setBackground(new Color(255, 255, 255));
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);

		txtPaginas = new JLabel();
		txtPaginas.setBackground(new Color(240, 240, 240));
		txtPaginas.setHorizontalAlignment(SwingConstants.LEFT);

		txtCaratula = new JLabel();
		txtCaratula.setBackground(new Color(240, 240, 240));
		txtCaratula.setHorizontalAlignment(SwingConstants.LEFT);

		txtCantRev = new JLabel();
		txtCantRev.setBackground(new Color(240, 240, 240));
		txtCantRev.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel labelFolioMasRevisado = new JLabel("Folio más revisado");
		labelFolioMasRevisado.setHorizontalAlignment(SwingConstants.CENTER);
		labelFolioMasRevisado.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout.createSequentialGroup()
								.addComponent(labelFolioMasRevisado, GroupLayout.PREFERRED_SIZE, 430,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnMostrar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblCantRev, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														108, Short.MAX_VALUE)
												.addComponent(lblCodigo, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
												.addComponent(lblCaratula, GroupLayout.DEFAULT_SIZE, 108,
														Short.MAX_VALUE)
												.addComponent(lblPaginas, GroupLayout.DEFAULT_SIZE, 108,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtCantRev, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtPaginas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(txtCaratula, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														294, Short.MAX_VALUE))))
								.addGap(32)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(labelFolioMasRevisado, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCaratula, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCaratula, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPaginas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPaginas, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblCantRev, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(76).addComponent(btnMostrar, GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtCantRev, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGap(20)));
		setLayout(groupLayout);
	}

	public void cargarFolioMaxRev(VoFolioMaxRev folioMasRev) {
		txtCodigo.setText(folioMasRev.getCodigo());
		txtCaratula.setText(folioMasRev.getCaratula());
		txtPaginas.setText(String.valueOf(folioMasRev.getPaginas()));
		txtCantRev.setText(String.valueOf(folioMasRev.getCantRev()));
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
