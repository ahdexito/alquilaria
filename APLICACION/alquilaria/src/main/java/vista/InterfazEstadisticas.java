package vista;

import java.sql.*;
import java.util.ArrayList;

public class InterfazEstadisticas {
    
    public static void gastoInquilino(ResultSet rs) throws SQLException {
		
		ArrayList<String> filas = new ArrayList<String>();
		
		while (rs.next()) {
			filas.add(
				"NOMBRE: " + rs.getString("apellidos") + ", " + rs.getString("nombre") + "  |||  " +
				"GASTO TOTAL: " + rs.getFloat("gasto_total") + "€"
			);
		}
		
		for (String fila : filas) {
			System.out.println(fila);
		}
	}
	
	public static void cantidadAlquileresPropietario(ResultSet rs) throws SQLException {
		
		ArrayList<String> filas = new ArrayList<String>();
		
		while (rs.next()) {
			filas.add(
				"NOMBRE: " + rs.getString("apellidos") + ", " + rs.getString("nombre") + "  |||  " +
				"VIVIENDAS ALQUILADAS: " + rs.getInt("viviendas_alquiladas")
			);
		}
		
		for (String fila : filas) {
			System.out.println(fila);
		}
	}
	
	public static void estadoContratos(ResultSet rs) throws SQLException {
		
		ArrayList<String> filas = new ArrayList<String>();
		
		while (rs.next()) {
			filas.add(
				"ESTADO: " + rs.getString("estado") + "  |||  " +
				"CANTIDAD: " + rs.getInt("cantidad")
			);
		}
		
		for (String fila : filas) {
			System.out.println(fila);
		}
	}
}