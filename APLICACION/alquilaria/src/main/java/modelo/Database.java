package modelo;

import java.sql.*;

// PATRÓN SINGLETON //
public class Database {
    
    private static Database conexUnica = null;
	
	private Connection conex;
	
	private Database(String nombreBD) throws SQLException {
		try {
			this.conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombreBD, "root", "");
			
			if (this.conex.isClosed()) {
				throw new SQLException("ERROR: LA CONEXIÓN ESTÁ CERRADA");
			}
		}
		
		catch (SQLException ex) {
			throw new SQLException("ERROR: " + ex.getMessage());
		}
	}
	
	public static Database getConex(String nombreBD) throws SQLException {
		if (conexUnica == null) {
			conexUnica = new Database(nombreBD);
		}
		return conexUnica;
	}
	
	public Connection getConex() {
		return conex;
	}
	
	public void cerrarConex() throws SQLException {
		if (conex != null && !conex.isClosed()) {
			conex.close();
		}
	}
}