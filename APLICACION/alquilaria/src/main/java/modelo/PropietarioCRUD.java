package modelo;

import java.sql.*;

/**
 * Clase que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Propietario en la base de datos.
 *
 * @author Ángel García Smakula
 */

public class PropietarioCRUD {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Inserta un nuevo propietario en la base de datos.
     *
     * @param conex Conexión activa a la base de datos.
     * @param propietario Objeto Propietario con los datos a insertar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
    public static void crear(Connection conex, Propietario propietario) throws SQLException {
		
		try {
			String query = "INSERT INTO propietario(dni, nombre, apellidos, correo, telefono) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, propietario.getDni());
			ps.setString(2, propietario.getNombre());
			ps.setString(3, propietario.getApellidos());
			ps.setString(4, propietario.getCorreo());
			ps.setString(5, propietario.getTelefono());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Consulta un propietario en la base de datos por su ID.
     *
     * @param conex Conexión activa a la base de datos.
     * @param id El ID del propietario a consultar.
     * @return Un ResultSet que contiene los datos del propietario encontrado.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		String query = "SELECT * FROM propietario WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica un propietario existente en la base de datos.
     *
     * @param conex Conexión activa a la base de datos.
     * @param propietario Objeto Propietario con los nuevos datos, incluyendo el ID para la modificación.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static void modificar(Connection conex, Propietario propietario) throws SQLException {
				
		try {
			String query = "UPDATE propietario SET dni = ?, nombre = ?, apellidos = ?, correo = ?, telefono = ? WHERE id = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setString(1, propietario.getDni());
			ps.setString(2, propietario.getNombre());
			ps.setString(3, propietario.getApellidos());
			ps.setString(4, propietario.getCorreo());
			ps.setString(5, propietario.getTelefono());
			ps.setInt(6, propietario.getId());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina un propietario de la base de datos por su ID.
     *
     * @param conex Conexión activa a la base de datos.
     * @param id El ID del propietario a eliminar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		String query = "DELETE FROM propietario WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}