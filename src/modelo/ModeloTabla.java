package modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.xml.crypto.Data;

public class ModeloTabla extends AbstractTableModel {
	
	String[] cabecera;
	Object[][] datos;
	CocheDAOImp cocheDAO = new CocheDAOImp();
	private List<CocheDTO> listaDeCoches;
	int contador = 0;
	int count = 0;





	public ModeloTabla() {
		cabecera = new String[]{"NumeroBastidor", "Marca","Modelo", "Color"};
		listaDeCoches = cocheDAO.listarTodosLosCoches();
		count = listaDeCoches.size();
		datos = new Object[listaDeCoches.size()][4];
		for (CocheDTO coche : listaDeCoches) {
			datos[contador][0]= coche.getNumeroBastidor();
			datos[contador][1]= coche.getMarca();
			datos[contador][2]= coche.getModelo();
			datos[contador][3]= coche.getColor();
			contador++;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cabecera.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getValueAt(int rowIndex, int columIndex) {
		// TODO Auto-generated method stub
		return datos[rowIndex][columIndex];
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabecera[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex < 1)
			return false;
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		datos[rowIndex][columnIndex] = aValue;
		System.out.println(aValue);
		System.out.println(datos[rowIndex][columnIndex]);
		System.out.println(aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
		String numeroBastidor = (String) datos[rowIndex][0];
		String marca = (String) datos[rowIndex][1];
		String modelo = (String) datos[rowIndex][2];
		String color = (String) datos[rowIndex][3];
		try {
			CocheDTO dtoUpdate = new CocheDTO(numeroBastidor, marca, modelo, color);

			cocheDAO.actualizarMarca(dtoUpdate);
		}catch (CocheException e) {
			e.printStackTrace();
		}


	}

	public void deleteRow(int row) {

		Object numeroBastidor = getValueAt(row, 0);
		cocheDAO.borrarCoche((String)datos[row][0]);
		for (int i = row; i < datos.length - 1; i++) {
			datos[i][0] = datos[i+1][0];
			datos[i][1] = datos[i+1][1];
			datos[i][2] = datos[i+1][2];
			datos[i][3] = datos[i+1][3];
			
		}
		count--;
		fireTableDataChanged();
	}
}
