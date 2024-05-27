package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    // Método para modificar un item en la base de datos
    public void modificarItem(Items item) {
        conexion = conectar();
        String query = "UPDATE Item SET nombre = ?, poder = ?, mejora_combate = ? WHERE id_item = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, item.getNombre());
            pstmt.setInt(2, item.getPoder());
            pstmt.setInt(3, item.getMejoraCombate());
            pstmt.setInt(4, item.getIdItem());
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
    public void eliminarItem(int idItem) {
        conexion = conectar();
        String query = "DELETE FROM Item WHERE id_item = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, idItem);
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

    public void insertarItemsPa() {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String LIGHT_GREEN = "\u001B[92m";
        Items[] items = new Items[4];
        items[0] = new Items(GREEN + "Beber Cerveza: " + RESET + LIGHT_GREEN + "'Además muy fría'" + RESET, 25, 20);
        items[1] = new Items(GREEN + "Ticket exclusivo del AMPA: " + RESET + LIGHT_GREEN + "'Recuperas toda la salud, aumenta tu ataque y defensa mucho, desde ahora todos te respetan.'" + RESET, 100, 20);
        items[2] = new Items(GREEN + "Tomar una tapita en el bar: " + RESET + LIGHT_GREEN + "'Además una cerveza'" + RESET, 35, 30);
        items[3] = new Items(GREEN + "Usa la chancla: " + RESET + LIGHT_GREEN + "'Se pueden hacer muchas cosas con una chancla'" + RESET, 10, 20);

        for (Items item : items) {
            agregarItem(item);
        }
    }
    
    public void insertarItemsPr() {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String LIGHT_GREEN = "\u001B[92m";
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
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String LIGHT_GREEN = "\u001B[92m";
        Items[] items = new Items[4];
		items[0] = new Items(1, GREEN+"Tomar Merienda: "+RESET+LIGHT_GREEN+"'El recreo es sagrado'"+RESET, 25, 20);
		items[1] = new Items(2, GREEN+"Fumar: "+RESET+LIGHT_GREEN+"'Recuperas todas las ganas de volver a clase.'"+RESET, 100, 20);
		items[2] = new Items(3, GREEN+"Beber Bebida Energética: "+RESET+LIGHT_GREEN+"'Te da un Boost a tus 5h de sueño'"+RESET, 15, 10);
		items[3] = new Items(4, GREEN+"Usar Cascos: "+RESET+LIGHT_GREEN+"'La música es poder'"+RESET, 40, 20);

        for (Items item : items) {
            agregarItem(item);
        }
    }
}
