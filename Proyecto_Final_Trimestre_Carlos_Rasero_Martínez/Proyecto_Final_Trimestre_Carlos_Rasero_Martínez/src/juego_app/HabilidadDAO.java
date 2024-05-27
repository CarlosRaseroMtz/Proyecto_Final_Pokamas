package juego_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    // Método para agregar una habilidad a la base de datos
    public void agregarHabilidad(Habilidad habilidad) {
        conexion = conectar();
        String query = "INSERT INTO Habilidad (nombre, poder, penalizacion_defensa, probabilidad_critico) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, habilidad.getNombre());
            pstmt.setInt(2, habilidad.getPoder());
            pstmt.setInt(3, habilidad.getPenalizacionDefensa());
            pstmt.setDouble(4, habilidad.getProbabilidadCritico());
            pstmt.executeUpdate();
            System.out.println("Habilidad agregada correctamente a la base de datos.");
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

    // Método para modificar una habilidad en la base de datos
    public void modificarHabilidad(Habilidad habilidad) {
        conexion = conectar();
        String query = "UPDATE Habilidad SET nombre = ?, poder = ?, penalizacion_defensa = ?, probabilidad_critico = ? WHERE id_habilidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, habilidad.getNombre());
            pstmt.setInt(2, habilidad.getPoder());
            pstmt.setInt(3, habilidad.getPenalizacionDefensa());
            pstmt.setDouble(4, habilidad.getProbabilidadCritico());
            pstmt.setInt(5, habilidad.getIdHabilidad());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Habilidad modificada correctamente en la base de datos.");
            } else {
                System.out.println("No se encontró ninguna habilidad con el ID proporcionado.");
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

    // Método para eliminar una habilidad de la base de datos
    public void eliminarHabilidad(int idHabilidad) {
        conexion = conectar();
        String query = "DELETE FROM Habilidad WHERE id_habilidad = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setInt(1, idHabilidad);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Habilidad eliminada correctamente de la base de datos.");
            } else {
                System.out.println("No se encontró ninguna habilidad con el ID proporcionado.");
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

    // Método para insertar una habilidad en la base de datos
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
