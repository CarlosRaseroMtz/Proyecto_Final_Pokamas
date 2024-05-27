package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ItemsDAO {
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

    // Método para agregar un item a la base de datos
    public void agregarItem(Items item) {
        conexion = conectar();
        String query = "INSERT INTO Item (id_habilidad, nombre, poder, mejora_combate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, item.getIdHabilidad());
            pstmt.setString(2, item.getNombre());
            pstmt.setInt(3, item.getPoder());
            pstmt.setInt(4, item.getMejoraCombate());
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

    // Método para modificar un item en la base de datos
    public void modificarItem(Items item) {
        conexion = conectar();
        String query = "UPDATE Item SET nombre = ?, poder = ?, mejora_combate = ? WHERE id_habilidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, item.getNombre());
            pstmt.setInt(2, item.getPoder());
            pstmt.setInt(3, item.getMejoraCombate());
            pstmt.setInt(4, item.getIdHabilidad());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item modificado correctamente en la base de datos.");
            } else {
                System.out.println("No se encontró ningún item con el ID proporcionado.");
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

    // Método para eliminar un item de la base de datos
    public void eliminarItem(int idHabilidad) {
        conexion = conectar();
        String query = "DELETE FROM Item WHERE id_habilidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, idHabilidad);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item eliminado correctamente de la base de datos.");
            } else {
                System.out.println("No se encontró ningún item con el ID proporcionado.");
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
