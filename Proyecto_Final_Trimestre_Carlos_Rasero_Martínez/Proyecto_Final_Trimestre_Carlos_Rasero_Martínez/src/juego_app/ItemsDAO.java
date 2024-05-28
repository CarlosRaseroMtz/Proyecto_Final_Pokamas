package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ItemsDAO {
	private Connection conexion;

	private final String USUARIO = "root";
	private final String PASSWORD = "1111";
	private final String MAQUINA = "localhost";
	private final String BD = "juego";
	
	public static final String GREEN = "\u001B[32m";
	public final static String LIGHT_GREEN = "\u001B[92m";
	public static final String RESET = "\u001B[0m";

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

	public void agregarItem() {
		conexion = conectar();
		long generatedId = 0;

		System.out.println("Diga el nombre del item");
		String nombreItem = sc.nextLine();
		System.out.println("Diga el poder del item");
		int poder = sc.nextInt();
		System.out.println("Diga la mejora de combate del item");
		int mejoraCombate = sc.nextInt();

		String query = "INSERT INTO Item (nombre, poder, mejora_combate) VALUES (?, ?, ?)";

		try (PreparedStatement pstmt = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, nombreItem);
			pstmt.setInt(2, poder);
			pstmt.setInt(3, mejoraCombate);

			pstmt.executeUpdate();
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedId = generatedKeys.getLong(1);
					System.out.println("Item agregado correctamente a la base de datos con ID: " + generatedId);
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

	public void modificarItem() {
		conexion = conectar();

		System.out.println("Diga el ID del item a modificar");
		int idItem = sc.nextInt();
		sc.nextLine(); // Consumir el salto de línea

		System.out.println("Diga el nuevo nombre del item");
		String nombreItem = sc.nextLine();
		System.out.println("Diga el nuevo poder del item");
		int poder = sc.nextInt();
		System.out.println("Diga la nueva mejora de combate del item");
		int mejoraCombate = sc.nextInt();

		String query = "UPDATE Item SET nombre = ?, poder = ?, mejora_combate = ? WHERE id_item = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, nombreItem);
			pstmt.setInt(2, poder);
			pstmt.setInt(3, mejoraCombate);
			pstmt.setInt(4, idItem);

			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Item modificado correctamente.");
			} else {
				System.out.println("No se encontró el item con el ID especificado.");
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

	public void eliminarItem() {
		conexion = conectar();

		System.out.println("Diga el ID del item a eliminar");
		int idItem = sc.nextInt();

		String query = "DELETE FROM Item WHERE id_item = ?";

		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setInt(1, idItem);

			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Item eliminado correctamente.");
			} else {
				System.out.println("No se encontró el item con el ID especificado.");
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

	public void mostrarItems() {
		conexion = conectar();

		String query = "SELECT id_item, nombre, poder, mejora_combate FROM Item";

		try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

			System.out.println("ID | Nombre | Poder | Mejora de Combate");
			System.out.println("------------------------------------------");

			while (rs.next()) {
				int id = rs.getInt("id_item");
				String nombre = rs.getString("nombre");
				int poder = rs.getInt("poder");
				int mejoraCombate = rs.getInt("mejora_combate");

				System.out.println(id + " | " + nombre + " | " + poder + " | " + mejoraCombate);
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

	// Método para agregar un item a la base de datos
	public void agregarItem(Items item) {
		conexion = conectar();
		String query = "INSERT INTO Item (nombre, poder, mejora_combate) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
			pstmt.setString(1, item.getNombre());
			pstmt.setInt(2, item.getPoder());
			pstmt.setInt(3, item.getMejoraCombate());
			pstmt.executeUpdate();
			System.out.println("Item agregado correctamente a la base de datos.");
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

	public void insertarItemsPa() {
		Items[] items = new Items[4];
		items[0] = new Items(GREEN + "Beber Cerveza: " + RESET + LIGHT_GREEN + "'Además muy fría'" + RESET, 25, 20);
		items[1] = new Items(GREEN + "Ticket exclusivo del AMPA: " + RESET + LIGHT_GREEN
				+ "'Recuperas toda la salud, aumenta tu ataque y defensa mucho, desde ahora todos te respetan.'"
				+ RESET, 100, 20);
		items[2] = new Items(
				GREEN + "Tomar una tapita en el bar: " + RESET + LIGHT_GREEN + "'Además una cerveza'" + RESET, 35, 30);
		items[3] = new Items(GREEN + "Usa la chancla: " + RESET + LIGHT_GREEN
				+ "'Se pueden hacer muchas cosas con una chancla'" + RESET, 10, 20);

		for (Items item : items) {
			agregarItem(item);
		}
	}

	public void insertarItemsPr() {
		Items[] items = new Items[4];
		items[0] = new Items(1,
				GREEN + "Beber Café: " + RESET + LIGHT_GREEN + "'Gracias al cafe, aguanta 3H mas'" + RESET, 25, 20);
		items[1] = new Items(2,
				GREEN + "Ir a la sala de profesores: " + RESET + LIGHT_GREEN + "'Lugar Oculto*'" + RESET, 20, 30);
		items[2] = new Items(3, GREEN + "Poner parte: " + RESET + LIGHT_GREEN
				+ "'En este instituto no toleramos los saltavallas'" + RESET, 15, 15);
		items[3] = new Items(4, GREEN + "Tirar Internet: " + RESET + LIGHT_GREEN
				+ "'Obliga al jugador a tener que irse, sube mucho la salud'" + RESET, 90, 30);

		for (Items item : items) {
			agregarItem(item);
		}
	}

	public void insertarItemsAl() {
		Items[] items = new Items[4];
		items[0] = new Items(1, GREEN + "Tomar Merienda: " + RESET + LIGHT_GREEN + "'El recreo es sagrado'" + RESET, 25,
				20);
		items[1] = new Items(2,
				GREEN + "Fumar: " + RESET + LIGHT_GREEN + "'Recuperas todas las ganas de volver a clase.'" + RESET, 100,
				20);
		items[2] = new Items(3, GREEN + "Beber Bebida Energética: " + RESET + LIGHT_GREEN
				+ "'Te da un Boost a tus 5h de sueño'" + RESET, 15, 10);
		items[3] = new Items(4, GREEN + "Usar Cascos: " + RESET + LIGHT_GREEN + "'La música es poder'" + RESET, 40, 20);

		for (Items item : items) {
			agregarItem(item);
		}
	}
}
