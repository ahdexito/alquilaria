package modelo;

import java.sql.*;

public class EstadisticasCRUD {
    
    public static ResultSet gastoInquilino(Connection conex) throws SQLException {
		
		String query = ""
			+ "SELECT i.nombre, i.apellidos, SUM(c.precio) AS gasto_total "
			+ "FROM contrato c "
			+ "JOIN inquilino i ON c.id_inquilino = i.id "
			+ "GROUP BY i.id";

		PreparedStatement ps = conex.prepareStatement(query);

		return ps.executeQuery();
	}
	
	public static ResultSet cantidadAlquileresPropietario(Connection conex) throws SQLException {
		
		String query = ""
			+ "SELECT p.nombre, p.apellidos, COUNT(DISTINCT c.cod_vivienda) AS viviendas_alquiladas "
			+ "FROM contrato c "
			+ "JOIN vivienda v ON c.cod_vivienda = v.cod "
			+ "JOIN propietario p ON v.id_propietario = p.id "
			+ "GROUP BY p.id";

		PreparedStatement ps = conex.prepareStatement(query);

		return ps.executeQuery();
	}
	
	public static ResultSet estadoContratos(Connection conex) throws SQLException {
		
		String query = ""
			+ "SELECT estado, COUNT(*) AS cantidad "
			+ "FROM contrato "
			+ "GROUP BY estado";
				
		PreparedStatement ps = conex.prepareStatement(query);

		return ps.executeQuery();
	}
}