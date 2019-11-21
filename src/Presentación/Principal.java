package Presentaci�n;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Dominio.Camiones;
import Dominio.Empleado;
import Dominio.Turismo;
import Dominio.Vehiculo;
import Dominio.Extra;
class excepcionusuario extends Exception {
}
class excepcioncon extends Exception {
}
class excepcioncodigo extends Exception {
}


public class Principal {

	public static void main(String[] args) throws IOException {
		
		// Cargar los datos
		
		Vehiculo turismo = new Turismo();
		Vehiculo camion = new Camiones();
		ArrayList<Vehiculo> turismos = turismo.leer();
		ArrayList<Vehiculo> camiones = camion.leer();
		Extra extra = new Extra();
		ArrayList<Extra> extras= extra.leer();
		Scanner sc = new Scanner(System.in);
		ArrayList<Empleado> empl = new ArrayList<Empleado>();
		Empleado empl1 = new Empleado();
		empl = empl1.leerEmpleados();
		boolean encontrado = false;
		boolean usuario_correcto = false;
		boolean contrase�a_correcta = false;
		boolean diurno = false;
		boolean nocturno = false;
		boolean sesioner = false;
		int usuario_cod;int i;
		int usuario_nivel = 0;
		String usuario_turno;
		String usuario_pass;
		String usuario_nom = null;
		double usuario_prod = 0;
		Login(sc, empl, usuario_correcto, contrase�a_correcta);
		
		// Aparici�n del Men� principal.
		boolean seguir = true;
		do {
			try {
				System.out.println("Men�");
				System.out.println("1. Mostrar todas los veh�culos");
				System.out.println("2. Buscar un veh�culo");
				System.out.println("3. A�adir un veh�culo");
				System.out.println("4. Modificar un veh�culo");
				System.out.println("5. Eliminar un veh�culo");
				System.out.println("6. Mostrar todos los extras");
				System.out.println("7. A�adir extra");
				System.out.println("8. Eliminar extra");
				System.out.println("9. Log out");
				
				int opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					mostrarTodos(turismos,camiones);
					break;
				case 2:
					buscarVehiculo(turismos,camiones,extra,sc);
					break;
				case 3:
					a�adirVehiculo(turismos,camiones, extras,sc);
					break;
				case 4:
					modificarVehiculo(turismos,camiones,extras, sc);
					break;
				case 5:
					eliminarVehiculo(turismos,camiones, sc);
					break;
				case 6:
					mostrarExtras(extras, sc);
					break;
				case 7:
					a�adirExtra(extras, sc);
					break;
				case 8:
					eliminarExtra(extras, sc);
					break;
				case 9:
					seguir = false;
					System.out.println("Hasta pronto");
					Login(sc, empl, usuario_correcto, contrase�a_correcta);
					seguir = true;
					break;
				default:
					System.out.println("Introduce un n�mero de 1 a 9");
				}

			} catch (InputMismatchException e) {
				System.err.println("Introduce un n�mero");
				sc.nextLine();
			}
	
		} while (seguir);
	}
	
	
	//Clase privada, en la cual ant�s de llegar al men�, se tiene que ingresar un c�digo de usuario y una contrase�a.
	private static void Login(Scanner sc, ArrayList<Empleado> empl, boolean usuario_correcto,boolean contrase�a_correcta) {
		
		//Se cargan los datos.
		boolean diurno;
		boolean sesioner;
		int usuario_cod;
		int i;
		String usuario_pass;
		String usuario_nom;
	
			do {
			sesioner=false;
				System.out.println("Introduce el codigo de usuario: ");
				usuario_cod = sc.nextInt();
				System.out.println("Introduce su contrase�a: ");
				usuario_pass = sc.next();
				for (i = 0; i < empl.size(); i++) {
					if (i < empl.size()) {
						if (usuario_cod == empl.get(i).getCodigoAcceso()) {
							usuario_correcto = true;
							if (usuario_pass.equals(empl.get(i).getPassword())) {
								contrase�a_correcta = true;
								sesioner = true;
								usuario_nom = empl.get(i).getNombreUsuario();
								diurno = true;
								i = empl.size();
							}
						}				
					}else {
						
					}
				}
			
			// Se ponen las excepciones. Si se falla en el usuario o en la contrase�a, se vuelve a preguntar el login, y te indicar�
			// el error que se ha tenido.
			try {
				if (usuario_correcto == false)
					throw new excepcionusuario();
			
				if (contrase�a_correcta == false)
					throw new excepcioncon();
		
			} catch (excepcionusuario e) {
				System.err.println("Usuario incorrecto");
				sc.nextLine();	
			} catch (excepcioncon e) {
				System.err.println("Contrase�a incorrecta");
				sc.nextLine();
				
			// La variable sesioner, permitir� la aparici�n del men�. Cuando se haya introducido los datos del login correctamente,
			// esta variable se activar�, y te mostrar� el men� correctamente. Mientras tanto, esta no funcionar�, si se han intro-
			// -ducido los datos erroneamente, con lo cual este no se activar�.
			}
			} while (sesioner==false);
		}	
	
	//Clase privada, despu�s de ver el resultado de una de las opciones que hayamos seleccionado en el men�, nos saldr� un submen�,
	// nos indicar� si queremos volver al men�, o si queremos salir de la aplicaci�n.
	private static void Submenus(Scanner sc) {
		//Se cargan los datos
		boolean seguir;
		int opcion = 0;
		do {
		try {
			System.out.println("");
			System.out.println("");
			System.out.println("Men�");
			System.out.println("1. Pulse 1 si quiere volver al menu principal");
			System.out.println("2. Pulse 2 si quiere salir");
		
			
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				
				seguir = true;
				break;
			case 2:
				seguir = false;
				System.out.println("Hasta pronto");
				System.exit(0);
				break;
			
			default:
				seguir=false;
				System.out.println("Introduce 1 o 2");
			
			}
		// Si se pusiese en vez de un n�mero una letra, saltar�a la excepci�n, solo debemos introducir n�meros.
		} catch (InputMismatchException e) {
			System.err.println("Introduce un n�mero");
			sc.nextLine();
		}
		} while (opcion>2);
	}
	
	
	// Clase publica, una de las opciones del men�, para visualizar todos los veh�culos que tengamos en nuestro fichero de "Turismos.txt",
	//  y de "Camiones.txt".
	public static void mostrarTodos(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones) {
		
		System.out.println("Camiones");
		
		//Carga de datos
		Scanner sc = new Scanner(System.in);
		boolean seguir = false;
		boolean sesioner = false;
		
		//Se muestra toda la informaci�n de los camiones y de los turismos. Indicando las variables que tengamos declaradas en cada
		// una de las clases de turismos y camiones (y extras, ya que esta relacionado con turismos.).
		for (int i = 0; i < camiones.size(); i++) {
			System.out.println(camiones.get(i).toString());
		}
		System.out.println("Turismos");
		for (int i = 0; i < turismos.size(); i++) {
			System.out.println(turismos.get(i).toString());
		}
		
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);
	}
	
	//Clase publica, se busca el veh�culo que nosotros indiquemos con la matr�cula.
	public static void buscarVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, Extra extra, Scanner sc) {
		
		System.out.println("Indica la matr�cula");
		String Matricula = sc.next();
		
		//Carga de datos
		boolean encontrado = false;
		
		//Nos buscar� la matr�cula que hayamos escrito, y se imprimir� el veh�culo seleccionado, con todos sus datos.
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(Matricula)) {
				System.out.println(camiones.get(i).toString());
				encontrado = true;
			}
		}
		for (int i = 0; i < turismos.size(); i++) {
			if (turismos.get(i).getMatricula().equals(Matricula)) {
				System.out.println(turismos.get(i).toString());
				encontrado = true;
			}
		}
		
		//En el caso de no haber encontrado el veh�culo, nos mostrar� un mensaje, indicando que no se ha encontrado el veh�culo.
		if (encontrado == false) {
			System.out.printf("No existe el veh�culo con la matr�cula %s\n", Matricula);

		}
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);
		
	}
	
	//Clase p�blica para a�adir un veh�culo
	public static void a�adirVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, ArrayList<Extra> extras, Scanner sc) throws IOException {
		
		//Carga de datos.
		boolean seguir = false;
		String Matricula = "";
		
		//Primero debemos introducir la m�tricula, en el caso de haber introducido una matr�cula repetida, nos volver� a preguntar
		//la m�tr�cula, en caso contrario, seguiremos con la siguiente pregunta.
		do {
			seguir = false;
			System.out.println("Introduzca la Matr�cula");
			Matricula = sc.next();
			for (int i = 0; i < camiones.size(); i++) {
				if (camiones.get(i).getMatricula().equals(Matricula)) {
					System.out.println("Matr�cula repetido");
					seguir = true;
				}
			}
			for (int i = 0; i < turismos.size(); i++) {
				if (turismos.get(i).getMatricula().equals(Matricula)) {
					System.out.println("Matr�cula repetida");
					seguir = true;
				}
			}

		} while (seguir);
		
		// Despu�s, se debe introducir la marca, el modelo y color.
		System.out.println("Introduzca la marca");
		String marca = sc.next();
		System.out.println("Introduzca el modelo");
		String modelo = sc.next();
		System.out.println("Introduzca el color");
		String color = sc.next();
		// Carga de datos
		int precio = 0;
		
		// Y a continuaci�n se pedir� el precio en n�meros. Si se pone letras, te volver� a preguntar el precio, y adem�s, te dir�
		// que solo debes introducir n�meros
		do {
			seguir = false;
			try {
				System.out.println("Introduzca el precio");
				precio = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo n�meros");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
		// carga de datos
		int tipo;
		int Extra =0;
		int capacidad_carga = 0;
		Extra extra= new Extra();

		// Te indicara si quieres un turismo, o un camion
		do {
		System.out.println("�Es un camion 1 o un turismo 2?");
		tipo=sc.nextInt();
		
		if(tipo==1) {
			// Este caso es el de cami�n, se deber� introducir la capacidad de carga en n�meros, o sino te volver� a preguntar
			// la cantidad, y te indicar� que solo deber�s introducir n�meros.
			do {
				seguir = false;
				try {
		
					System.out.println("Introduzca la capacidad de carga");
					capacidad_carga = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo n�meros");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			
			// Y con todos los datos ingresados, se crear� el nuevo camion, con los datos que hayamos escrito.
			Vehiculo newVehiculo = new Camiones(Matricula, marca, modelo, color, precio, capacidad_carga);
			camiones.add(newVehiculo);
			newVehiculo.escribir(camiones);
			
		}else if(tipo==2){
			// Esta es la segunda opci�n, si hemos indicado de crear un turismo, nos pedir� el n�mero de puertas con un n�mero,
			// y despu�s debemos introducir el extra, en la cual nos pondr�n los extras que est�n disponibles.
			int n_puertas = 0;
			Extra = 0;
			System.out.println("Introduzca el n�mero de puertas");
			n_puertas= sc.nextInt();
			do {
				seguir = false;
				try {
					
					//Si se pone un 0, eso querr� decir que no queremos ning�n extra. Y aqui se debe introducir n�meros, no letras.
					System.out.println("Introduzca el extra");
					sc.nextLine();
					System.out.println("1. Lunas pintadas");
					System.out.println("2. Ruedas �ltima generaci�n");
					System.out.println("3. Nacho de bolsillo en Black Desert");
					Extra = sc.nextInt();
					boolean encontrado=false;
					for(int j=0;j<extras.size();j++) {
						if(extras.get(j).getId()==Extra) {
							encontrado=true;
							extra=extras.get(j);
						}
					} 
					if(encontrado==false) {
						extra = new Extra (0,"Sin extra");

					}
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo n�meros");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			
			// Y desp��s se crear� el turismo, con los datos que hayamos escrito.
			Vehiculo newVehiculo = new Turismo(Matricula, modelo, marca, color, precio, extra, n_puertas);
			turismos.add(newVehiculo);
			newVehiculo.escribir(turismos);
		}
		}while(tipo<1 || tipo >2);
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);

	}

	// Clase publica, para modificar un vehiculo que este previamente creado, y podemos buscar el veh�culo, introduciendo la matr�cula.
	// Si se ha encontrado el veh�culo, nos mostrar� un men�, indicando que es lo que debemos modificar. Si no se ha encontrado la
	// matr�cula, nos volver� a preguntar la m�tricula, y nos indicar� que no se ha encontrado el veh�culo con la matr�cula correspondiente.
	public static void modificarVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, ArrayList<Extra> extra, Scanner sc) throws IOException {
		
		System.out.println("Indica la matr�cula");
		String matricula = sc.next();
		boolean encontrado = false;
		
		// Este es el caso de los camiones, lo �nico que cambiar� en comparaci�n de los turismos, es que en uno de podr� cambiar la
		// capacidad de carga (Camiones), y en otro se puede cambiar el extra (En Turismos).
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(matricula)) {
				System.out.println("�Qu� deseas modificar?");
				System.out.println("1. Matr�cula");
				System.out.println("2. Marca");
				System.out.println("3. Modelo");
				System.out.println("4. Color");
				System.out.println("5. Capacidad de carga");
				System.out.println("6. Precio");
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Introduzca una nueva matr�cula");
					String Matricula = sc.next();
					camiones.get(i).setMatricula(Matricula);
					break;
				case 2:
					System.out.println("Introduzca una nueva Marca");
					String Marca = sc.next();
					camiones.get(i).setMarca(Marca);
					break;
				case 3:
					System.out.println("Introduce un nuevo Modelo");
					String Modelos = sc.next();
					camiones.get(i).setModelo(Modelos);
					break;
				case 4:
					System.out.println("Introduce un nuevo Color");
					String Colores = sc.next();
					camiones.get(i).setColor(Colores);
					break;
				case 5:
					boolean seguir = false;
					do {
						seguir = false;
						try {
							System.out.println("Introduce una nueva capacidad de carga");
							int carga = sc.nextInt();
							((Camiones)camiones.get(i)).setCapacidad_carga(carga);
							break;
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo n�meros");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					break;
				case 6:
					seguir = false;
					do {
						seguir = false;
						try {
							System.out.println("Introduce un nuevo precio");
							int precio = sc.nextInt();
							((Camiones)camiones.get(i)).setPrecio(precio);
							break;
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo n�meros");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					break;
				}
				// Se nos habr� modificado el cami�n que hayamos seleccionado previamente
				encontrado = true;
				Vehiculo modVehiculo = new Camiones();
				modVehiculo.escribir(camiones);
			}
		}
		
		// Lo mismo, pero en turismos. Pero indicando extras, y no capacidad de carga.
		for (int i = 0; i < turismos.size(); i++) {
			if (turismos.get(i).getMatricula().equals(matricula)) {
				System.out.println("�Qu� deseas modificar?");
				System.out.println("1. Matr�cula");
				System.out.println("2. Marca");
				System.out.println("3. Modelo");
				System.out.println("4. Color");
				System.out.println("5. N� de puertas");
				System.out.println("6. Beca");
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Introduzca una nueva matr�cula");
					String Matricula = sc.next();
					turismos.get(i).setMatricula(Matricula);
					break;
				case 2:
					System.out.println("Introduzca una nueva Marca");
					String Marca = sc.next();
					turismos.get(i).setMarca(Marca);
					break;
				case 3:
					System.out.println("Introduce un nuevo Modelo");
					String Modelos = sc.next();
					turismos.get(i).setModelo(Modelos);
					break;
				case 4:
					System.out.println("Introduce un nuevo Color");
					String Colores = sc.next();
					turismos.get(i).setColor(Colores);
					break;
				case 5:
					boolean seguir = false;
					do {
						seguir = false;
						try {
							System.out.println("Introduce el m�mero de puertas");
							int n�puertas = sc.nextInt();
							((Turismo)turismos.get(i)).setN_puertas(n�puertas);
							break;
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo n�meros");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					break;
				case 6:
					seguir = false;
					do {
						seguir = false;
						try {
							System.out.println("Introduce un nuevo extra");
							int Extr= sc.nextInt();
							encontrado=false;
							for(int j=0;j<extra.size();j++) {
								if(extra.get(j).getId()==Extr) {
									encontrado=true;
									((Turismo)turismos.get(i)).setExtra(extra.get(j));
								}
							}
							if(encontrado==false) {
								Extra sinExtra = new Extra (0,"Sin extra");
								((Turismo)turismos.get(i)).setExtra(sinExtra);

							}
							break;
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo n�meros");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					break;
				}
				// Se nos habr� modificado el cami�n que hayamos seleccionado previamente
				encontrado = true;
				Vehiculo modVehiculo = new Turismo();
				modVehiculo.escribir(turismos);
			}
		}
		
		//Es el caso de no haber encontrado la matricula escrita, nos indicar� este texto.
		if (encontrado == false) {
			System.out.printf("No existe el vehiculo con la matr�cula %s\n", matricula);

		}
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);

	}
	
	
	// Clase p�blica en la que podremos borrar el vehiculo que queramos.
	public static void eliminarVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, Scanner sc) throws IOException{
		
		// Primero debemos indicar la matr�cula del vehiculo que queramos borrar.
		System.out.println("Indique la matr�cula del veh�culo");
		String Matricula = sc.next();
		
		// carga de datos
		boolean encontrado = false;
		
		// Te buscar� si ese veh�culo existe. En el caso de exista, se borrar� autom�ticamente. Este es en el apartado de camiones.
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(Matricula)) {
				camiones.remove(i);
				Vehiculo delVehiculo = new Camiones();
				delVehiculo.escribir(camiones);
				encontrado = true;
			}
		
		}
		
		// Lo mismo, pero en turismos.
		for (int i = 0; i < turismos.size(); i++) {
			if (turismos.get(i).getMatricula().equals(Matricula)) {
				turismos.remove(i);
				Vehiculo delVehiculo = new Turismo();
				delVehiculo.escribir(turismos);
				encontrado = true;
			}
		}
		
		//Si no se ha encontrado el veh�culo, nos mostrar� este mensaje.
		if (encontrado == false) {
			System.out.printf("No existe el veh�culo con la matr�cula %s\n", Matricula);
			
		}
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);
	}
	
	// Clase p�blica, en la cual podremos ver todos los extras que tengamos creados de los turismos.
	public static void mostrarExtras(ArrayList<Extra> extras, Scanner sc) {
		System.out.println("Extras");
		
		// Funci�n en la cual mostrar� los extras.
		for (int i = 0; i < extras.size(); i++) {
			System.out.println(extras.get(i).toString());
		}
		
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);
		
	}
	
	// Clase p�blica para poder crear un extra.
	public static void a�adirExtra(ArrayList<Extra> extras, Scanner sc) throws IOException {
		
		// carga de datos
		boolean seguir = false;
		// primero debemos crear la descripci�n del extra que nosotros necesitemos,
		System.out.println("Introduzca la descripcion");
		String descripci�n = sc.next();
		// carga de datos
		int identificador = 0;
		do {
			seguir = false;
			// Despu�s se tendr� que poner un n�mero identificativo, para poderlo diferenciar entre los demas. Y solo se debe poner
			// n�meros
			try {
				System.out.println("Introduzca el identificador");
				identificador = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo n�meros");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
			
			// Se crear� el extra, con los datos que hayamos indicado previamente
			Extra newExtra = new Extra(identificador,descripci�n);
			extras.add(newExtra);
			newExtra.escribir(extras);
			
			// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
			Submenus(sc);
	}
	
	// Clase p�blica en la que podremos borrar el extra que queramos.
	public static void eliminarExtra(ArrayList<Extra> extras, Scanner sc) throws IOException {
		
		System.out.println("Indica el Id");
		
		// carga de datos
		int id = sc.nextInt();
		boolean encontrado = false;
		
		// Funci�n para poder eliminar el extra que hayamos seleccionado con el identificador indicado. Y este se borrar� correctamente.
		for (int i = 0; i < extras.size(); i++) {
			if (extras.get(i).getId()==id) {
				extras.remove(i);
				Extra delExtra = new Extra();
				delExtra.escribir(extras);
				encontrado = true;
			}
		}

		// En el caso de no haber encontrado la id, se nos mostrar� el siguiente mensaje
		if (encontrado == false) {
			System.out.printf("No existe el extra con el Id %s\n", id);
		}
		
		// Variable para que nos muestre el submen�, despu�s de haber visto el resultado anterior.
		Submenus(sc);
	}		
}
