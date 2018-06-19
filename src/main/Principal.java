package main;

import java.awt.EventQueue;

import controlador.Controlador;
import vista.InterfazProyecto;


public class Principal {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazProyecto window = new InterfazProyecto();
					new Controlador(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
