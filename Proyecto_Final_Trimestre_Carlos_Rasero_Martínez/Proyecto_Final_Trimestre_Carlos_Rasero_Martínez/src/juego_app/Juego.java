package juego_app;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Random;

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
	public final static String LIGHT_GREEN = "\u001B[92m";

	private static final String DB_URL = "jdbc:mysql://localhost:3306/juego";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "1111";

	public static void main(String[] args) {

		JugadorDAO bd = new JugadorDAO();
		HabilidadDAO habilidadDAO = new HabilidadDAO();
		ItemsDAO itemsDAO = new ItemsDAO();

		ArrayList<Jugador> jugadoresDisponibles = cargarJugadores();

		System.out.println("..........................");
		System.out.println(".............");
		System.out.println(".....");
		System.out.println("INICIANDO");
		System.out.println("INICIANDO..");
		System.out.println("INICIANDO.....");
		System.out.println("...................................");
		System.out.println("..................");
		System.out.println("CARGANDO.....");
		System.out.println("YA CASI ESTAMOS ACABANDO..........");
		System.out.println("GRACIAS POR TU PACIENCIA");
		System.out.println("......................");
		System.out.println("............................");
		System.out.println("FINALIZANDO...............");
		System.out.println("FINALIZANDO........");
		System.out.println("FINALIZANDO........");

		// INSERTAR HABILIDADES E ITEMS PARA PROFESOR
		Habilidad[] habilidadesPr = new Habilidad[4];
		habilidadesPr[0] = new Habilidad(1,
				GREEN + "Poner Examen Sorpresa: " + RESET + LIGHT_GREEN + "'Nadie lo ve venir'" + RESET, 25, -3, 0.20);
		habilidadesPr[1] = new Habilidad(2,
				GREEN + "Enfado: " + RESET + LIGHT_GREEN + "'Con razon por no estudiar'" + RESET, 20, -1, 0.30);
		habilidadesPr[2] = new Habilidad(3,
				GREEN + "Usar Moodle: " + RESET + LIGHT_GREEN + "'La usa (pero a nadie le gusta*)'" + RESET, 15, 1,
				0.50);
		habilidadesPr[3] = new Habilidad(4, GREEN + "Salvar: " + RESET + LIGHT_GREEN + "'Pasa de un 4 a un 5'" + RESET,
				20, 3, 0.40);

		for (Habilidad habilidad : habilidadesPr) {
			habilidadDAO.insertarHabilidad(habilidad);
		}
		itemsDAO.insertarItemsPr();

		// INSERTAR HABILIDADES E ITEMS PARA PADRE
		Habilidad[] habilidadesPa = new Habilidad[4];
		habilidadesPa[0] = new Habilidad(1,
				GREEN + "Elocuencia: " + RESET + LIGHT_GREEN + "'Se lo camela básicamente'" + RESET, 20, 0, 0.20);
		habilidadesPa[1] = new Habilidad(2,
				GREEN + "Indignacion: " + RESET + LIGHT_GREEN + "'Se indigna tanto que le dices vale.'" + RESET, 20, 0,
				0.30);
		habilidadesPa[2] = new Habilidad(3,
				GREEN + "Ser Del AMPA: " + RESET + LIGHT_GREEN + "'Prácticamente tiene acciones del colegio'" + RESET,
				25, 3, 0.60);
		habilidadesPa[3] = new Habilidad(4, GREEN + "Justificar a mi hijo: " + RESET + LIGHT_GREEN
				+ "'Mi hijo ese dia estaba malo, lo juro.'" + RESET, 15, -3, 0.30);

		for (Habilidad habilidad : habilidadesPa) {
			habilidadDAO.insertarHabilidad(habilidad);
		}
		itemsDAO.insertarItemsPa();

		// INSERTAR HABILIDADES E ITEMS PARA ALUMNO
		Habilidad[] habilidadesAl = new Habilidad[4];
		habilidadesAl[0] = new Habilidad(1,
				GREEN + "Mentir: " + RESET + LIGHT_GREEN + "Falló la habilidad*('Se mintió a sí mismo')." + RESET, 10,
				-10, 0.50); // 40% probabilidad de crítico
		habilidadesAl[1] = new Habilidad(2, GREEN + "Usar ChatGPT: " + RESET + LIGHT_GREEN
				+ "'Un gran poder conlleva una gran responsabilidad.'" + RESET, 25, -5, 0.20); // 30% probabilidad de
																								// crítico
		habilidadesAl[2] = new Habilidad(3,
				GREEN + "Estudiar: " + RESET + LIGHT_GREEN + "Esta habilidad siempre acierta al objetivo" + RESET, 25,
				20, 0.40); // 65% probabilidad de crítico
		habilidadesAl[3] = new Habilidad(4,
				GREEN + "Faltar: " + RESET + LIGHT_GREEN + "'Hoy me quedo en casita.'" + RESET, 20, 3, 0.30); // 20%
																												// probabilidad
																												// de
																												// crítico

		for (Habilidad habilidad : habilidadesAl) {
			habilidadDAO.insertarHabilidad(habilidad);
		}
		itemsDAO.insertarItemsAl();

		System.out.println("DATOS CARGADOS EXITOSAMENTE");
		System.out.println("GEIM FRIK ESTUDIOS PRESENTA");
		System.out.println("-->POKAMAS<--");

		Scanner sc = new Scanner(System.in);
		int opMenuPrincipal = 0;
		int opMenuJuego = 0;
		int opMenuPersonajes = 0;
		int opMenuAjustes = 0;
		int opSubPersonajes = 0;

		do {
			jugadoresDisponibles = cargarJugadores();
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
							jugarCombate(jugadoresDisponibles.get(0), jugadoresDisponibles.get(3));
						} else if (decisionPr == 2) {
							caminoDerechaPr();
							jugarCombate(jugadoresDisponibles.get(0), jugadoresDisponibles.get(6));
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
								"Estás entrando por la puerta del instituto y te da por saltarte la valla, y de pronto ves entrando al jefe de estudio con el coche.");
						System.out.println("¿Corres a clase y dices que tienes un examen? (" + CYAN
								+ "1 Salir corriendo" + RESET + "/" + RED + "2 Asumir las consecuencias" + RESET + ")");
						int decisionAl = sc.nextInt();

						if (decisionAl == 1) {
							caminoIzquierdaAl();
							jugarCombate(jugadoresDisponibles.get(6), jugadoresDisponibles.get(1));
						} else if (decisionAl == 2) {
							caminoDerechaAl();
							jugarCombate(jugadoresDisponibles.get(6), jugadoresDisponibles.get(25));
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
					int indice1 = random.nextInt(jugadoresDisponibles.size()) + 1; // El +1 ajusta el rango para que vaya desde 1 hasta 20
					int indice2 = random.nextInt(jugadoresDisponibles.size()) + 1;
					jugarCombate(jugadoresDisponibles.get(indice1), jugadoresDisponibles.get(indice2));
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
				System.out.println("5. ITEMS Y HABILIDADES");
				System.out.println("6. VOLVER ATRÁS");
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
					imprimirProfesores();
					imprimirAlumnos();
					imprimirPadres();
					break;
				case 5:
					System.out.println(LIGHT_YELLOW
							+ "-----------------------------------------------------------------------------------"
							+ RESET);
					System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
							+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
					System.out.println("1. HABLIDADES");
					System.out.println("2. ITEMS");
					System.out.println("3. VOLVER ATRÁS");
					System.out.println(LIGHT_YELLOW
							+ "-----------------------------------------------------------------------------------"
							+ RESET);
					opSubPersonajes = sc.nextInt();
						switch (opSubPersonajes) {
						case 1:
							System.out.println(LIGHT_YELLOW
									+ "-----------------------------------------------------------------------------------"
									+ RESET);
							System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
									+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
							System.out.println("1. AGREGAR HABILIDAD");
							System.out.println("2. MODIFICAR HABILIDAD");
							System.out.println("3. ELIMINAR HABILIDAD");
							System.out.println("4. LISTAR HABILIDADES");
							System.out.println("5. VOLVER ATRÁS");
							System.out.println(LIGHT_YELLOW
									+ "-----------------------------------------------------------------------------------"
									+ RESET);
							
							int opSubPersonajeHab = sc.nextInt();
							
							switch (opSubPersonajeHab) {
							case 1:
								habilidadDAO.agregarHabilidad();
								break;
							case 2:
								habilidadDAO.modificarHabilidad();
								break;
							case 3:
								habilidadDAO.eliminarHabilidad();
								break;
							case 4:
								habilidadDAO.mostrarHabilidades();
								break;
							case 5:
								System.out.println("Volviendo atrás");
								break;

							default:
								System.out.println("Opción inválida");
								break;
							}
							break;
						case 2:
							System.out.println(LIGHT_YELLOW
									+ "-----------------------------------------------------------------------------------"
									+ RESET);
							System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET
									+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
							System.out.println("1. AGREGAR ITEM");
							System.out.println("2. MODIFICAR ITEM");
							System.out.println("3. ELIMINAR ITEM");
							System.out.println("4. LISTAR ITEMS");
							System.out.println("5. VOLVER ATRÁS");
							System.out.println(LIGHT_YELLOW
									+ "-----------------------------------------------------------------------------------"
									+ RESET);
							int opSubPersonajesItems = sc.nextInt();
							
							switch (opSubPersonajesItems) {
							case 1:
								itemsDAO.agregarItem();
								break;
							case 2:
								itemsDAO.modificarItem();
								break;
							case 3:
								itemsDAO.eliminarItem();
								break;
							case 4:
								itemsDAO.mostrarItems();
								break;
							case 5:
								System.out.println("Volviendo atrás");
								break;

							default:
								System.out.println("Opción inválida");
								break;
							}
							break;
						case 3:
							System.out.println("Volviendo atrás...");
							break;

						default:
							break;
						}
					break;
				case 6:
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
				System.out.println("1. ACTIVAR DALTONISMO A");
				System.out.println("2. ACTIVAR DALTONISMO B");
				System.out.println("3. ACTIVAR DALTONISMO C");
				System.out.println("4. DESACTIVAR DALTONISMO");
				System.out.println("5. VOLVER ATRÁS");
				System.out.println(LIGHT_YELLOW
						+ "-----------------------------------------------------------------------------------"
						+ RESET);
				opMenuAjustes = sc.nextInt();
				opMenuPrincipal = 0;

				switch (opMenuAjustes) {
				case 1:
					System.setOut(new ColoredPrintStream(System.out, ConsoleColors.PURPLE_BACKGROUND_BRIGHT));
					break;
				case 2:
					System.setOut(new ColoredPrintStream(System.out, ConsoleColors.GREEN_BACKGROUND_BRIGHT));
					break;
				case 3:
					System.setOut(new ColoredPrintStream(System.out, ConsoleColors.RED_BACKGROUND_BRIGHT));
					break;
				case 4:
					System.setOut(new ColoredPrintStream(System.out, ConsoleColors.RESET));
					break;
				case 5:
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
			jugadoresDisponibles.clear();
		} while (opMenuPrincipal != 4);
		sc.close();
	}

//////////////////////////////////////////////////////////
////////      METODOS ESTATICOS COMUNES    //////////////
////////////////////////////////////////////////////////

	private static ArrayList<Jugador> cargarJugadores() {
		ArrayList<Jugador> jugadoresDisponibles = new ArrayList<>();
		Connection connection = null;

		try {
			// Conecta a la base de datos
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			// Carga los jugadores por tipo
			jugadoresDisponibles.addAll(cargarProfesores(connection));
			jugadoresDisponibles.addAll(cargarAlumnos(connection));
			jugadoresDisponibles.addAll(cargarPadres(connection));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cierra la conexión
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return jugadoresDisponibles;
	}

	private static ArrayList<Profesor> cargarProfesores(Connection connection) throws SQLException {
		ArrayList<Profesor> profesores = new ArrayList<>();
		String sql = "SELECT j.id_jugador, j.nombre, j.vida, j.ataque, j.defensa, p.asignatura " + "FROM Jugador j "
				+ "JOIN Profesor p ON j.id_jugador = p.id_jugador";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id_jugador");
				String nombre = resultSet.getString("nombre");
				int vida = resultSet.getInt("vida");
				int ataque = resultSet.getInt("ataque");
				int defensa = resultSet.getInt("defensa");
				String asignatura = resultSet.getString("asignatura");
				profesores.add(new Profesor(id, nombre, vida, ataque, defensa, asignatura));
			}
		}
		return profesores;
	}

	private static ArrayList<Alumno> cargarAlumnos(Connection connection) throws SQLException {
		ArrayList<Alumno> alumnos = new ArrayList<>();
		String sql = "SELECT j.id_jugador, j.nombre, j.vida, j.ataque, j.defensa, a.curso " + "FROM Jugador j "
				+ "JOIN Alumno a ON j.id_jugador = a.id_jugador";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id_jugador");
				String nombre = resultSet.getString("nombre");
				int vida = resultSet.getInt("vida");
				int ataque = resultSet.getInt("ataque");
				int defensa = resultSet.getInt("defensa");
				String curso = resultSet.getString("curso");
				alumnos.add(new Alumno(id, nombre, vida, ataque, defensa, curso));
			}
		}
		return alumnos;
	}

	private static ArrayList<Padre> cargarPadres(Connection connection) throws SQLException {
		ArrayList<Padre> padres = new ArrayList<>();
		String sql = "SELECT j.id_jugador, j.nombre, j.vida, j.ataque, j.defensa, pa.sexo " + "FROM Jugador j "
				+ "JOIN Padre pa ON j.id_jugador = pa.id_jugador";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt("id_jugador");
				String nombre = resultSet.getString("nombre");
				int vida = resultSet.getInt("vida");
				int ataque = resultSet.getInt("ataque");
				int defensa = resultSet.getInt("defensa");
				String sexo = resultSet.getString("sexo");
				padres.add(new Padre(id, nombre, vida, ataque, defensa, sexo));
			}
		}
		return padres;
	}

//	private static ArrayList<Jugador> cargarJugadores() {
//		ArrayList<Jugador> jugadoresDisponibles = new ArrayList<>();
//		// Profesores ya predefinidos
//		jugadoresDisponibles.add(new Profesor(1, "Angelica", 150, 30, 50, "Prog"));
//		jugadoresDisponibles.add(new Profesor(2, "Angelica", 150, 30, 50, "Bdd"));
//		jugadoresDisponibles.add(new Profesor(3, "Angelica", 150, 30, 50, "Ed"));
//		jugadoresDisponibles.add(new Profesor(4, "Gema", 130, 50, 40, "Html"));
//		jugadoresDisponibles.add(new Profesor(5, "Gema", 130, 50, 40, "Si"));
//		jugadoresDisponibles.add(new Profesor(6, "laDeFol", 120, 25, 35, "Fol"));
//
//		// Alumnos ya predefinidos
//		jugadoresDisponibles.add(new Alumno(7, "Carlos MañanaDejoDeFumar", 140, 40, 40, "1 DAM"));
//		jugadoresDisponibles.add(new Alumno(8, "Ale Machaca", 50, 100, 30, "1 DAM"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Ricardo NoFaltes"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Pedro Tengo2Asignaturas"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Manuel Vapesito"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Manuel Cigarrito"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Javi Preguntas"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Ruben Responsable"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Jimmie Teclado"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Jaime Calmado"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Pablo Tranquilito"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Hugo Sobrao'"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Maria Delegada en Funciones"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Caido en combate Pablo"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Legendario Francisco Vallas"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Un tal Alberto"));
//		jugadoresDisponibles.add(agregarJugadorAleatorio("Una tal Lourdes"));
//
//		// Padres ya predefinidos
//		jugadoresDisponibles.add(new Padre(9, "Isabel", 111, 22, 33, "madre"));
//		jugadoresDisponibles.add(new Padre(10, "Jose Luis", 111, 22, 33, "padre"));
//		jugadoresDisponibles.add(new Padre(11, "Maricarmen", 111, 22, 33, "madre"));
//		jugadoresDisponibles.add(new Padre(12, "Francisco", 111, 22, 33, "padre"));
//
//		return jugadoresDisponibles;
//	}

	public static void imprimirListas(ArrayList<Jugador> jugadoresDisponibles) {
		System.out.println("Personajes disponibles:");
		for (Jugador jugador : jugadoresDisponibles) {
			System.out.println(jugador.getNombre() + " " + jugador.getVida() + " " + jugador.getAtaque() + " "
					+ jugador.getDefensa());
		}
	}

	public static void imprimirAlumnos() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			ArrayList<Alumno> alumnos = cargarAlumnos(connection);
			System.out.println("Alumnos:");
			for (Alumno alumno : alumnos) {
				System.out.println("ID: " + alumno.getIdJugador() + ", Nombre: " + alumno.getNombre() + ", Vida: "
						+ alumno.getVida() + ", Ataque: " + alumno.getAtaque() + ", Defensa: " + alumno.getDefensa()
						+ ", Curso: " + alumno.getCurso());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void imprimirPadres() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			ArrayList<Padre> padres = cargarPadres(connection);
			System.out.println("Padres:");
			for (Padre padre : padres) {
				System.out.println("ID: " + padre.getIdJugador() + ", Nombre: " + padre.getNombre() + ", Vida: "
						+ padre.getVida() + ", Ataque: " + padre.getAtaque() + ", Defensa: " + padre.getDefensa()
						+ ", Sexo: " + padre.getSexo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void imprimirProfesores() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			ArrayList<Profesor> profesores = cargarProfesores(connection);
			System.out.println("Profesores:");
			for (Profesor profesor : profesores) {
				System.out.println("ID: " + profesor.getIdJugador() + ", Nombre: " + profesor.getNombre() + ", Vida: "
						+ profesor.getVida() + ", Ataque: " + profesor.getAtaque() + ", Defensa: "
						+ profesor.getDefensa() + ", Asignatura: " + profesor.getAsignatura());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public static void imprimirListas(ArrayList<Jugador> jugadoresDisponibles) {
//		System.out.println("Personajes disponibles:");
//		for (Jugador jugador : jugadoresDisponibles) {
//			System.out.println(jugador.getNombre() + " " + jugador.getVida() + " " + jugador.getAtaque() + " "
//					+ jugador.getDefensa());
//		}
//
//	}

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

//	public static <T extends Jugador> List<T> filtrarYImprimirJugadoresPorTipo(List<Jugador> jugadores, Class<T> tipo) {
//		List<T> listaFiltrada = jugadores.stream().filter(jugador -> tipo.isInstance(jugador))
//				.map(jugador -> tipo.cast(jugador)).collect(Collectors.toList());
//
//		System.out.println(LIGHT_YELLOW + "Jugadores de tipo " + RESET + YELLOW + tipo.getSimpleName() + RESET
//				+ LIGHT_YELLOW + ":" + RESET);
//		listaFiltrada.forEach(System.out::println);
//		System.out.println();
//
//		return listaFiltrada;
//	}
}
