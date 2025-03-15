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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import grafica.controladores.DarDescripcionRevisionControlador;

public class JPanelDarDescripcion extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodFolio;
	private JTextField txtNumRevision;
	private JTextArea textDesc;
	private JLabel lblDescripcin;
	private DarDescripcionRevisionControlador controlador;

	public JPanelDarDescripcion() {
		this.controlador = new DarDescripcionRevisionControlador(this);
		setBorder(null);
		JLabel labelTitulo = new JLabel("Obtener Descripción");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCodFolio = new JLabel("Código Folio");
		labelCodFolio.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodFolio = new JTextField();
		txtCodFolio.setColumns(10);
		txtNumRevision = new JTextField();
		txtNumRevision.setColumns(10);

		textDesc = new JTextArea();
		textDesc.setOpaque(false);
		textDesc.setEditable(false);

		JButton btnIngresar = new JButton("Obtener");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoFolio = txtCodFolio.getText();
				String numRevisionString = txtNumRevision.getText();
				controlador.darDescripcion(codigoFolio, numRevisionString);
			}
		});

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodFolio.setText("");
				txtNumRevision.setText("");
				textDesc.setText("");
				textDesc.setOpaque(false);
				lblDescripcin.setText("");
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

		txtNumRevision.addKeyListener(new KeyAdapter() {
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

		JLabel labelDescripcion = new JLabel("Num Revision");
		labelDescripcion.setHorizontalAlignment(SwingConstants.CENTER);

		lblDescripcin = new JLabel("");
		lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTitulo, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
								.addComponent(btnIngresar))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(labelCodFolio, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
										.addComponent(labelDescripcion, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtCodFolio)
										.addComponent(txtNumRevision, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblDescripcin, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textDesc, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(labelTitulo).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCodFolio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCodFolio))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(labelDescripcion)
								.addComponent(txtNumRevision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblDescripcin)
								.addComponent(textDesc, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar)
								.addComponent(btnLimpiar))
						.addContainerGap()));
		setLayout(groupLayout);
	}

	public void cargarDescripcion(String desc) {
		textDesc.setText(desc);
		textDesc.setOpaque(true);
		lblDescripcin.setText("Descripción");
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
