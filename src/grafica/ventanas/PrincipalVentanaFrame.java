package grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grafica.ventanas.panels.JPanelAgregarFolio;
import grafica.ventanas.panels.JPanelAgregarRevision;
import grafica.ventanas.panels.JPanelBorrarFolioRevisiones;
import grafica.ventanas.panels.JPanelDarDescripcion;
import grafica.ventanas.panels.JPanelFolioMasRevisado;
import grafica.ventanas.panels.JPanelListarFolios;
import grafica.ventanas.panels.JPanelListarRevisiones;

public class PrincipalVentanaFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalVentanaFrame frame = new PrincipalVentanaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrincipalVentanaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Gestión Folios");
		menuBar.add(mnNewMenu);

		JMenuItem mntmAgregarFolio = new JMenuItem("Agregar Folio");
		mntmAgregarFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelAgregarFolio());
			}
		});
		mnNewMenu.add(mntmAgregarFolio);

		JMenuItem mntmBorrarFolio = new JMenuItem("Borrar Folio");
		mntmBorrarFolio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelBorrarFolioRevisiones());
			}
		});
		mnNewMenu.add(mntmBorrarFolio);

		JMenuItem mntmFolioMasRevisado = new JMenuItem("Folio Más Revisado");
		mntmFolioMasRevisado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelFolioMasRevisado());
			}
		});
		mnNewMenu.add(mntmFolioMasRevisado);

		JMenuItem mntmListarFolios = new JMenuItem("Listar Folios");
		mntmListarFolios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelListarFolios());
			}
		});
		mnNewMenu.add(mntmListarFolios);

		JMenu mnNewMenu_1 = new JMenu("Gestión Revisiones");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Agregar Revisión");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelAgregarRevision());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Ver Descripción");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelDarDescripcion());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmListarRevisiones = new JMenuItem("Listar Revisiones");
		mntmListarRevisiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(new JPanelListarRevisiones());
			}
		});
		mnNewMenu_1.add(mntmListarRevisiones);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

	}

	private void cambiarPanel(JPanel panel) {
		setContentPane(panel);
		revalidate();
		repaint();
		pack();
	}

}
