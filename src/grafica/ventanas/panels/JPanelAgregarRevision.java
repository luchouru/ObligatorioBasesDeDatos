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

import grafica.controladores.AgregarRevisionControlador;

public class JPanelAgregarRevision extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodFolio;
	private JTextField txtDescripcion;
	private AgregarRevisionControlador controlador;

	public JPanelAgregarRevision() {
		this.controlador = new AgregarRevisionControlador(this);
		setBorder(null);
		JLabel labelTitulo = new JLabel("Agregar Revision");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCodFolio = new JLabel("Código de folio");
		labelCodFolio.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodFolio = new JTextField();
		txtCodFolio.setColumns(10);
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoFolio = txtCodFolio.getText();
				String descripcion = txtDescripcion.getText();

				controlador.agregarRevision(codigoFolio, descripcion);
				txtCodFolio.setText("");
				txtDescripcion.setText("");
			}
		});

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodFolio.setText("");
				txtDescripcion.setText("");
			}
		});
		txtCodFolio.addKeyListener(new KeyAdapter() {
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

		txtDescripcion.addKeyListener(new KeyAdapter() {
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

		JLabel labelDescripcion = new JLabel("Descripción");
		labelDescripcion.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
								.addComponent(btnIngresar))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelDescripcion, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
										.addComponent(labelCodFolio, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtDescripcion)
										.addComponent(txtCodFolio, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(labelTitulo).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCodFolio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCodFolio))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelDescripcion))
						.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar)
								.addComponent(btnLimpiar))
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
