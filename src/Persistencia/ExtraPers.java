package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import Dominio.Extra;

//Clase ExtraPers, en la cual podemos leer las variables creadas en la clase Extra
public class ExtraPers {
	
	public ExtraPers(){
		
	}

	// Función para poder leer los datos que tengamos creados dentro del fichero "Extras.txt".
	public ArrayList<Extra> leer() throws FileNotFoundException {
		ArrayList<Extra> extras = new ArrayList<Extra>();
		Scanner in = new Scanner(new FileReader("Extras.txt"));
		in.next();
		int contador = in.nextInt();
		// Leer Extra

		for (int i = 0; i < contador; i++) {
		
			in.next();
			int id = in.nextInt();
			in.next();
			in.nextLine();
			String descripción = in.nextLine();
			Extra extra = new Extra(id,descripción);
			extras.add(extra);
		}
		in.close();
		return extras;
	}

	// Función para poder escribir o modificar los datos que tengamos creados dentro del fichero "Extras.txt".
	public void escribir(ArrayList<Extra> extras) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("Extras.txt"));
		out.println("Extras:");
		out.println(extras.size());
		for (int i = 0; i < extras.size(); i++) {
			out.println("Identificador:");
			String cuota=((Extra)extras.get(i)).getId()+"";
			cuota=cuota.replace(".", ",");
			out.println(cuota);			
			out.println("descripción:");
			out.println(extras.get(i).getDescripción());

			}
		out.close();
	}
	
}
