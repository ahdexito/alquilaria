package modelo;

import java.sql.*;

/**
 * Clase que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Vivienda en la base de datos.
 *
 * @author Ángel García Smakula
 */

public class ViviendaCRUD {
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Inserta una nueva vivienda en la base de datos.
     *
     * @param conex Conexión activa a la base de datos.
     * @param vivienda Objeto Vivienda con los datos a insertar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
		
    public static void crear(Connection conex, Vivienda vivienda) throws SQLException {
		
		try {			
			String query = "INSERT INTO vivienda(cod, id_propietario, direccion, precio, superficie, descripcion, mascotas, tipo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, vivienda.getCod());
			ps.setInt(2, vivienda.getIdPropietario());
			ps.setString(3, vivienda.getDireccion());
			ps.setFloat(4, vivienda.getPrecio());
			ps.setFloat(5, vivienda.getSuperficie());
			ps.setString(6, vivienda.getDescripcion());
			ps.setInt(7, vivienda.getMascotas());
			ps.setInt(8, vivienda.getTipo());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Consulta una vivienda en la base de datos por su código.
     * Realiza un JOIN con la tabla 'tipo_vivienda' para obtener información detallada del tipo.
     *
     * @param conex Conexión activa a la base de datos.
     * @param cod El código de la vivienda a consultar.
     * @return Un ResultSet que contiene los datos de la vivienda encontrada.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static ResultSet consultar(Connection conex, String cod) throws SQLException {
		
		String query = "SELECT * FROM vivienda v JOIN tipo_vivienda t ON v.tipo = t.numero WHERE cod = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setString(1, cod);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica una vivienda existente en la base de datos.
     *
     * @param conex Conexión activa a la base de datos.
     * @param vivienda Objeto Vivienda con los nuevos datos, incluyendo el código para la modificación.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static void modificar(Connection conex, Vivienda vivienda) throws SQLException {	
		
		try {
			String query = "UPDATE vivienda SET id_propietario = ?, direccion = ?, precio = ?, superficie = ?, descripcion = ?, mascotas = ?, tipo = ? WHERE cod = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setInt(1, vivienda.getIdPropietario());
			ps.setString(2, vivienda.getDireccion());
			ps.setFloat(3, vivienda.getPrecio());
			ps.setFloat(4, vivienda.getSuperficie());
			ps.setString(5, vivienda.getDescripcion());
			ps.setInt(6, vivienda.getMascotas());
			ps.setInt(7, vivienda.getTipo());
			ps.setString(8, vivienda.getCod());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina una vivienda de la base de datos por su código.
     *
     * @param conex Conexión activa a la base de datos.
     * @param cod El código de la vivienda a eliminar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static void eliminar(Connection conex, String cod) throws SQLException {
		
		String query = "DELETE FROM vivienda WHERE cod = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setString(1, cod);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}