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
		
		Scanner sc = new Scanner(System.in);
		int opMenuPrincipal = 0;
		int opMenuJuego = 0;
		int opMenuPersonajes = 0;
		int opMenuAjustes = 0;
		int opSubPersonajes = 0;

		// ESTO ES UNA CHORRADA
		System.out.println(".....");
		System.out.println("INICIANDO");
		System.out.println("INICIANDO..");
		System.out.println("INICIANDO.....");
		System.out.println("................");
		
		cargarItems();
		
		// ESTO ES UNA CHORRADA
		System.out.println("..................");
		System.out.println("CARGANDO.....");
		System.out.println("YA CASI ESTAMOS ACABANDO..........");
		System.out.println("GRACIAS POR TU PACIENCIA");
		System.out.println("......................");

		cargarHabilidades();
		
		// ESTO ES UNA CHORRADA
		System.out.println("............................");
		System.out.println("FINALIZANDO...............");
		System.out.println("FINALIZANDO........");
		System.out.println("FINALIZANDO........");
		System.out.println("..............");
		System.out.println("DATOS CARGADOS EXITOSAMENTE");
		System.out.println("GEIM FRIK ESTUDIOS PRESENTA");
		System.out.println("-->POKAMAS<--");


		do {
			jugadoresDisponibles = cargarJugadores();
			System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
			System.out.println(LIGHT_YELLOW + "-----------------------------> |" + YELLOW + " POKAMAS " + RESET + LIGHT_YELLOW + "| <---------------------------------------" + RESET);
			System.out.println("1. JUGAR");
			System.out.println("2. PERSONAJES");
			System.out.println("3. AJUSTES");
			System.out.println("4. SALIR");
			System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
			opMenuPrincipal = sc.nextInt();

			switch (opMenuPrincipal) {
			case 1:

				System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET + LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("1. MODO HISTORIA");
				System.out.println("2. COMBATE RAPIDO");
				System.out.println("3. VOLVER ATRAS");
				System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
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
							jugarCombate(jugadoresDisponibles.get(1), jugadoresDisponibles.get(3));
						} else if (decisionPr == 2) {
							caminoDerechaPr();
							jugarCombate(jugadoresDisponibles.get(2), jugadoresDisponibles.get(13));
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
							jugarCombate(jugadoresDisponibles.get(7), jugadoresDisponibles.get(1));
						} else if (decisionAl == 2) {
							caminoDerechaAl();
							jugarCombate(jugadoresDisponibles.get(7), jugadoresDisponibles.get(26));
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
					Random random = new Random();
					int indice1 = random.nextInt(jugadoresDisponibles.size()) + 1;
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
				break;

			case 2:
				System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------" + RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET + LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("1. CREAR JUGADOR");
				System.out.println("2. MODIFICAR JUGADOR");
				System.out.println("3. ELIMINAR JUGADOR");
				System.out.println("4. MOSTRAR JUGADORES");
				System.out.println("5. ITEMS Y HABILIDADES");
				System.out.println("6. VOLVER ATRAS");
				System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------"+ RESET);
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
					System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
					System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET + LIGHT_YELLOW + "| <---------------------------------------" + RESET);
					System.out.println("1. HABLIDADES");
					System.out.println("2. ITEMS");
					System.out.println("3. VOLVER ATRAS");
					System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
					opSubPersonajes = sc.nextInt();
						switch (opSubPersonajes) {
						case 1:
							System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------"+ RESET);
							System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
							System.out.println("1. AGREGAR HABILIDAD");
							System.out.println("2. MODIFICAR HABILIDAD");
							System.out.println("3. ELIMINAR HABILIDAD");
							System.out.println("4. LISTAR HABILIDADES");
							System.out.println("5. VOLVER ATRAS");
							System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------"+ RESET);
							
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
							System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------"+ RESET);
							System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET + LIGHT_YELLOW + "| <---------------------------------------" + RESET);
							System.out.println("1. AGREGAR ITEM");
							System.out.println("2. MODIFICAR ITEM");
							System.out.println("3. ELIMINAR ITEM");
							System.out.println("4. LISTAR ITEMS");
							System.out.println("5. VOLVER ATRAS");
							System.out.println(LIGHT_YELLOW + "-----------------------------------------------------------------------------------" + RESET);
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
					System.out.println("Volviendo atras");
					break;

				default:
					System.out.println("Opción no válida");
					break;
				}
				break;

			case 3:
				System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------"+ RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("1. ACTIVAR DALTONISMO A");
				System.out.println("2. ACTIVAR DALTONISMO B");
				System.out.println("3. ACTIVAR DALTONISMO C");
				System.out.println("4. DESACTIVAR DALTONISMO");
				System.out.println("5. VOLVER ATRAS");
				System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------" + RESET);
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
				System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------"+ RESET);
				System.out.println(LIGHT_YELLOW + "---------------------> |" + YELLOW + " POKAMAS:submenu " + RESET+ LIGHT_YELLOW + "| <---------------------------------------" + RESET);
				System.out.println("¿Que le ha parecido?");
				System.out.println("1. Mejor que Pokemon");
				System.out.println("2. Pues aquí no está Vaporeon");
				System.out.println(LIGHT_YELLOW+ "-----------------------------------------------------------------------------------"+ RESET);
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
	
	/**
	 * Carga las habilidades predefinidas en la base de datos.
	 * Se insertan habilidades para diferentes tipos de jugadores: Profesores, Padres y Alumnos.
	 * Cada habilidad se inserta en la tabla "Habilidad" con su respectivo ID, nombre, poder, penalización de defensa y probabilidad de crítico.
	 * Este método utiliza un lote de inserción para mejorar la eficiencia al realizar múltiples inserciones en la base de datos.
	 * Si ocurre un error durante la carga de las habilidades, se imprime un mensaje de error.
	 */
    public static void cargarHabilidades() {
        String insertSQL = "INSERT INTO Habilidad (id_habilidad, nombre, poder, penalizacion_def, probabilidad_crit) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Habilidades de Profesores
            cargarHabilidad(pstmt, 1, "Elocuencia: Se lo camela básicamente", 20, 0, 0.20);
            cargarHabilidad(pstmt, 2, "Indignacion: Se indigna tanto que le dices vale.", 20, 0, 0.30);
            cargarHabilidad(pstmt, 3, "Ser Del AMPA: Prácticamente tiene acciones del colegio", 25, 3, 0.60);
            cargarHabilidad(pstmt, 4, "Justificar a mi hijo: Mi hijo ese dia estaba malo, lo juro.", 15, -3, 0.30);

            // Habilidades de Padres
            cargarHabilidad(pstmt, 5, "Poner Examen Sorpresa: Nadie lo ve venir", 25, -3, 0.20);
            cargarHabilidad(pstmt, 6, "Enfado: Con razon por no estudiar", 20, -1, 0.30);
            cargarHabilidad(pstmt, 7, "Usar Moodle: La usa (pero a nadie le gusta*)", 15, 1, 0.50);
            cargarHabilidad(pstmt, 8, "Salvar: Pasa de un 4 a un 5", 20, 3, 0.40);
            
            // Habilidades de Alumnos
            cargarHabilidad(pstmt,9, "Mentir: Falló la habilidad*(Se mintió a sí mismo)", 10, -10, 0.50);
            cargarHabilidad(pstmt,10, "Usar ChatGPT: Un gran poder conlleva una gran responsabilidad.", 25, -5, 0.20);
            cargarHabilidad(pstmt,11, "Estudiar: Esta habilidad siempre acierta al objetivo", 25, 20, 0.40);
            cargarHabilidad(pstmt,12, "Faltar: Hoy me quedo en casita", 20, 3, 0.30);

            pstmt.executeBatch();
            System.out.println("Habilidades insertadas exitosamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Método para cargar ítems en la base de datos.
     * Se conecta a la base de datos, inserta los ítems en lotes y los confirma.
     */
    public static void cargarItems() {
        String insertSQL = "INSERT INTO Item (id_item, nombre, poder, mejora_combate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // Habilidades de Padres
            cargarItem(pstmt, 1, "Beber Cerveza: 'Además muy fría'", 25, 20);
            cargarItem(pstmt, 2, "Ticket exlusivo del AMPA: 'Recuperas toda la salud, aumenta tu ataque y defensa mucho, desde ahora todos te respetan.'", 50, 30);
            cargarItem(pstmt, 3, "Tomar una tapita en el bar: 'Además una cerveza'", 25, 30);
            cargarItem(pstmt, 4, "Usa la chancla: 'Se pueden hacer muchas cosas con una chancla'", 15, 20);

            // Habilidades de Profesores
            cargarItem(pstmt, 5, "Beber Café: 'Gracias al cafe, aguanta 3H mas'", 25, 20);
            cargarItem(pstmt, 6, "Ir a la sala de profesores: 'Lugar Oculto*'", 25, 30);
            cargarItem(pstmt, 7, "Poner parte: 'En este instituto no toleramos los saltavallas'", 15, 15);
            cargarItem(pstmt, 8, "Tirar Internet: 'Obliga al jugador a tener que irse, sube mucho la salud'", 55, 30);
            
            // Habilidades de Alumnos
            cargarItem(pstmt, 9, "Tomar Merienda: 'El recreo es sagrado'", 25, 20);
            cargarItem(pstmt, 10, "Fumar: 'Recuperas todas las ganas de volver a clase.'", 50, 30);
            cargarItem(pstmt, 11, "Beber Bebida Energética: 'Te da un Boost a tus 5h de sueño'", 25, 30);
            cargarItem(pstmt, 12, "Usar Cascos: 'La música es poder'", 20, 20);

            pstmt.executeBatch();
            System.out.println("Items insertados exitosamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Agrega una habilidad al lote de inserción para su posterior inserción en la base de datos.
     *
     * @param pstmt            La declaración preparada para la inserción en la base de datos.
     * @param id               El ID de la habilidad.
     * @param nombre           El nombre de la habilidad.
     * @param poder            El poder de la habilidad.
     * @param penalizacionDef  La penalización de defensa de la habilidad.
     * @param probabilidadCrit La probabilidad de crítico de la habilidad.
     * @throws SQLException Si ocurre un error al configurar los parámetros de la declaración preparada.
     */
    private static void cargarHabilidad(PreparedStatement pstmt, int id, String nombre, int poder, int penalizacionDef, double probabilidadCrit) throws SQLException {
        pstmt.setInt(1, id);
        pstmt.setString(2, nombre);
        pstmt.setInt(3, poder);
        pstmt.setInt(4, penalizacionDef);
        pstmt.setDouble(5, probabilidadCrit);
        pstmt.addBatch();
    }
    
    /**
     * Método para cargar un nuevo ítem en la base de datos.
     * 
     * @param pstmt         El PreparedStatement utilizado para ejecutar la inserción.
     * @param id            El ID del ítem.
     * @param nombre        El nombre del ítem.
     * @param poder         El poder del ítem.
     * @param mejoraCombate El nivel de mejora en el combate del ítem.
     * @throws SQLException Si ocurre algún error de SQL durante la inserción.
     */
    private static void cargarItem(PreparedStatement pstmt, int id, String nombre, int poder, int mejoraCombate) throws SQLException {
        pstmt.setInt(1, id);
        pstmt.setString(2, nombre);
        pstmt.setInt(3, poder);
        pstmt.setInt(4, mejoraCombate);
        pstmt.addBatch();
    }

    /**
     * Carga todos los jugadores disponibles desde la base de datos.
     *
     * @return Una lista de todos los jugadores disponibles.
     */
	private static ArrayList<Jugador> cargarJugadores() {
		ArrayList<Jugador> jugadoresDisponibles = new ArrayList<>();
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			jugadoresDisponibles.addAll(cargarProfesores(connection));
			jugadoresDisponibles.addAll(cargarAlumnos(connection));
			jugadoresDisponibles.addAll(cargarPadres(connection));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return jugadoresDisponibles;
	}

	/**
	 * Carga todos los profesores desde la base de datos y los devuelve como una lista.
	 *
	 * @param connection La conexión a la base de datos.
	 * @return Una lista de objetos Profesor cargados desde la base de datos.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
	 */
	private static ArrayList<Profesor> cargarProfesores(Connection connection) throws SQLException {
		// Lista para almacenar los profesores cargados desde la base de datos
		ArrayList<Profesor> profesores = new ArrayList<>();
		
		// Consulta SQL para obtener los datos de los profesores
		String sql = "SELECT j.id_jugador, j.nombre, j.vida, j.ataque, j.defensa, p.asignatura " + "FROM Jugador j "
				+ "JOIN Profesor p ON j.id_jugador = p.id_jugador";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			// Iterar sobre el resultado de la consulta
			while (resultSet.next()) {
				 // Obtener los valores de las columnas de la tabla
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

	/**
	 * Carga todos los alumnos desde la base de datos y los devuelve como una lista.
	 *
	 * @param connection La conexión a la base de datos.
	 * @return Una lista de objetos Alumno cargados desde la base de datos.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
	 */
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

	/**
	 * Carga todos los padres desde la base de datos y los devuelve como una lista.
	 *
	 * @param connection La conexión a la base de datos.
	 * @return Una lista de objetos Padre cargados desde la base de datos.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
	 */
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

	/**
	 * Imprime los detalles de los jugadores disponibles en la lista proporcionada.
	 *
	 * @param jugadoresDisponibles La lista de jugadores disponibles.
	 */
	public static void imprimirListas(ArrayList<Jugador> jugadoresDisponibles) {
		System.out.println("Personajes disponibles:");
		for (Jugador jugador : jugadoresDisponibles) {
			System.out.println(jugador.getNombre() + " " + jugador.getVida() + " " + jugador.getAtaque() + " "
					+ jugador.getDefensa());
		}
	}

	/**
	 * Imprime los detalles de todos los alumnos almacenados en la base de datos.
	 * Se conecta a la base de datos, carga la lista de alumnos y luego imprime los detalles de cada uno.
	 */
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

	/**
	 * Imprime los detalles de todos los padres almacenados en la base de datos.
	 * Se conecta a la base de datos, carga la lista de padres y luego imprime los detalles de cada uno.
	 */
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

	/**
	 * Imprime los detalles de todos los profesores almacenados en la base de datos.
	 * Se conecta a la base de datos, carga la lista de profesores y luego imprime los detalles de cada uno.
	 */
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

	/**
	 * Crea y devuelve un jugador aleatorio con el nombre proporcionado.
	 * Genera valores aleatorios para el ID, vida, ataque y defensa del jugador.
	 *
	 * @param nombre El nombre del jugador.
	 * @return Un objeto de tipo Jugador creado aleatoriamente.
	 */
	public static Jugador agregarJugadorAleatorio(String nombre) {
		int idAleatoria = generarNumeroAleatorio(0, 110);
		int vidaAleatoria = generarNumeroAleatorio(80, 120);
		int atqAleatoria = generarNumeroAleatorio(25, 35);
		int defAleatoria = generarNumeroAleatorio(25, 35);

		return new Alumno(idAleatoria, nombre, vidaAleatoria, atqAleatoria, defAleatoria, "1 DAM");
	}

	/**
	 * Genera un número entero aleatorio dentro del rango especificado.
	 *
	 * @param min El valor mínimo del rango (incluido).
	 * @param max El valor máximo del rango (incluido).
	 * @return Un número aleatorio dentro del rango especificado.
	 */
	public static int generarNumeroAleatorio(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	/**
	 * Simula un combate por turnos entre dos jugadores, mostrando el progreso de la batalla en la consola.
	 *
	 * @param jugador1 El primer jugador que participa en el combate.
	 * @param jugador2 El segundo jugador que participa en el combate.
	 */
	public static void jugarCombate(Jugador jugador1, Jugador jugador2) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int ronda = 1;

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

	/**
	 * Muestra una representación visual de la vida del jugador en forma de una barra de vida en la consola.
	 * La barra de vida muestra el porcentaje de vida actual del jugador.
	 *
	 * @param jugador El jugador del que se mostrará la barra de vida.
	 */
	public static void mostrarBarraVida(Jugador jugador) {
		int vidaActual = jugador.getVida();
		int vidaTotal = 100;
		int porcentajeVida = vidaActual * 100 / vidaTotal;

		System.out.print("HP: ");
		for (int i = 0; i < porcentajeVida / 3; i++) {
			System.out.print("|");
		}
		System.out.println(" " + porcentajeVida + "%");
	}

	/**
	 * Simula el camino de decisión del jugador cuando elige la opción de seguir corrigiendo exámenes.
	 * Imprime mensajes que describen la situación y comienza un combate con un profesor enfadado.
	 */
	public static void caminoIzquierdaPr() {
		System.out.println("Has decidido hacerte el loco y seguir corriguiendo exámenes.");
		System.out.println("Parece que entra un profesor enfadado contigo por poner la fecha del examen el mismo dia...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	/**
	 * Simula el camino de decisión del jugador cuando elige la opción de resolver una duda.
	 * Imprime mensajes que describen la situación y comienza un combate con un alumno con dudas.
	 */
	public static void caminoDerechaPr() {
		System.out.println("Has decidido resolver la duda y todo sale genial, ya no tiene dudas.");
		System.out.println("Sin darte cuenta se te ha ido la hora del café y tienes otro alumno delante tuya con dudas...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	/**
	 * Simula el camino de decisión del jugador cuando elige la opción de salir corriendo.
	 * Imprime mensajes que describen la situación y comienza un combate con un profesor que acaba de adquirir su café.
	 */
	public static void caminoIzquierdaAl() {
		System.out.println("Has decidido salir por patas y hacerte el loco.");
		System.out.println("Corriendo sin darte cuenta te chocas con un profesor que justo acababa de adquirir su café...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	}

	/**
	 * Simula el camino de decisión del jugador cuando elige la opción de asumir el parte.
	 * Imprime mensajes que describen la situación y comienza un combate con su padre, quien se entera de la situación y se enfada.
	 */
	public static void caminoDerechaAl() {
		System.out.println("Has decidido asumir el parte, ya que te ha visto.");
		System.out.println("Aparentemente no iba a pasar nada, hasta que tu padre se entera de que perderás la beca 6000 por hacer el gilipollas...");
		System.out.println("..");
		System.out.println(".");
		System.out.println("COMIENZA EL COMBATE");
	} 

}
