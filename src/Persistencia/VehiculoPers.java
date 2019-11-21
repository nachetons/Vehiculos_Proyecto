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


// Desclaración de clases abstractas para poder leer y escribir los datos del vehículo
abstract public class VehiculoPers {
	public  VehiculoPers() {
	}
	abstract public ArrayList<Vehiculo> leer() throws FileNotFoundException ;
	
	abstract public void escribir(ArrayList<Vehiculo> turismos) throws IOException;
}
