package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class HabilidadDAO {
	private Connection conexion;

	private final String USUARIO = "root";
	private final String PASSWORD = "1111";
	private final String MAQUINA = "localhost";
	private final String BD = "juego";

	private final Scanner sc = new Scanner(System.in);

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

	public void agregarHabilidad() {
		conexion = conectar();
		long generatedId = 0;

		System.out.println("Diga el nombre de la habilidad");
		String nombreHabilidad = sc.nextLine();
		System.out.println("Diga el poder de la habilidad");
		int poder = sc.nextInt();
		System.out.println("Diga la penalización de defensa");
		int penalizacionDef = sc.nextInt();
		System.out.println("Diga la probabilidad de crítico");
		float probabilidadCrit = sc.nextFloat();

		String query = "INSERT INTO Habilidad (nombre, poder, penalizacion_def, probabilidad_crit) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, nombreHabilidad);
			pstmt.setInt(2, poder);
			pstmt.setInt(3, penalizacionDef);
			pstmt.setFloat(4, probabilidadCrit);

			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getLong(1);
					System.out.println("Habilidad agregada correctamente a la base de datos con ID: " + generatedId);
				} else {
					System.out.println("No se pudo obtener el ID generado.");
				}
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

	public void modificarHabilidad() {
		conexion = conectar();

		System.out.println("Diga el ID de la Hablididad que desea modificar");
		long idJugador = sc.nextLong();
		sc.nextLine();

		System.out.println("Diga el nuevo nombre de la Habilidad");
		String nombreEscrito = sc.nextLine();
		System.out.println("Diga el nuevo poder de la Habilidad");
		int poderEscrito = sc.nextInt();
		System.out.println("Diga la nueva penalización de la Habilidad");
		int penalizacionEscrita = sc.nextInt();
		System.out.println("Diga la nueva probabilidad de critico de la Habilidad");
		float probabilidadEscrita = sc.nextFloat();
		sc.nextLine();

		String query = "UPDATE Habilidad SET nombre = ?, poder = ?, penalizacion_def = ?, probabilidad_crit = ? WHERE id_habilidad = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, nombreEscrito);
			pstmt.setInt(2, poderEscrito);
			pstmt.setInt(3, penalizacionEscrita);
			pstmt.setFloat(4, probabilidadEscrita);
			pstmt.setLong(5, idJugador);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Habilidad modificada correctamente en la base de datos.");
			} else {
				System.out.println("No se encontró ninguna habilidad con el ID proporcionado.");
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

	public void eliminarHabilidad() {
		conexion = conectar();

		System.out.println("Diga el ID de la habilidad a eliminar");
		int idHabilidad = sc.nextInt();

		String query = "DELETE FROM Habilidad WHERE id_habilidad = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setInt(1, idHabilidad);

			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Habilidad eliminada correctamente.");
			} else {
				System.out.println("No se encontró la habilidad con el ID especificado.");
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

	public void mostrarHabilidades() {
		conexion = conectar();

		String query = "SELECT id_habilidad, nombre, poder, penalizacion_def, probabilidad_crit FROM Habilidad";

		try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			System.out.println("ID | Nombre | Poder | Penalización de Defensa | Probabilidad de Crítico");
			System.out.println("-----------------------------------------------------------");

			while (rs.next()) {
				int id = rs.getInt("id_habilidad");
				String nombre = rs.getString("nombre");
				int poder = rs.getInt("poder");
				int penalizacionDef = rs.getInt("penalizacion_def");
				float probabilidadCrit = rs.getFloat("probabilidad_crit");

				System.out.println(
						id + " | " + nombre + " | " + poder + " | " + penalizacionDef + " | " + probabilidadCrit);
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

	public void insertarHabilidad(Habilidad habilidad) {
		conexion = conectar();
		String query = "INSERT INTO Habilidad (nombre, poder, penalizacion_def, probabilidad_crit) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, habilidad.getNombre());
			pstmt.setInt(2, habilidad.getPoder());
			pstmt.setInt(3, habilidad.getPenalizacionDefensa());
			pstmt.setDouble(4, habilidad.getProbabilidadCritico());

			pstmt.executeUpdate();
			System.out.println("Habilidad insertada correctamente en la base de datos.");
		} catch (SQLException e) {
			System.out.println("Error al insertar la habilidad: " + e.getMessage());
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
}
