package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tabla {
	private Connection conexion = Conexion.getConexion();
	private CocheDAOImp cocheDAOImp;
	private List<CocheDTO> listaDeCoches = new ArrayList<>();
	private String[] cabecera;
	private Object[][] datos;

	
	public List<CocheDTO> getListaDeCoches() {
		return listaDeCoches;
	}


	public String[] getCabecera() {
		return cabecera;
	}


	public Object[][] getDatos() {
		return datos;
	}


	public Tabla(){
		cocheDAOImp = new CocheDAOImp();
		listaDeCoches = cocheDAOImp.listarTodosLosCoches();
			int contador = 0;
			datos = new Object[listaDeCoches.size()][4];
			for (CocheDTO coche : listaDeCoches) {
				datos[contador][0]= coche.getNumeroBastidor();
				datos[contador][0]= coche.getMarca();
				datos[contador][0]= coche.getModelo();
				datos[contador][0]= coche.getColor();
				contador++;
			}
			
			
			
		
	}
	
	

}
