package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Camiones;
import Dominio.Turismo;
import Dominio.Vehiculo;

// Clase CamionesPers, en la cual podemos leer las variables creadas en la clase Camiones
public class CamionesPers extends VehiculoPers {
	
	public CamionesPers(){
		
	}
	
	// Función para poder leer los datos que tengamos creados dentro del fichero "Camiones.txt".
	public ArrayList<Vehiculo> leer() throws FileNotFoundException {
		ArrayList<Vehiculo> camiones = new ArrayList<Vehiculo>();

		Scanner in = new Scanner(new FileReader("Camiones.txt"));
		in.next();
		int contador = in.nextInt();

		for (int i = 0; i < contador; i++) {
			in.next();
			String matricula = in.next();
			in.next();
			String marca = in.next();
			in.next();
			String modelo = in.next();
			in.next();
			String color = in.next();
			in.next();
			int precio = in.nextInt();
			in.next();
			int capacidad_carga = in.nextInt();
	
			// Se lee las variables con el ordén indicado a continuación.
			Vehiculo camion = new Camiones(matricula,marca,modelo,color,precio,capacidad_carga);
			camiones.add(camion);
		}
		in.close();
		return camiones;
	}
	
	// Función para poder escribir o modificar los datos que tengamos creados dentro del fichero "Camiones.txt".
	public void escribir(ArrayList<Vehiculo> camiones) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("Camiones.txt"));
		out.println("camiones:");
		out.println(camiones.size());
		for (int i = 0; i < camiones.size(); i++) {
			out.println("Matricula:");
			out.println(camiones.get(i).getMatricula());
			out.println("Marca:");
			out.println(camiones.get(i).getMarca());
			out.println("Modelo:");
			out.println(camiones.get(i).getModelo());
			out.println("Color:");
			out.println(camiones.get(i).getColor());
			out.println("Precio:");
			out.println(camiones.get(i).getPrecio());
			out.println("Capacidad");
			out.println(((Camiones)camiones.get(i)).getCapacidad_carga());
		}
		out.close();
		
	}
}
