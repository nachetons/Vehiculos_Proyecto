package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Extra;
import Dominio.Turismo;
import Dominio.Vehiculo;

//Clase TurismoPers, en la cual podemos leer las variables creadas en la clase Turismos
public class TurismoPers extends VehiculoPers {
	
	public  TurismoPers() {
		
	}
	
	// Función para poder leer los datos que tengamos creados dentro del fichero "Turismos.txt".
	public ArrayList<Vehiculo> leer() throws FileNotFoundException {
		ArrayList<Vehiculo> turismos = new ArrayList<Vehiculo>();
		Extra e= new Extra ();
		ArrayList<Extra> extra = e.leer();

		Scanner in = new Scanner(new FileReader("Turismos.txt"));
		in.next();
		int contador = in.nextInt();

		for (int i = 0; i < contador; i++) {
			in.next();
			String matricula = in.next();
			in.next();
			String marca = in.next();
			in.next();
			in.nextLine();
			String modelo = in.nextLine();
			in.next();
			String color = in.next();
			in.next();
			int n_puertas = in.nextInt();
			in.next();
			int precio = in.nextInt();
			in.next();
			int ExtraId =in.nextInt();
			boolean encontrado=false;
			for(int j=0;j<extra.size();j++) {
				if(extra.get(j).getId()==ExtraId){
					e=extra.get(j);
					encontrado=true;
				}
			}
			if(encontrado==false) {
				e= new Extra (0,"Sin extra");
			}
			Vehiculo turismo = new Turismo(matricula,marca,modelo,color,precio,e,n_puertas);
			turismos.add(turismo);
		}
		in.close();
		return turismos;
	}
	
	// Función para poder escribir o modificar los datos que tengamos creados dentro del fichero "Turismos.txt".
	public void escribir(ArrayList<Vehiculo> turismos) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("Turismos.txt"));
		out.println("turismos:");
		out.println(turismos.size());
		for (int i = 0; i < turismos.size(); i++) {
			out.println("Matricula:");
			out.println(turismos.get(i).getMatricula());
			out.println("Marca:");
			out.println(turismos.get(i).getMarca());
			out.println("Modelo:");
			out.println(turismos.get(i).getModelo());
			out.println("Color:");
			out.println(turismos.get(i).getColor());
			out.println("Precio:");
			out.println(turismos.get(i).getPrecio());
			out.println("NºPuertas:");
			out.println(((Turismo) turismos.get(i)).getN_puertas());
			out.println("Extra:");
			out.println(((Turismo)turismos.get(i)).getExtra().getId());

		}
		out.close();
	}
}
