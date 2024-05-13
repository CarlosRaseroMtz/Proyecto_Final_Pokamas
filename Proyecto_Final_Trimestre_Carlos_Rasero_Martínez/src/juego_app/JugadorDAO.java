package juego_app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JugadorDAO {
	private Connection conexion;

	private final String USUARIO = "root";
	private final String PASSWORD = "1111";
	private final String MAQUINA = "localhost";
	private final String BD = "Juego";

	private Connection conectar() {
		Connection con = null;
		String url = "jdbc:mysql://" + MAQUINA + "/" + BD;
		try {
			con = DriverManager.getConnection(url, USUARIO, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Error al conectar al SGBD");
		}
		return con;
	}
}
