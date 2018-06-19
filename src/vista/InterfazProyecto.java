package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class InterfazProyecto {

	private JFrame frame;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldColor;
	private JTextField textFieldBastidor;
	private JButton btnInsertar;
	private JButton btnLimpiar;
	private JButton buttonMenos20;
	private JButton btnBorrar;
	private JButton buttonMas20;
	private JMenuItem mntmAnnadirFichero;
	private JMenu mnAyuda;
	private JMenuItem mntmSalir;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazProyecto window = new InterfazProyecto();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public InterfazProyecto() {
		initialize();
	}

	public JTextField getTextFieldMarca() {
		return textFieldMarca;
	}

	public JTextField getTextFieldModelo() {
		return textFieldModelo;
	}

	public JTextField getTextFieldColor() {
		return textFieldColor;
	}

	public JTextField getTextFieldBastidor() {
		return textFieldBastidor;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnDatos = new JMenu("Datos");
		menuBar.add(mnDatos);
		
		mntmAnnadirFichero = new JMenuItem("A\u00F1adir Fichero");
		mnDatos.add(mntmAnnadirFichero);
		
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Informacion");
		mnAyuda.add(mntmAcercaDe);
		
		mntmSalir = new JMenuItem("Salir");
		mnAyuda.add(mntmSalir);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelTabla = new JPanel();
		tabbedPane.addTab("Datos", null, panelTabla, null);
		panelTabla.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panelTabla.add(panel_1, BorderLayout.SOUTH);
		
		buttonMenos20 = new JButton("<");
		buttonMenos20.setEnabled(false);
		panel_1.add(buttonMenos20);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setEnabled(false);
		panel_1.add(btnBorrar);
		
		buttonMas20 = new JButton(">");
		buttonMas20.setEnabled(false);
		panel_1.add(buttonMas20);
		
		scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panelInsertar = new JPanel();
		tabbedPane.addTab("Insertar", null, panelInsertar, null);
		panelInsertar.setLayout(new GridLayout(0, 2, 0, 40));
		
		JLabel lblNumeroDeBastidor = new JLabel("Numero de bastidor:");
		panelInsertar.add(lblNumeroDeBastidor);
		
		textFieldBastidor = new JTextField();
		panelInsertar.add(textFieldBastidor);
		textFieldBastidor.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		panelInsertar.add(lblMarca);
		
		textFieldMarca = new JTextField();
		panelInsertar.add(textFieldMarca);
		textFieldMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		panelInsertar.add(lblModelo);
		
		textFieldModelo = new JTextField();
		panelInsertar.add(textFieldModelo);
		textFieldModelo.setColumns(10);
		
		JLabel lblColor = new JLabel("Color:");
		panelInsertar.add(lblColor);
		
		textFieldColor = new JTextField();
		panelInsertar.add(textFieldColor);
		textFieldColor.setColumns(10);
		
		JPanel panel = new JPanel();
		panelInsertar.add(panel);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.setEnabled(false);
		panel.add(btnInsertar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setEnabled(false);
		panel.add(btnLimpiar);
	}

	public JMenuItem getMntmSalir() {
		return mntmSalir;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnLimpiar() {
		return btnBorrar;
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
	}

	public JButton getBtnBorrar() {
		return btnLimpiar;
	}

	public JButton getButtonMenos20() {
		return buttonMenos20;
	}

	public JButton getButtonMas20() {
		return buttonMas20;
	}

	public JMenu getMnAyuda() {
		return mnAyuda;
	}

	public JMenuItem getMntmAnnadirFichero() {
		return mntmAnnadirFichero;
	}

}
