package modelo;

import java.sql.*;

/**
 * Clase que provee métodos para la generación de estadísticas relacionadas
 * con los contratos, inquilinos y propietarios en la base de datos.
 *
 * @author Ángel García Smakula
 */

public class EstadisticasCRUD {
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Calcula el gasto total en alquiler de cada inquilino.
     *
     * @param conex Conexión activa a la base de datos.
     * @return Un ResultSet con el nombre, apellidos del inquilino y el gasto total en alquiler.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static ResultSet gastoInquilino(Connection conex) throws SQLException {
		
		String query = ""
			+ "SELECT i.nombre, i.apellidos, SUM(c.precio) AS gasto_total "
			+ "FROM contrato c "
			+ "JOIN inquilino i ON c.id_inquilino = i.id "
			+ "GROUP BY i.id";

		PreparedStatement ps = conex.prepareStatement(query);

		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Calcula la cantidad de viviendas en alquiler que tiene cada propietario.
     *
     * @param conex Conexión activa a la base de datos.
     * @return Un ResultSet con el nombre, apellidos del propietario y el número de viviendas alquiladas.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
		
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Cuenta cuántos contratos existen actualmente para cada estado (por ejemplo, "activo", "finalizado", etc.).
     *
     * @param conex Conexión activa a la base de datos.
     * @return Un ResultSet con el estado del contrato y la cantidad de contratos en ese estado.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static ResultSet estadoContratos(Connection conex) throws SQLException {
		
		String query = ""
			+ "SELECT estado, COUNT(*) AS cantidad "
			+ "FROM contrato "
			+ "GROUP BY estado";
				
		PreparedStatement ps = conex.prepareStatement(query);

		return ps.executeQuery();
	}
}