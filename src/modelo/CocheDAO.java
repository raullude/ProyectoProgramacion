package modelo;

import java.util.ArrayList;
import java.util.List;

public interface CocheDAO {
	List<CocheDTO> listarTodosLosCoches();
	boolean borrarCoche(String numeroBastidor);
	boolean actualizarMarca(CocheDTO coche);
	boolean insertarCoche(CocheDTO coche);
	boolean insertarListaCoches(List<CocheDTO> listaCoches);
	void crearTabla();
	
}
