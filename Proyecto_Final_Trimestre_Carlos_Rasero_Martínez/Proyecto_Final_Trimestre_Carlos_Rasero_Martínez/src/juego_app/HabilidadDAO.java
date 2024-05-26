package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class HabilidadDAO {
	private Connection conexion;

	private final String USUARIO = "root";
	private final String PASSWORD = "1111";
	private final String MAQUINA = "localhost";
	private final String BD = "juego";

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

	// Método para agregar un jugador a la base de datos
	public void insertarHabilidades() {
		// Establecer la conexión con la base de datos
		conexion = conectar();
		long generatedId = 0;

		String query = "INSERT INTO Jugador (nombre, vida, ataque, defensa) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			// pstmt.setString(1, nombreEscrito);
//			pstmt.setInt(2, vidaEscrito);
//			pstmt.setInt(3, atqEscrito);
//			pstmt.setInt(4, defEscrito);

			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getLong(1);
					System.out.println("Jugador agregado correctamente a la base de datos con ID: " + generatedId);
				} else {
					System.out.println("No se pudo obtener el ID generado.");
				}
			}
			System.out.println("Diga el rol que desea seleccionar");
			System.out.println("1. Alumno");
			System.out.println("2. Profesor");
			System.out.println("3. Padre");
			int rolJugador = 0;
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

	private void crearAlumno(long generatedId) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Diga el curso del Alumno");
		String curso = sc.nextLine();

		String query = "INSERT INTO Alumno (id_jugador, curso) VALUES (?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setLong(1, generatedId);
			pstmt.setString(2, curso);
		}
	}

	private void crearProfesor(long generatedId) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Diga la asignatura del Profesor");
		String asig = sc.nextLine();

		String query = "INSERT INTO Profesor (id_jugador, curso) VALUES (?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setLong(1, generatedId);
			pstmt.setString(2, asig);
		}
	}

	private void crearPadre(long generatedId) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Diga el sexo del Padre");
		String sexo = sc.nextLine();

		String query = "INSERT INTO Padre (id_jugador, curso) VALUES (?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setLong(1, generatedId);
			pstmt.setString(2, sexo);
		}
	}

}