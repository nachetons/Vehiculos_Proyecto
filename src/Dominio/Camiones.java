package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.CamionesPers;

// Esta es la clase camiones, que tiene una extensi�n de la clase Veh�culo, donde se cogen las variables de la clase Veh�culo,
// y ademas se declar�n otras, dentro de la clase camiones.

public class Camiones extends Vehiculo{
	//Declaraci�n de variables
	private CamionesPers camionespers;
	private int capacidad_carga;

	// Creaci�n de los constructores, en la clase camiones.
	public Camiones(String matricula, String marca, String modelo, String color, int precio, int capacidad_carga) {
		super(matricula, marca, modelo, color, precio);
		this.capacidad_carga = capacidad_carga;
		camionespers = new CamionesPers();

	}
	public Camiones() {
		camionespers = new CamionesPers();
	}
	public CamionesPers getCamionesPers() {
		return camionespers;
	}

	// Creaci�n de getters y setters
	public int getCapacidad_carga() {
		return capacidad_carga;
	}

	public void setCapacidad_carga(int capacidad_carga) {
		this.capacidad_carga = capacidad_carga;
	}
	
	// 	
	public ArrayList<Vehiculo> leer() throws FileNotFoundException{
		return camionespers.leer();
	}
	
	// Creaci�n de ToString, para poder visualizar los datos de la clase Camiones.
	@Override
	public String toString() {
		return "Camiones [Capacidad=" + capacidad_carga + ", Matricula="
				+ getMatricula() + ", Marca=" + getMarca() + ", Modelo=" + getModelo() + ", Color="
				+ getColor() + ", Precio=" + getPrecio() + "]";
	}
	
	// Con esto podemos leer y escribir los datos que tengamos en la clase Turismo. Y estas dos funciones se declar�n dentro de la
	// clase TurismoPers
	public void escribir(ArrayList<Vehiculo> camiones) throws IOException{
		camionespers.escribir(camiones);
	}




}
