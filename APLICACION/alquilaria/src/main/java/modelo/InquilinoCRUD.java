package modelo;

import java.sql.*;

/**
 * Clase que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Inquilino en la base de datos.
 *
 * @author Ángel García Smakula
 */

public class InquilinoCRUD {
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Inserta un nuevo inquilino en la base de datos.
     *
     * @param conex Conexión activa a la base de datos.
     * @param inquilino Objeto Inquilino con los datos a insertar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
    public static void crear(Connection conex, Inquilino inquilino) throws SQLException {
		
		try {			
			String query = "INSERT INTO inquilino(dni, nombre, apellidos, correo, telefono, mascotas) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, inquilino.getDni());
			ps.setString(2, inquilino.getNombre());
			ps.setString(3, inquilino.getApellidos());
			ps.setString(4, inquilino.getCorreo());
			ps.setString(5, inquilino.getTelefono());
			ps.setInt(6, inquilino.getMascotas());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Consulta un inquilino en la base de datos por su ID.
     *
     * @param conex Conexión activa a la base de datos.
     * @param id El ID del inquilino a consultar.
     * @return Un ResultSet que contiene los datos del inquilino encontrado.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		String query = "SELECT * FROM inquilino WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica un inquilino existente en la base de datos.
     *
     * @param conex Conexión activa a la base de datos.
     * @param inquilino Objeto Inquilino con los nuevos datos, incluyendo el ID para la modificación.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static void modificar(Connection conex, Inquilino inquilino) throws SQLException {
			
		try {
			String query = "UPDATE inquilino SET dni = ?, nombre = ?, apellidos = ?, correo = ?, telefono = ?, mascotas = ? WHERE id = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setString(1, inquilino.getDni());
			ps.setString(2, inquilino.getNombre());
			ps.setString(3, inquilino.getApellidos());
			ps.setString(4, inquilino.getCorreo());
			ps.setString(5, inquilino.getTelefono());
			ps.setInt(6, inquilino.getMascotas());
			ps.setInt(7, inquilino.getId());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina un inquilino de la base de datos por su ID.
     *
     * @param conex Conexión activa a la base de datos.
     * @param id El ID del inquilino a eliminar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
	
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		String query = "DELETE FROM inquilino WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}