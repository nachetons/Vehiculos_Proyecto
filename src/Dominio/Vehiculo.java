package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Esta es la clase Vehículo, declarada como abstracta, porque luego se va a llamar a través de dos clases, "Turismo" y "Camiones".
abstract public class Vehiculo{
	
	//Se indica las variables que queramos crear.
	private String matricula;
	private String marca; 
	private String modelo; 
	private String color;
	private int precio;
	
	//Creación de constructores.
	public Vehiculo(String matricula, String marca, String modelo, String color, int precio) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
	}
	public Vehiculo() {
	
	}

	// Creación de getters y setters de las variables que hayamos indicado.
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	// Se crea el toString, con esto podemos mostrar los datos que tengamos de las variables indicadas previamente.
	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", precio=" + precio + "]";
	}
	
	
	//Clases abstractas, para que podamos leer y escribir los datos de los vehículos, que se clarificarán en turismos y camiones.
	abstract public ArrayList<Vehiculo> leer() throws FileNotFoundException;
	abstract public void escribir(ArrayList<Vehiculo> vehiculos) throws IOException;
	
}
