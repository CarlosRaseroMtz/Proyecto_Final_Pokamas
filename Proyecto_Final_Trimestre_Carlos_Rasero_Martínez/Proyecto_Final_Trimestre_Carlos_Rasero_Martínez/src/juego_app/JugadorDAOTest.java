package juego_app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class JugadorDAOTest {
    
    private JugadorDAO jugadorDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        jugadorDAO = new JugadorDAO();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    void testAgregarJugador() throws SQLException {
        // Configurar los mocks
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong(1)).thenReturn(1L);

        // Ejecutar el método
        jugadorDAO.agregarJugador();

        // Verificar interacciones y capturar argumentos
        verify(mockPreparedStatement, times(1)).setString(eq(1), anyString());
        verify(mockPreparedStatement, times(1)).setInt(eq(2), anyInt());
        verify(mockPreparedStatement, times(1)).setInt(eq(3), anyInt());
        verify(mockPreparedStatement, times(1)).setInt(eq(4), anyInt());
        verify(mockPreparedStatement, times(1)).executeUpdate();

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(mockPreparedStatement).setString(eq(1), stringCaptor.capture());
        verify(mockPreparedStatement).setInt(eq(2), intCaptor.capture());
        verify(mockPreparedStatement).setInt(eq(3), intCaptor.capture());
        verify(mockPreparedStatement).setInt(eq(4), intCaptor.capture());

        assertEquals("Nombre del jugador", stringCaptor.getValue());
        assertEquals(100, intCaptor.getAllValues().get(0));  // Vida
        assertEquals(50, intCaptor.getAllValues().get(1));   // Ataque
        assertEquals(30, intCaptor.getAllValues().get(2));   // Defensa
    }

    @Test
    void testModificarJugador() throws SQLException {
        // Configurar los mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Ejecutar el método
        jugadorDAO.modificarJugador();

        // Verificar interacciones y capturar argumentos
        verify(mockPreparedStatement, times(1)).setString(eq(1), anyString());
        verify(mockPreparedStatement, times(1)).setInt(eq(2), anyInt());
        verify(mockPreparedStatement, times(1)).setInt(eq(3), anyInt());
        verify(mockPreparedStatement, times(1)).setInt(eq(4), anyInt());
        verify(mockPreparedStatement, times(1)).setLong(eq(5), anyLong());
        verify(mockPreparedStatement, times(1)).executeUpdate();

        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);

        verify(mockPreparedStatement).setString(eq(1), stringCaptor.capture());
        verify(mockPreparedStatement).setInt(eq(2), intCaptor.capture());
        verify(mockPreparedStatement).setInt(eq(3), intCaptor.capture());
        verify(mockPreparedStatement).setInt(eq(4), intCaptor.capture());
        verify(mockPreparedStatement).setLong(eq(5), longCaptor.capture());

        assertEquals("Nuevo nombre del jugador", stringCaptor.getValue());
        assertEquals(120, intCaptor.getAllValues().get(0));  // Nueva vida
        assertEquals(70, intCaptor.getAllValues().get(1));   // Nuevo ataque
        assertEquals(40, intCaptor.getAllValues().get(2));   // Nueva defensa
        assertEquals(1L, longCaptor.getValue());             // ID del jugador
    }

    @Test
    void testEliminarJugador() throws SQLException {
        // Configurar los mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Ejecutar el método
        jugadorDAO.eliminarJugador();

        // Verificar interacciones y capturar argumentos
        verify(mockPreparedStatement, times(4)).setLong(eq(1), anyLong());
        verify(mockPreparedStatement, times(4)).executeUpdate();

        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
        verify(mockPreparedStatement, times(4)).setLong(eq(1), longCaptor.capture());

        assertEquals(1L, longCaptor.getValue());  // ID del jugador
    }
}
