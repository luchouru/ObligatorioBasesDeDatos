package grafica.ventanas.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import grafica.controladores.AgregarFolioControlador;

public class JPanelAgregarFolio extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtCaratula;
	private JTextField txtPagina;
	private AgregarFolioControlador controlador;

	public JPanelAgregarFolio() {
		this.controlador = new AgregarFolioControlador(this);

		JLabel lblNewLabel = new JLabel("Agregar Folio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCodigo = new JLabel("Código:");
		labelCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelCaratula = new JLabel("Carátula:");
		labelCaratula.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel labelPagina = new JLabel("Página:");
		labelPagina.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnIngresar = new JButton("Ingresar");
		JButton btnLimpiar = new JButton("Limpiar");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCaratula = new JTextField();
		txtCaratula.setColumns(10);
		txtPagina = new JTextField();
		txtPagina.setColumns(10);

		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = txtCodigo.getText();
				String caratula = txtCaratula.getText();
				String paginaString = txtPagina.getText();

				controlador.agregarFolio(codigo, caratula, paginaString);
				txtCodigo.setText("");
				txtCaratula.setText("");
				txtPagina.setText("");
			}
		});
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodigo.setText("");
				txtCaratula.setText("");
				txtPagina.setText("");
			}
		});

		txtCaratula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});
		txtCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isWhitespace(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});
		txtPagina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIngresar.doClick();
				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
								.addComponent(btnIngresar))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(labelPagina, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelCaratula, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(labelCodigo, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtPagina, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
												.addComponent(txtCaratula, GroupLayout.DEFAULT_SIZE, 331,
														Short.MAX_VALUE)
												.addComponent(txtCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														331, Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelCodigo).addComponent(
						txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCaratula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelCaratula))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPagina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPagina))
				.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE).addGroup(groupLayout
						.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar).addComponent(btnLimpiar))
				.addContainerGap()));
		setLayout(groupLayout);

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
