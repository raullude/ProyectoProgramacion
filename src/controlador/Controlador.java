package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import modelo.CocheDAOImp;
import modelo.CocheDTO;
import modelo.CocheException;
import modelo.LecturaFichero;
import modelo.ModeloTabla;
import modelo.Tabla;
import vista.InterfazProyecto;


public class Controlador implements ActionListener {
	private InterfazProyecto vista;
	private CocheDAOImp cocheDAO;
	private List<CocheDTO> listaCoches;
	private int contador = 0;
	private String path;
	private Tabla tabla;
	private JTable tablaVista;
	private ModeloTabla modeloTabla;
	CocheDAOImp implementacion = new CocheDAOImp();
	
	

	public Controlador(InterfazProyecto vista) {
		this.vista = vista;
		cocheDAO = new CocheDAOImp();
		registrarBotones();
		comprobarDatos();
		
	}
	
	private void comprobarDatos() {
		if(hayDatos()) {
			rellenarTabla();
			vista.getMntmAnnadirFichero().setEnabled(false);
			activarBotones();
			
		}else {
			vista.getMntmAnnadirFichero().setEnabled(true);

		}
	}

	private void registrarBotones() {
		vista.getBtnBorrar().addActionListener(this);
		vista.getBtnInsertar().addActionListener(this);
		vista.getBtnLimpiar().addActionListener(this);
		vista.getButtonMas20().addActionListener(this);
		vista.getButtonMenos20().addActionListener(this);
		vista.getMnAyuda().addActionListener(this);
		vista.getMntmAnnadirFichero().addActionListener(this);
		vista.getMntmSalir().addActionListener(this);
	}
	
	

	private void activarBotones() {
		vista.getMntmAnnadirFichero().setEnabled(false);
		vista.getBtnInsertar().setEnabled(true);
		vista.getBtnBorrar().setEnabled(true);
		vista.getBtnLimpiar().setEnabled(true);
		vista.getButtonMas20().setEnabled(true);
		vista.getButtonMenos20().setEnabled(true);
	}



	private void rellenarTabla() {
		modeloTabla = new ModeloTabla();
		tablaVista = new JTable(modeloTabla);
		vista.getScrollPane().setViewportView(tablaVista);
	}



	private boolean hayDatos() {
		
		return cocheDAO.hayDatos();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass() == JMenuItem.class) {
			JMenuItem jmenuitem = (JMenuItem) e.getSource();
			if (jmenuitem.getText().equals("Salir")) {
				salir();
			}else if (jmenuitem.getText().equals("Informacion")) {
				informacion("Registro de taller");
			}else {
				elegirFichero();
			}
		}
		if (e.getSource().getClass() == JButton.class) {
			JButton jButton = (JButton) e.getSource();
			String textoBoton = jButton.getText();
			switch (textoBoton) {
			case "Borrar":
				int row = tablaVista.getSelectionModel().getMinSelectionIndex();
				((ModeloTabla)tablaVista.getModel()).deleteRow(row);
				break;
			case "Insertar":
				try {
					InsertarCoche();
				} catch (CocheException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			case "Limpiar":
				limpiarFormulario();
			
			}
		}
		
	}

	private void limpiarFormulario() {
		vista.getTextFieldBastidor().setText("");
		vista.getTextFieldMarca().setText("");
		vista.getTextFieldModelo().setText("");
		vista.getTextFieldColor().setText("");
	}

	private void InsertarCoche() throws CocheException {
		if (vista.getTextFieldBastidor().getText().equals("") || vista.getTextFieldMarca().getText().equals("") || vista.getTextFieldModelo().getText().equals("") || vista.getTextFieldColor().getText().equals("") ) {
			informacion("Falta algun dato por rellenar");
		}else {
			String numeroBastidor = vista.getTextFieldBastidor().getText();
			String marca = vista.getTextFieldMarca().getText();
			String modelo = vista.getTextFieldModelo().getText();
			String color = vista.getTextFieldColor().getText();
			
			CocheDTO coche = new CocheDTO(numeroBastidor, marca, modelo, color);
			cocheDAO.insertarCoche(coche);
			informacion("Nuevo coche introducido");
			comprobarDatos();
		}
		
	}

	private void elegirFichero() {
		JFileChooser jFileChooser = new JFileChooser();
		int resultado = jFileChooser.showOpenDialog(null);
		if(resultado == jFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getSelectedFile().getPath();
			LecturaFichero fichero = new LecturaFichero();
			cocheDAO.crearTabla();
			listaCoches = fichero.lecturaJSON(path);
			cocheDAO.insertarListaCoches(listaCoches);
		}
		comprobarDatos();
	}

	private void informacion(String string) {
		JOptionPane jOptionPane = new JOptionPane();
		jOptionPane.showMessageDialog(jOptionPane, string);
	}

	private void salir() {
		System.exit(0);
		
	}

}
