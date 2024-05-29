package juego_app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class HabilidadDAOTest {

    private HabilidadDAO habilidadDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private Statement mockStatement;


    @Test
    void testAgregarHabilidad() throws SQLException {
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong(1)).thenReturn(1L);

        // Simulación de la entrada del usuario
        doReturn("Habilidad Test").when(habilidadDAO).sc.nextLine();
        doReturn(50).when(habilidadDAO).sc.nextInt();
        doReturn(10).when(habilidadDAO).sc.nextInt();
        doReturn(0.2f).when(habilidadDAO).sc.nextFloat();

        habilidadDAO.agregarHabilidad();

        verify(mockPreparedStatement).setString(1, "Habilidad Test");
        verify(mockPreparedStatement).setInt(2, 50);
        verify(mockPreparedStatement).setInt(3, 10);
        verify(mockPreparedStatement).setFloat(4, 0.2f);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testModificarHabilidad() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Simulación de la entrada del usuario
        doReturn(1L).when(habilidadDAO).sc.nextLong();
        doReturn("Habilidad Modificada").when(habilidadDAO).sc.nextLine();
        doReturn(60).when(habilidadDAO).sc.nextInt();
        doReturn(15).when(habilidadDAO).sc.nextInt();
        doReturn(0.25f).when(habilidadDAO).sc.nextFloat();

        habilidadDAO.modificarHabilidad();

        verify(mockPreparedStatement).setString(1, "Habilidad Modificada");
        verify(mockPreparedStatement).setInt(2, 60);
        verify(mockPreparedStatement).setInt(3, 15);
        verify(mockPreparedStatement).setFloat(4, 0.25f);
        verify(mockPreparedStatement).setLong(5, 1L);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testEliminarHabilidad() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Simulación de la entrada del usuario
        doReturn(1).when(habilidadDAO).sc.nextInt();

        habilidadDAO.eliminarHabilidad();

        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testMostrarHabilidades() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id_habilidad")).thenReturn(1);
        when(mockResultSet.getString("nombre")).thenReturn("Habilidad Test");
        when(mockResultSet.getInt("poder")).thenReturn(50);
        when(mockResultSet.getInt("penalizacion_def")).thenReturn(10);
        when(mockResultSet.getFloat("probabilidad_crit")).thenReturn(0.2f);

        habilidadDAO.mostrarHabilidades();

        verify(mockStatement).executeQuery(anyString());
    }

}
