package Persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Dominio.Empleado;

public class EmpleadoPers {
public EmpleadoPers(){
	
}
public ArrayList<Empleado> leer() throws FileNotFoundException {
	
		ArrayList <Empleado> empleados = new ArrayList <Empleado>();
		Scanner out = new Scanner (new FileReader("Usuarios.txt"));
		out.next();
		int contador = out.nextInt();
		//Leer empleados
		for (int i=0; i<contador;i++) {
			out.next();
			int codigo = out.nextInt();
			out.next();
			String nombre = out.next();
			out.next();
			String password = out.next();

			//Guardo un usuario
			Empleado emp = new Empleado(codigo,nombre,password);
			
			//incluyo en la lista
			empleados.add(emp);
		}
		
		return empleados;
	}
	
}
