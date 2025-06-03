package modelo;

import java.sql.*;

/**
 *
 * @author Ángel García Smakula
 */
public class PropietarioCRUD {
	
    // CREAR UN PROPIETARIO //
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
	
	// CONSULTAR UN PROPIETARIO //
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		String query = "SELECT * FROM propietario WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR UN PROPIETARIO //
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
	
	// ELIMINAR UN PROPIETARIO //
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		String query = "DELETE FROM propietario WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}