package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.ExtraPers;

// Creación de variables en la clase Extra
public class Extra {
	
	//Se declarán las variables
	private int id;
	private String descripción;
	private ExtraPers extrapers;
	
	// Creación de constructores
	public Extra(int id, String descripción) {
		this.id = id;
		this.descripción = descripción;
		extrapers= new ExtraPers();
	}	
	public Extra() {
		extrapers= new ExtraPers();
	}

	// Cración de getters y setters.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
	// Se crea el toString, con esto podemos mostrar los datos que tengamos de las variables indicadas previamente.
	@Override
	public String toString() {
		return "Extra [identificador=" + id + ", descripción=" + descripción + "]";
	}
	
	// Funciones en la que podemos leer y escribir los datos de extras, dentro de la clase "ExtraPers".
	public ArrayList<Extra> leer() throws FileNotFoundException{
		return extrapers.leer();
	}
	public void escribir(ArrayList<Extra> extras) throws IOException{
		extrapers.escribir(extras);
	}
}
