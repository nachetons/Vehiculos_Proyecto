package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.*;

//Esta es la clase camiones, que tiene una extensión de la clase Vehículo, donde se cogen las variables de la clase Vehículo,
//y ademas se declarán otras, dentro de la clase turismos.

public class Turismo extends Vehiculo {
	
	//Declaración de variables
	private VehiculoPers turismopers;
	private int n_puertas;
	private Extra extra;
	
	// Creación de los constructores, en la clase camiones.
	public Turismo(String matricula, String marca, String modelo, String color, int precio, Extra extra,
			 int n_puertas) {
		super(matricula, marca, modelo, color, precio);
		this.n_puertas = n_puertas;
		this.setExtra(extra);
		turismopers = new TurismoPers();
	}
	
	public Turismo(){
		turismopers = new TurismoPers();
	}
	
	// Creación de getters y setters
	public int getN_puertas() {
		return n_puertas;
	}

	public void setN_puertas(int n_puertas) {
		this.n_puertas = n_puertas;
	}

	public Extra getExtra() {
		return extra;
	}

	public void setExtra(Extra extra) {
		this.extra = extra;
	}
	

	// Creación de ToString, para poder visualizar los datos de la clase Turismo.
	@Override
	public String toString() {
		return "Turismo [Nº Puertas=" + n_puertas + ", Extra=" + extra.getDescripción()
				+ ", Matricula=" + getMatricula() + ", Marca=" + getMarca() + ", Modelo=" + getModelo()
				+ ", Color=" + getColor() + ", Precio=" + getPrecio() + "]";
	}
	
	// Con esto podemos leer y escribir los datos que tengamos en la clase Turismo. Y estas dos funciones se declarán dentro de la
	// clase TurismoPers
	public ArrayList<Vehiculo> leer() throws FileNotFoundException{
		return turismopers.leer();
	}

	public void escribir(ArrayList<Vehiculo> turismos) throws IOException{
		turismopers.escribir(turismos);
	}
		
}
