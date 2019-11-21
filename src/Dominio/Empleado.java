package Dominio;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Persistencia.EmpleadoPers;
 
// Esta es la clase del usuario que vaya a acceder al menú de los vehículos
// Declaración de variables, que tiene la clase Empleado,
public class Empleado {
	private int codigoAcceso;
	private String nombreUsuario;
	private String password;
	private EmpleadoPers empleadopers;
	
	// Declaración de constructores.
	public Empleado(int codigoAcceso, String nombreUsuario, String password) {
		this.codigoAcceso = codigoAcceso;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.empleadopers= new EmpleadoPers();
	}
	public Empleado() {
		
		this.empleadopers= new EmpleadoPers();
	}
	
	// Creación de getters y setters de las variables indicadas previamente.
	public int getCodigoAcceso() {
		return codigoAcceso;
	}
	public void setCodigoAcceso(int codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Se crea esta clase, para que se pueda leer los datos del usuario.
	public ArrayList<Empleado> leerEmpleados() throws FileNotFoundException {
		return empleadopers.leer();
	}
}
