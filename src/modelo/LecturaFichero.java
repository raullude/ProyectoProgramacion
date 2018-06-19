package modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class LecturaFichero {
	
	public static List<CocheDTO> lecturaJSON(String path){
		List<CocheDTO> listaCoches = new ArrayList<>();
		JsonReader in = null;
		try {
			in = new JsonReader(new FileReader(path));
			Gson gson = new GsonBuilder().create();
			in.beginArray();
			while(in.hasNext())
				listaCoches.add(gson.fromJson(in, CocheDTO.class));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listaCoches;
		
	}
	
}

