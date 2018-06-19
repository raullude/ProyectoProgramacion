package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.Logs;

public class CocheDAOImp implements CocheDAO {
	private static Connection conexion = Conexion.getConexion();
	

	@Override
	public List<CocheDTO> listarTodosLosCoches() {
		List<CocheDTO> listaCoches = new ArrayList<>();
		// Crear objeto Statement
		String sql = "SELECT * FROM coche;";
		try (Statement statement = conexion.createStatement();){
			// Crear objeto ResultSet
			ResultSet resulset = statement.executeQuery(sql);
			while (resulset.next()) {
				CocheDTO coche = new CocheDTO(resulset.getString(1),
						resulset.getString(2), resulset.getString(3), resulset.getString(4));
				listaCoches.add(coche);

			}
		} catch (SQLException | CocheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaCoches;
	
	}

	@Override
	public boolean borrarCoche(String numeroBastidor) {
		int deletes = 0;
		String sql = "DELETE from coche WHERE numeroBastidor = ?";
		try (PreparedStatement preparedstatement = conexion.prepareStatement(sql);) {
			preparedstatement.setString(1, numeroBastidor);
			deletes = preparedstatement.executeUpdate();
			Logs.crearLog("Coche borrado el " + LocalDate.now() + " con numero de bastidor " + numeroBastidor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deletes != 0;
	}

	@Override
	public boolean actualizarMarca(CocheDTO coche) {
		int actualizados = 0;
		String sql = "UPDATE coche set marca= ?, modelo = ?, color= ? where numeroBastidor = ?; ";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);
) {
			preparedStatement.setString(1, coche.getMarca());
			preparedStatement.setString(2, coche.getModelo());
			preparedStatement.setString(3, coche.getColor());
			preparedStatement.setString(4, coche.getNumeroBastidor());
			actualizados = preparedStatement.executeUpdate();
			Logs.crearLog("Coche actualizado el " + LocalDate.now() + " con numero de bastidor " + coche.getNumeroBastidor());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actualizados == 1;
	}

	@Override
	public boolean insertarCoche(CocheDTO coche) {
		int inserts = 0;
		String sql = "INSERT INTO coche (numeroBastidor,marca,modelo,color) VALUES (?,?,?,?);";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);){
			preparedStatement.setString(1, coche.getNumeroBastidor());
			preparedStatement.setString(2, coche.getMarca());
			preparedStatement.setString(3, coche.getModelo());
			preparedStatement.setString(4, coche.getColor());
			inserts = preparedStatement.executeUpdate();
			Logs.crearLog("Coche insertado el " + LocalDate.now() + " con numero de bastidor " + coche.getNumeroBastidor());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inserts == 1;
	}

	@Override
	public boolean insertarListaCoches(List<CocheDTO> listaCoches) {
		try {
			conexion.setAutoCommit(false);
			for (CocheDTO cocheDTO : listaCoches) {
				insertarCoche(cocheDTO);
			}
			conexion.commit();
			conexion.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					conexion.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return false;
			}
		}
	}

	@Override
	public void crearTabla() {
		int tablaCreada = 0;
		String sql = "CREATE TABLE coche("
				+ "numeroBastidor varchar(20),"
				+ "marca varchar(15),"
				+ "modelo varchar(15),"
				+ "color varchar(15)"
				+ ");";
		
		try (Statement statement = conexion.createStatement();) {
			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public boolean hayDatos() {
		int datos = 0;
		String sql = "Select count(*) from coche;";
		try (PreparedStatement preparedStatement = conexion.prepareStatement(sql);) {
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.getInt(1)>0) {
				datos = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay tabla");
		}
		return datos == 1;
		
	}

}
