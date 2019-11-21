package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.ExtraPers;

// Creaci�n de variables en la clase Extra
public class Extra {
	
	//Se declar�n las variables
	private int id;
	private String descripci�n;
	private ExtraPers extrapers;
	
	// Creaci�n de constructores
	public Extra(int id, String descripci�n) {
		this.id = id;
		this.descripci�n = descripci�n;
		extrapers= new ExtraPers();
	}	
	public Extra() {
		extrapers= new ExtraPers();
	}

	// Craci�n de getters y setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripci�n() {
		return descripci�n;
	}
	public void setDescripci�n(String descripci�n) {
		this.descripci�n = descripci�n;
	}
	
	// Se crea el toString, con esto podemos mostrar los datos que tengamos de las variables indicadas previamente.
	@Override
	public String toString() {
		return "Extra [identificador=" + id + ", descripci�n=" + descripci�n + "]";
	}
	
	// Funciones en la que podemos leer y escribir los datos de extras, dentro de la clase "ExtraPers".
	public ArrayList<Extra> leer() throws FileNotFoundException{
		return extrapers.leer();
	}
	public void escribir(ArrayList<Extra> extras) throws IOException{
		extrapers.escribir(extras);
	}
}
