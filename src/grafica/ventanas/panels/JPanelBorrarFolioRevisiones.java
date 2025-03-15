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

import grafica.controladores.BorrarFolioControlador;

public class JPanelBorrarFolioRevisiones extends JPanel implements IPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCodFolio;
	private BorrarFolioControlador controlador;

	public void mostrarMensajeExito(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Exito!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Atencion!", JOptionPane.ERROR_MESSAGE);
	}

	public JPanelBorrarFolioRevisiones() {
		this.controlador = new BorrarFolioControlador(this);
		setBorder(null);
		JLabel labelTitulo = new JLabel("Borrar Folio");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		JLabel labelCodFolio = new JLabel("Código Folio");
		labelCodFolio.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodFolio = new JTextField();
		txtCodFolio.setColumns(10);

		JButton btnIngresar = new JButton("Borrar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoFolio = txtCodFolio.getText();

				controlador.eliminaFolio(codigoFolio);
				txtCodFolio.setText("");
			}
		});

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCodFolio.setText("");
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

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(labelTitulo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addComponent(btnLimpiar).addGap(18)
										.addComponent(btnIngresar))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(labelCodFolio, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtCodFolio, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(labelTitulo).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCodFolio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCodFolio))
						.addPreferredGap(ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnIngresar)
								.addComponent(btnLimpiar))
						.addContainerGap()));
		setLayout(groupLayout);
	}
}
