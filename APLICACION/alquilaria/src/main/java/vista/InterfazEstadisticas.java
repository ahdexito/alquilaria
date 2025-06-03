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
	
	public static void cantidadAlquileresPropietario(ResultSet rs) {
		
	}
	
	public static void estadoContratos(ResultSet rs) {
		
	}
}