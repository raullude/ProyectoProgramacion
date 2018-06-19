package modelo;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public class Conexion {
	
	private static Connection conexion;

	private Conexion () {

		// inicializar el objeto conexion:
		// cargar el fichero propierties y leemos clave/valor
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("BD.properties"));
			String DRIVER = properties.getProperty("DRIVER");
			String DB_URL = properties.getProperty("DB_URL");
			String BD = properties.getProperty("BD");
			//			System.out.println(BD);
			// cargar el driver con Clas.forName
			Class.forName(DRIVER);
			// Configuramos el objeto Config para permitir foreign keys
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			sqLiteConfig.enforceForeignKeys(true);
			// Inicializamos el objeto conexion con DriverManager.getConnection
			conexion = DriverManager.getConnection(
					DB_URL + BD, sqLiteConfig.toProperties());
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public static Connection getConexion() {

		if (conexion == null) {
			new Conexion();
			Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		}

		return conexion; 


	}

	static class ShutdownHook extends Thread{
		@Override
		public void run() {
			if (conexion != null)
				try {
					System.out.println("Cerrando conexión");
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
