package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * La clase ItemsDAO se encarga de gestionar la conexión con la base de datos
 * y las operaciones CRUD (crear, leer, actualizar, eliminar) para los ítems.
 */
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

    /**
     * Establece una conexión con la base de datos.
     * 
     * @return la conexión establecida.
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
     * Agrega un nuevo ítem a la base de datos solicitando la información al usuario.
     */
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

    /**
     * Modifica un ítem existente en la base de datos solicitando la información al usuario.
     */
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

    /**
     * Elimina un ítem de la base de datos solicitando el ID al usuario.
     */
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

    /**
     * Muestra todos los ítems de la base de datos.
     */
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
}
