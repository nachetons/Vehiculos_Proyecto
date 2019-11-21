package Presentación;

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
		boolean contraseña_correcta = false;
		boolean diurno = false;
		boolean nocturno = false;
		boolean sesioner = false;
		int usuario_cod;int i;
		int usuario_nivel = 0;
		String usuario_turno;
		String usuario_pass;
		String usuario_nom = null;
		double usuario_prod = 0;
		Login(sc, empl, usuario_correcto, contraseña_correcta);
		
		// Aparición del Menú principal.
		boolean seguir = true;
		do {
			try {
				System.out.println("Menú");
				System.out.println("1. Mostrar todas los vehículos");
				System.out.println("2. Buscar un vehículo");
				System.out.println("3. Añadir un vehículo");
				System.out.println("4. Modificar un vehículo");
				System.out.println("5. Eliminar un vehículo");
				System.out.println("6. Mostrar todos los extras");
				System.out.println("7. Añadir extra");
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
					añadirVehiculo(turismos,camiones, extras,sc);
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
					añadirExtra(extras, sc);
					break;
				case 8:
					eliminarExtra(extras, sc);
					break;
				case 9:
					seguir = false;
					System.out.println("Hasta pronto");
					Login(sc, empl, usuario_correcto, contraseña_correcta);
					seguir = true;
					break;
				default:
					System.out.println("Introduce un número de 1 a 9");
				}

			} catch (InputMismatchException e) {
				System.err.println("Introduce un número");
				sc.nextLine();
			}
	
		} while (seguir);
	}
	
	
	//Clase privada, en la cual antés de llegar al menú, se tiene que ingresar un código de usuario y una contraseña.
	private static void Login(Scanner sc, ArrayList<Empleado> empl, boolean usuario_correcto,boolean contraseña_correcta) {
		
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
				System.out.println("Introduce su contraseña: ");
				usuario_pass = sc.next();
				for (i = 0; i < empl.size(); i++) {
					if (i < empl.size()) {
						if (usuario_cod == empl.get(i).getCodigoAcceso()) {
							usuario_correcto = true;
							if (usuario_pass.equals(empl.get(i).getPassword())) {
								contraseña_correcta = true;
								sesioner = true;
								usuario_nom = empl.get(i).getNombreUsuario();
								diurno = true;
								i = empl.size();
							}
						}				
					}else {
						
					}
				}
			
			// Se ponen las excepciones. Si se falla en el usuario o en la contraseña, se vuelve a preguntar el login, y te indicará
			// el error que se ha tenido.
			try {
				if (usuario_correcto == false)
					throw new excepcionusuario();
			
				if (contraseña_correcta == false)
					throw new excepcioncon();
		
			} catch (excepcionusuario e) {
				System.err.println("Usuario incorrecto");
				sc.nextLine();	
			} catch (excepcioncon e) {
				System.err.println("Contraseña incorrecta");
				sc.nextLine();
				
			// La variable sesioner, permitirá la aparición del menú. Cuando se haya introducido los datos del login correctamente,
			// esta variable se activará, y te mostrará el menú correctamente. Mientras tanto, esta no funcionará, si se han intro-
			// -ducido los datos erroneamente, con lo cual este no se activará.
			}
			} while (sesioner==false);
		}	
	
	//Clase privada, después de ver el resultado de una de las opciones que hayamos seleccionado en el menú, nos saldrá un submenú,
	// nos indicará si queremos volver al menú, o si queremos salir de la aplicación.
	private static void Submenus(Scanner sc) {
		//Se cargan los datos
		boolean seguir;
		int opcion = 0;
		do {
		try {
			System.out.println("");
			System.out.println("");
			System.out.println("Menú");
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
		// Si se pusiese en vez de un número una letra, saltaría la excepción, solo debemos introducir números.
		} catch (InputMismatchException e) {
			System.err.println("Introduce un número");
			sc.nextLine();
		}
		} while (opcion>2);
	}
	
	
	// Clase publica, una de las opciones del menú, para visualizar todos los vehículos que tengamos en nuestro fichero de "Turismos.txt",
	//  y de "Camiones.txt".
	public static void mostrarTodos(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones) {
		
		System.out.println("Camiones");
		
		//Carga de datos
		Scanner sc = new Scanner(System.in);
		boolean seguir = false;
		boolean sesioner = false;
		
		//Se muestra toda la información de los camiones y de los turismos. Indicando las variables que tengamos declaradas en cada
		// una de las clases de turismos y camiones (y extras, ya que esta relacionado con turismos.).
		for (int i = 0; i < camiones.size(); i++) {
			System.out.println(camiones.get(i).toString());
		}
		System.out.println("Turismos");
		for (int i = 0; i < turismos.size(); i++) {
			System.out.println(turismos.get(i).toString());
		}
		
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);
	}
	
	//Clase publica, se busca el vehículo que nosotros indiquemos con la matrícula.
	public static void buscarVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, Extra extra, Scanner sc) {
		
		System.out.println("Indica la matrícula");
		String Matricula = sc.next();
		
		//Carga de datos
		boolean encontrado = false;
		
		//Nos buscará la matrícula que hayamos escrito, y se imprimirá el vehículo seleccionado, con todos sus datos.
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
		
		//En el caso de no haber encontrado el vehículo, nos mostrará un mensaje, indicando que no se ha encontrado el vehículo.
		if (encontrado == false) {
			System.out.printf("No existe el vehículo con la matrícula %s\n", Matricula);

		}
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);
		
	}
	
	//Clase pública para añadir un vehículo
	public static void añadirVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, ArrayList<Extra> extras, Scanner sc) throws IOException {
		
		//Carga de datos.
		boolean seguir = false;
		String Matricula = "";
		
		//Primero debemos introducir la mátricula, en el caso de haber introducido una matrícula repetida, nos volverá a preguntar
		//la mátrícula, en caso contrario, seguiremos con la siguiente pregunta.
		do {
			seguir = false;
			System.out.println("Introduzca la Matrícula");
			Matricula = sc.next();
			for (int i = 0; i < camiones.size(); i++) {
				if (camiones.get(i).getMatricula().equals(Matricula)) {
					System.out.println("Matrícula repetido");
					seguir = true;
				}
			}
			for (int i = 0; i < turismos.size(); i++) {
				if (turismos.get(i).getMatricula().equals(Matricula)) {
					System.out.println("Matrícula repetida");
					seguir = true;
				}
			}

		} while (seguir);
		
		// Después, se debe introducir la marca, el modelo y color.
		System.out.println("Introduzca la marca");
		String marca = sc.next();
		System.out.println("Introduzca el modelo");
		String modelo = sc.next();
		System.out.println("Introduzca el color");
		String color = sc.next();
		// Carga de datos
		int precio = 0;
		
		// Y a continuación se pedirá el precio en números. Si se pone letras, te volverá a preguntar el precio, y además, te dirá
		// que solo debes introducir números
		do {
			seguir = false;
			try {
				System.out.println("Introduzca el precio");
				precio = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
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
		System.out.println("¿Es un camion 1 o un turismo 2?");
		tipo=sc.nextInt();
		
		if(tipo==1) {
			// Este caso es el de camión, se deberá introducir la capacidad de carga en números, o sino te volverá a preguntar
			// la cantidad, y te indicará que solo deberás introducir números.
			do {
				seguir = false;
				try {
		
					System.out.println("Introduzca la capacidad de carga");
					capacidad_carga = sc.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			
			// Y con todos los datos ingresados, se creará el nuevo camion, con los datos que hayamos escrito.
			Vehiculo newVehiculo = new Camiones(Matricula, marca, modelo, color, precio, capacidad_carga);
			camiones.add(newVehiculo);
			newVehiculo.escribir(camiones);
			
		}else if(tipo==2){
			// Esta es la segunda opción, si hemos indicado de crear un turismo, nos pedirá el número de puertas con un número,
			// y después debemos introducir el extra, en la cual nos pondrán los extras que estén disponibles.
			int n_puertas = 0;
			Extra = 0;
			System.out.println("Introduzca el número de puertas");
			n_puertas= sc.nextInt();
			do {
				seguir = false;
				try {
					
					//Si se pone un 0, eso querrá decir que no queremos ningún extra. Y aqui se debe introducir números, no letras.
					System.out.println("Introduzca el extra");
					sc.nextLine();
					System.out.println("1. Lunas pintadas");
					System.out.println("2. Ruedas última generación");
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
					System.err.println("Introduzce solo números");
					seguir = true;
					sc.nextLine();
				}
			} while (seguir);
			
			// Y despùés se creará el turismo, con los datos que hayamos escrito.
			Vehiculo newVehiculo = new Turismo(Matricula, modelo, marca, color, precio, extra, n_puertas);
			turismos.add(newVehiculo);
			newVehiculo.escribir(turismos);
		}
		}while(tipo<1 || tipo >2);
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);

	}

	// Clase publica, para modificar un vehiculo que este previamente creado, y podemos buscar el vehículo, introduciendo la matrícula.
	// Si se ha encontrado el vehículo, nos mostrará un menú, indicando que es lo que debemos modificar. Si no se ha encontrado la
	// matrícula, nos volverá a preguntar la mátricula, y nos indicará que no se ha encontrado el vehículo con la matrícula correspondiente.
	public static void modificarVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, ArrayList<Extra> extra, Scanner sc) throws IOException {
		
		System.out.println("Indica la matrícula");
		String matricula = sc.next();
		boolean encontrado = false;
		
		// Este es el caso de los camiones, lo único que cambiará en comparación de los turismos, es que en uno de podrá cambiar la
		// capacidad de carga (Camiones), y en otro se puede cambiar el extra (En Turismos).
		for (int i = 0; i < camiones.size(); i++) {
			if (camiones.get(i).getMatricula().equals(matricula)) {
				System.out.println("¿Qué deseas modificar?");
				System.out.println("1. Matrícula");
				System.out.println("2. Marca");
				System.out.println("3. Modelo");
				System.out.println("4. Color");
				System.out.println("5. Capacidad de carga");
				System.out.println("6. Precio");
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Introduzca una nueva matrícula");
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
							System.err.println("Introduzce solo números");
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
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					break;
				}
				// Se nos habrá modificado el camión que hayamos seleccionado previamente
				encontrado = true;
				Vehiculo modVehiculo = new Camiones();
				modVehiculo.escribir(camiones);
			}
		}
		
		// Lo mismo, pero en turismos. Pero indicando extras, y no capacidad de carga.
		for (int i = 0; i < turismos.size(); i++) {
			if (turismos.get(i).getMatricula().equals(matricula)) {
				System.out.println("¿Qué deseas modificar?");
				System.out.println("1. Matrícula");
				System.out.println("2. Marca");
				System.out.println("3. Modelo");
				System.out.println("4. Color");
				System.out.println("5. Nº de puertas");
				System.out.println("6. Beca");
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Introduzca una nueva matrícula");
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
							System.out.println("Introduce el múmero de puertas");
							int nºpuertas = sc.nextInt();
							((Turismo)turismos.get(i)).setN_puertas(nºpuertas);
							break;
						} catch (InputMismatchException e) {
							System.err.println("Introduzce solo números");
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
							System.err.println("Introduzce solo números");
							sc.nextLine();
							seguir = true;
						}
					} while (seguir);
					break;
				}
				// Se nos habrá modificado el camión que hayamos seleccionado previamente
				encontrado = true;
				Vehiculo modVehiculo = new Turismo();
				modVehiculo.escribir(turismos);
			}
		}
		
		//Es el caso de no haber encontrado la matricula escrita, nos indicará este texto.
		if (encontrado == false) {
			System.out.printf("No existe el vehiculo con la matrícula %s\n", matricula);

		}
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);

	}
	
	
	// Clase pública en la que podremos borrar el vehiculo que queramos.
	public static void eliminarVehiculo(ArrayList<Vehiculo> turismos, ArrayList<Vehiculo> camiones, Scanner sc) throws IOException{
		
		// Primero debemos indicar la matrícula del vehiculo que queramos borrar.
		System.out.println("Indique la matrícula del vehículo");
		String Matricula = sc.next();
		
		// carga de datos
		boolean encontrado = false;
		
		// Te buscará si ese vehículo existe. En el caso de exista, se borrará automáticamente. Este es en el apartado de camiones.
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
		
		//Si no se ha encontrado el vehículo, nos mostrará este mensaje.
		if (encontrado == false) {
			System.out.printf("No existe el vehículo con la matrícula %s\n", Matricula);
			
		}
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);
	}
	
	// Clase pública, en la cual podremos ver todos los extras que tengamos creados de los turismos.
	public static void mostrarExtras(ArrayList<Extra> extras, Scanner sc) {
		System.out.println("Extras");
		
		// Función en la cual mostrará los extras.
		for (int i = 0; i < extras.size(); i++) {
			System.out.println(extras.get(i).toString());
		}
		
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);
		
	}
	
	// Clase pública para poder crear un extra.
	public static void añadirExtra(ArrayList<Extra> extras, Scanner sc) throws IOException {
		
		// carga de datos
		boolean seguir = false;
		// primero debemos crear la descripción del extra que nosotros necesitemos,
		System.out.println("Introduzca la descripcion");
		String descripción = sc.next();
		// carga de datos
		int identificador = 0;
		do {
			seguir = false;
			// Después se tendrá que poner un número identificativo, para poderlo diferenciar entre los demas. Y solo se debe poner
			// números
			try {
				System.out.println("Introduzca el identificador");
				identificador = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Introduzce solo números");
				seguir = true;
				sc.nextLine();
			}
		} while (seguir);
			
			// Se creará el extra, con los datos que hayamos indicado previamente
			Extra newExtra = new Extra(identificador,descripción);
			extras.add(newExtra);
			newExtra.escribir(extras);
			
			// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
			Submenus(sc);
	}
	
	// Clase pública en la que podremos borrar el extra que queramos.
	public static void eliminarExtra(ArrayList<Extra> extras, Scanner sc) throws IOException {
		
		System.out.println("Indica el Id");
		
		// carga de datos
		int id = sc.nextInt();
		boolean encontrado = false;
		
		// Función para poder eliminar el extra que hayamos seleccionado con el identificador indicado. Y este se borrará correctamente.
		for (int i = 0; i < extras.size(); i++) {
			if (extras.get(i).getId()==id) {
				extras.remove(i);
				Extra delExtra = new Extra();
				delExtra.escribir(extras);
				encontrado = true;
			}
		}

		// En el caso de no haber encontrado la id, se nos mostrará el siguiente mensaje
		if (encontrado == false) {
			System.out.printf("No existe el extra con el Id %s\n", id);
		}
		
		// Variable para que nos muestre el submenú, después de haber visto el resultado anterior.
		Submenus(sc);
	}		
}
