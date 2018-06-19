package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Logs {
	public static void  crearLog(String log){
		try (FileWriter writer = new FileWriter("ficheros/bd.log", true);) {
			writer.write(log);
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//método que crea un String con el contenido del fichero db.log
	public static String leerFicheroLog(){
		String log = "";
		try (Scanner in = new Scanner(new File("ficheros/bd.log"));) {
			while (in.hasNextLine()) {
				log += in.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return log;
	}
}
