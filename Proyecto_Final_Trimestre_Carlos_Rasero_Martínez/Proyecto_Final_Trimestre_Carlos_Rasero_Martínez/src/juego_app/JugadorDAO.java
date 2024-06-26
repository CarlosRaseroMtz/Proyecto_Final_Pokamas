package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class JugadorDAO {
	private Connection conexion;

	private final String USUARIO = "root";
	private final String PASSWORD = "1111";
	private final String MAQUINA = "localhost";
	private final String BD = "juego";

	private final Scanner sc = new Scanner(System.in);

	/**
	 * Establece una conexión a la base de datos utilizando JDBC.
	 * 
	 * @return La conexión establecida o null si no se pudo conectar.
	 */
	private Connection conectar() {
		Connection con = null;
		String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
		try {
			con = DriverManager.getConnection(url, USUARIO, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Error al conectar al SGBD: " + e.getMessage());
		}
		return con;
	}

	/**
	 * Agrega un nuevo jugador a la base de datos y selecciona su rol.
	 * 
	 * Se solicita al usuario que ingrese el nombre, la vida, el ataque y la defensa del jugador.
	 * Luego se inserta esta información en la base de datos y se solicita al usuario que seleccione el rol del jugador:
	 *   - Alumno
	 *   - Profesor
	 *   - Padre
	 * Dependiendo del rol seleccionado, se crea un nuevo registro en la tabla correspondiente.
	 */
	public void agregarJugador() {
		conexion = conectar();
		long generatedId = 0;

		System.out.println("Diga el nombre del Jugador");
		String nombreEscrito = sc.nextLine();
		System.out.println("Diga la vida del Jugador");
		int vidaEscrito = sc.nextInt();
		System.out.println("Diga el ataque del Jugador");
		int atqEscrito = sc.nextInt();
		System.out.println("Diga la defensa del Jugador");
		int defEscrito = sc.nextInt();

		String query = "INSERT INTO Jugador (nombre, vida, ataque, defensa) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, nombreEscrito);
			pstmt.setInt(2, vidaEscrito);
			pstmt.setInt(3, atqEscrito);
			pstmt.setInt(4, defEscrito);

			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getLong(1);
					System.out.println("Jugador agregado correctamente a la base de datos con ID: " + generatedId);
				} else {
					System.out.println("No se pudo obtener el ID generado.");
				}
			}
			sc.nextLine();
			System.out.println("Diga el rol que desea seleccionar");
			System.out.println("1. Alumno");
			System.out.println("2. Profesor");
			System.out.println("3. Padre");
			int rolJugador = sc.nextInt();
			sc.nextLine();

			switch (rolJugador) {
			case 1:
				crearAlumno(generatedId);
				break;
			case 2:
				crearProfesor(generatedId);
				break;
			case 3:
				crearPadre(generatedId);
				break;
			default:
				System.out.println("Opción inválida.");
				break;
			}
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}

	/**
	 * Crea un nuevo registro de alumno en la base de datos con el ID del jugador generado.
	 * 
	 * @param generatedId El ID generado del jugador
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos
	 */
	private void crearAlumno(long generatedId) throws SQLException {
		System.out.println("Diga el curso del Alumno");
		String curso = sc.nextLine();
		System.out.println("Agregado correctamente");

		String query = "INSERT INTO Alumno (id_jugador, curso) VALUES (?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setLong(1, generatedId);
			pstmt.setString(2, curso);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Crea un nuevo registro de profesor en la base de datos con el ID del jugador generado.
	 * 
	 * @param generatedId El ID generado del jugador
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos
	 */
	private void crearProfesor(long generatedId) throws SQLException {
		System.out.println("Diga la asignatura del Profesor");
		String asig = sc.nextLine();
		System.out.println("Agregado correctamente");

		String query = "INSERT INTO Profesor (id_jugador, asignatura) VALUES (?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setLong(1, generatedId);
			pstmt.setString(2, asig);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Crea un nuevo registro de padre en la base de datos con el ID del jugador generado.
	 * 
	 * @param generatedId El ID generado del jugador
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos
	 */
	private void crearPadre(long generatedId) throws SQLException {
		System.out.println("Diga el sexo del Padre");
		String sexo = sc.nextLine();
		System.out.println("Agregado correctamente");

		String query = "INSERT INTO Padre (id_jugador, sexo) VALUES (?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setLong(1, generatedId);
			pstmt.setString(2, sexo);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Modifica los datos de un jugador en la base de datos.
	 * 
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos
	 */
	public void modificarJugador() {
		conexion = conectar();

		System.out.println("Diga el ID del Jugador que desea modificar");
		long idJugador = sc.nextLong();
		sc.nextLine();

		System.out.println("Diga el nuevo nombre del Jugador");
		String nombreEscrito = sc.nextLine();
		System.out.println("Diga la nueva vida del Jugador");
		int vidaEscrito = sc.nextInt();
		System.out.println("Diga el nuevo ataque del Jugador");
		int atqEscrito = sc.nextInt();
		System.out.println("Diga la nueva defensa del Jugador");
		int defEscrito = sc.nextInt();
		sc.nextLine();

		String query = "UPDATE Jugador SET nombre = ?, vida = ?, ataque = ?, defensa = ? WHERE id_jugador = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, nombreEscrito);
			pstmt.setInt(2, vidaEscrito);
			pstmt.setInt(3, atqEscrito);
			pstmt.setInt(4, defEscrito);
			pstmt.setLong(5, idJugador);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Jugador modificado correctamente en la base de datos.");
			} else {
				System.out.println("No se encontró ningún jugador con el ID proporcionado.");
				return;
			}

		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}
	/**
	 * Elimina un jugador de la base de datos junto con sus registros asociados en las tablas de roles (Alumno, Profesor, Padre).
	 * 
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos
	 */
	public void eliminarJugador() {
		conexion = conectar();

		System.out.println("Diga el ID del Jugador que desea eliminar");
		long idJugador = sc.nextLong();
		sc.nextLine(); // Consumir la nueva línea

		String deleteJugadorQuery = "DELETE FROM Jugador WHERE id_jugador = ?";
		String deleteAlumnoQuery = "DELETE FROM Alumno WHERE id_jugador = ?";
		String deleteProfesorQuery = "DELETE FROM Profesor WHERE id_jugador = ?";
		String deletePadreQuery = "DELETE FROM Padre WHERE id_jugador = ?";

		try {
			// Iniciar transacción
			conexion.setAutoCommit(false);

			// Eliminar registros de las tablas específicas de rol
			eliminarRegistroEspecifico(idJugador, deleteAlumnoQuery);
			eliminarRegistroEspecifico(idJugador, deleteProfesorQuery);
			eliminarRegistroEspecifico(idJugador, deletePadreQuery);

			// Eliminar registro de la tabla Jugador
			try (PreparedStatement pstmt = conexion.prepareStatement(deleteJugadorQuery)) {
				pstmt.setLong(1, idJugador);
				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Jugador eliminado correctamente de la base de datos.");
				} else {
					System.out.println("No se encontró ningún jugador con el ID proporcionado.");
				}
			}

			conexion.commit();
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la consulta: " + e.getMessage());
			try {
				conexion.rollback();
			} catch (SQLException rollbackEx) {
				System.out.println("Error al revertir la transacción: " + rollbackEx.getMessage());
			}
		} finally {
			try {
				if (conexion != null) {
					conexion.setAutoCommit(true);
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}

	/**
	 * Elimina un registro específico de una tabla en la base de datos.
	 * 
	 * @param idJugador El ID del jugador cuyo registro se eliminará
	 * @param query La consulta SQL para eliminar el registro específico
	 * @throws SQLException Si ocurre un error al interactuar con la base de datos
	 */
	private void eliminarRegistroEspecifico(long idJugador, String query) throws SQLException {
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setLong(1, idJugador);
			pstmt.executeUpdate();
		}
	}
}
