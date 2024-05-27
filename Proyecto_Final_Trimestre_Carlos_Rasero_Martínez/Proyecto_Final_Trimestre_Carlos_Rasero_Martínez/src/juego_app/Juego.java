package juego_app;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Juego {

	// Colores ANSI
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";
	public static final String LIGHT_YELLOW = "\u001B[93m";

	public static void main(String[] args) {

		JugadorDAO bd = new JugadorDAO();
		ArrayList<Jugador> jugadoresDisponibles = cargarJugadores();

		Scanner sc = new Scanner(System.in);
		int opMenuPrincipal = 0;
		int opMenuJuego = 0;
		int opMenuPersonajes = 0;
		int opMenuAjustes = 0;

		do {
			System.out.println(LIGHT_YELLOW
					+ "-----------------------------------------------------------------------------------" + RESET);
			System.out.println(LIGHT_YELLOW + "-----------------------------> |" + YELLOW + " POKAMAS " + RESET
					+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
			System.out.println("1. JUGAR");
			System.out.println("2. PERSONAJES");
			System.out.println("3. AJUSTES");
			System.out.println("4. SALIR");
			System.out.println(LIGHT_YELLOW
					+ "-----------------------------------------------------------------------------------" + RESET);
			opMenuPrincipal = sc.nextInt();

			switch (opMenuPrincipal) {
			case 1:
//				do {
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
						+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("1. MODO HISTORIA");
				System.out.println("2. COMBATE RÁPIDO");
				System.out.println("3. VOLVER ATRÁS");
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				opMenuJuego = sc.nextInt();

				switch (opMenuJuego) {
				case 1:
					opMenuJuego = 0;
					System.out.println("Comienza la aventura...");
					System.out.println("Por favor elija que camino desea escoger...");
					System.out.println("1. Profesor: Son poseedores del conocimiento y la experiencia");
					System.out.println("2. Alumno: Maestros del sigilo y sacar el curso");
					System.out.println("3. Padre: Destacan por su fuerza y su paciencia");
					int op = sc.nextInt();

					switch (op) {
					case 1:
						op = 0;
						sc = new Scanner(System.in);
						System.out.println();
						System.out.println();
						System.out.println("¡Bienvenido a la aventura de ser un Profesor!");
						System.out.println();
						System.out.println(
								"Estás caminando por la sala de profesores y ves a lo lejos a Javi, que quiere preguntarte una duda.");
						System.out.println("¿Cierras la puerta y haces como que no le has visto? (" + CYAN
								+ "1 para no ayudar" + RESET + "/" + RED + "2 para ayudar" + RESET + ")");
						int decisionPr = sc.nextInt();

						if (decisionPr == 1) {
							caminoIzquierdaPr();
							jugarCombate(jugadoresDisponibles.get(0), jugadoresDisponibles.get(2));
							jugadoresDisponibles.clear();
							jugadoresDisponibles = cargarJugadores();
						} else if (decisionPr == 2) {
							caminoDerechaPr();
							jugarCombate(jugadoresDisponibles.get(0), jugadoresDisponibles.get(6));
							jugadoresDisponibles.clear();
							jugadoresDisponibles = cargarJugadores();
						} else {
							System.out.println("Por favor, ingresa una respuesta válida (1/2).");
						}
						break;
					case 2:
						System.out.println();
						System.out.println();
						System.out.println("¡Bienvenido a la aventura de ser un Alumno!");
						System.out.println();
						System.out.println(
								"Estás entrando por la puerta del instituto y te da por saltarte la puerta, y de pronto ves entrando al jefe de estudio con el coche.");
						System.out.println("¿Corres a clase y dices que tienes un examen? (" + CYAN
								+ "1 Salir corriendo" + RESET + "/" + RED + "2 Asumir las consecuencias" + RESET + ")");
						int decisionAl = sc.nextInt();

						if (decisionAl == 1) {
							caminoIzquierdaAl();
							jugarCombate(jugadoresDisponibles.get(6), jugadoresDisponibles.get(1));
							jugadoresDisponibles.clear();
							jugadoresDisponibles = cargarJugadores();
							cargarJugadores();
						} else if (decisionAl == 2) {
							caminoDerechaAl();
							jugarCombate(jugadoresDisponibles.get(6), jugadoresDisponibles.get(25));
							jugadoresDisponibles.clear();
							jugadoresDisponibles = cargarJugadores();
							cargarJugadores();
						} else {
							System.out.println("Por favor, ingresa una respuesta válida (1/2).");
						}

						break;
					case 3:
						System.out.println("Próximamente como DLC");
					default:
						System.out.println("Opción no válida");
						break;
					}
					break;
				case 2:
					// Obtener dos índices aleatorios entre 1 y 20
					Random random = new Random();
					int indice1 = random.nextInt(20) + 1; // El +1 ajusta el rango para que vaya desde 1 hasta 20
					int indice2 = random.nextInt(20) + 1;
					jugarCombate(jugadoresDisponibles.get(indice1), jugadoresDisponibles.get(indice2));
					jugadoresDisponibles.clear();
					jugadoresDisponibles = cargarJugadores();
					break;
				case 3:
					System.out.println("Volviendo atrás");
					break;

				default:
					System.out.println("Opción no válida");
					break;
				}
//				} while (opMenuJuego != 3);

				break;

			case 2:
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
						+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("1. CREAR JUGADOR");
				System.out.println("2. MODIFICAR JUGADOR");
				System.out.println("3. ELIMINAR JUGADOR");
				System.out.println("4. MOSTRAR JUGADORES");
				System.out.println("5. VOLVER ATRÁS");
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				opMenuPersonajes = sc.nextInt();

				switch (opMenuPersonajes) {
				case 1:
					bd.agregarJugador();
					break;
				case 2:
					bd.modificarJugador();
					break;
				case 3:
					bd.eliminarJugador();
					break;
				case 4:
					filtrarYImprimirJugadoresPorTipo(jugadoresDisponibles, Profesor.class);
					filtrarYImprimirJugadoresPorTipo(jugadoresDisponibles, Padre.class);
					filtrarYImprimirJugadoresPorTipo(jugadoresDisponibles, Alumno.class);
					break;
				case 5:
					System.out.println("Volviendo atrás");
					break;

				default:
					System.out.println("Opción no válida");
					break;
				}
				break;

			case 3:
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
						+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("1. ACTIVAR DALTONISMO");
				System.out.println("2. DESACTIVAR DALTONISMO");
				System.out.println("3. VOLVER ATRÁS");
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				opMenuAjustes = sc.nextInt();
				opMenuPrincipal = 0;

				switch (opMenuAjustes) {
				case 1:

					break;
				case 2:

					break;
				case 3:
					System.out.println("Volviendo atrás");
					break;

				default:
					System.out.println("Opción no válida");
					break;
				}
				break;

			case 4:
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
						+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("¿Que le ha parecido?");
				System.out.println("1. Mejor que Pokemon");
				System.out.println("2. Pues aquí no está Vaporeon");
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				opMenuJuego = sc.nextInt();
				System.out.println("Finalizando programa... (Sleep 3)");
				break;
			default:
				System.out.println("Acción inválida");
				break;
			}
		} while (opMenuPrincipal != 4);
		sc.close();
	}

//////////////////////////////////////////////////////////
////////      METODOS ESTATICOS COMUNES    //////////////
////////////////////////////////////////////////////////

	private static ArrayList<Jugador> cargarJugadores() {
		ArrayList<Jugador> jugadoresDisponibles = new ArrayList<>();
		// Profesores ya predefinidos
		jugadoresDisponibles.add(new Profesor(1, "Angelica", 150, 30, 50, "Prog"));
		jugadoresDisponibles.add(new Profesor(2, "Angelica", 150, 30, 50, "Bdd"));
		jugadoresDisponibles.add(new Profesor(3, "Angelica", 150, 30, 50, "Ed"));
		jugadoresDisponibles.add(new Profesor(4, "Gema", 130, 50, 40, "Html"));
		jugadoresDisponibles.add(new Profesor(5, "Gema", 130, 50, 40, "Si"));
		jugadoresDisponibles.add(new Profesor(6, "laDeFol", 120, 25, 35, "Fol"));

		// Alumnos ya predefinidos
		jugadoresDisponibles.add(new Alumno(7, "Carlos MañanaDejoDeFumar", 140, 40, 40, "1 DAM"));
		jugadoresDisponibles.add(new Alumno(8, "Ale Machaca", 50, 100, 30, "1 DAM"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Ricardo NoFaltes"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Pedro Tengo2Asignaturas"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Manuel Vapesito"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Manuel Cigarrito"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Javi Preguntas"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Ruben Responsable"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Jimmie Teclado"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Jaime Calmado"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Pablo Tranquilito"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Hugo Sobrao'"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Maria Delegada en Funciones"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Caido en combate Pablo"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Legendario Francisco Vallas"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Un tal Alberto"));
		jugadoresDisponibles.add(agregarJugadorAleatorio("Una tal Lourdes"));

		// Padres ya predefinidos
		jugadoresDisponibles.add(new Padre(9, "Isabel", 111, 22, 33, "madre"));
		jugadoresDisponibles.add(new Padre(10, "Jose Luis", 111, 22, 33, "padre"));
		jugadoresDisponibles.add(new Padre(11, "Maricarmen", 111, 22, 33, "madre"));
		jugadoresDisponibles.add(new Padre(12, "Francisco", 111, 22, 33, "padre"));

		return jugadoresDisponibles;
	}

	public static void imprimirListas(ArrayList<Jugador> jugadoresDisponibles) {
		System.out.println("Personajes disponibles:");
		for (Jugador jugador : jugadoresDisponibles) {
			System.out.println(jugador.getNombre() + " " + jugador.getVida() + " " + jugador.getAtaque() + " "
					+ jugador.getDefensa());
		}

	}

	public static Jugador agregarJugadorAleatorio(String nombre) {
		int idAleatoria = generarNumeroAleatorio(0, 110);
		int vidaAleatoria = generarNumeroAleatorio(80, 120);
		int atqAleatoria = generarNumeroAleatorio(25, 35);
		int defAleatoria = generarNumeroAleatorio(25, 35);

		return new Alumno(idAleatoria, nombre, vidaAleatoria, atqAleatoria, defAleatoria, "1 DAM");
	}

	public static int generarNumeroAleatorio(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	public static void jugarCombate(Jugador jugador1, Jugador jugador2) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int ronda = 1; // Contador de rondas

		// Bucle para simular turnos hasta que uno de los jugadores muera
		while (jugador1.getVida() > 0 && jugador2.getVida() > 0) {
			// Turno del jugador 1
			System.out.println("Turno " + ronda + ":");
			mostrarBarraVida(jugador1);
			jugador1.atacar(jugador2);
			System.out.println("");
			if (jugador2.getVida() <= 0) {
				System.out.println(jugador1.getNombre() + " ha ganado la partida.");
				return;
			}
			System.out.println("Presiona Enter para continuar...");
			scanner.nextLine();

			// Turno del jugador 2
			System.out.println("Turno " + ronda + ":");
			mostrarBarraVida(jugador2);
			jugador2.atacar(jugador1);
			System.out.println("");
			if (jugador1.getVida() <= 0) {
				System.out.println(jugador2.getNombre() + " ha ganado la partida.");
				return;
			}
			System.out.println("Presiona Enter para continuar...");
			scanner.nextLine();
			ronda++;
		}
	}

	public static void mostrarBarraVida(Jugador jugador) {
		int vidaActual = jugador.getVida();
		int vidaTotal = 100; // Ahora la vida total es 50
		int porcentajeVida = vidaActual * 100 / vidaTotal;

		System.out.print("HP: ");
		for (int i = 0; i < porcentajeVida / 3; i++) {
			System.out.print("|");
		}
		System.out.println(" " + porcentajeVida + "%");
	}

	public static void caminoIzquierdaPr() {
		System.out.println("Has decidido hacerte el loco y seguir corriguiendo exámenes.");
		System.out
				.println("Parece que entra un profesor enfadado contigo por poner la fecha del examen el mismo dia...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	public static void caminoDerechaPr() {
		System.out.println("Has decidido resolver la duda y todo sale genial, ya no tiene dudas.");
		System.out.println(
				"Sin darte cuenta se te ha ido la hora del café y tienes otro alumno delante tuya con dudas...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	public static void caminoIzquierdaAl() {
		System.out.println("Has decidido salir por patas y hacerte el loco.");
		System.out.println(
				"Corriendo sin darte cuenta te chocas con un profesor que justo acababa de adquirir su café...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	public static void caminoDerechaAl() {
		System.out.println("Has decidido asumir el parte ya que te ha visto.");
		System.out.println(
				"Aparentemente no iba a pasar nada, hasta que tu padre se entera de que perderás la beca 6000 por hacer el gilipollas...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	public static <T extends Jugador> List<T> filtrarYImprimirJugadoresPorTipo(List<Jugador> jugadores, Class<T> tipo) {
		List<T> listaFiltrada = jugadores.stream().filter(jugador -> tipo.isInstance(jugador))
				.map(jugador -> tipo.cast(jugador)).collect(Collectors.toList());

		System.out.println(LIGHT_YELLOW + "Jugadores de tipo " + RESET + YELLOW + tipo.getSimpleName() + RESET
				+ LIGHT_YELLOW + ":" + RESET);
		listaFiltrada.forEach(System.out::println);
		System.out.println();

		return listaFiltrada;
	}
}
