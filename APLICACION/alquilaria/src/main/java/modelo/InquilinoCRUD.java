package modelo;

import java.sql.*;

public class InquilinoCRUD {
    
    // CREAR UN INQUILINO //
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
	
	// CONSULTAR UN INQUILINO //
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		String query = "SELECT * FROM inquilino WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR UN INQUILINO //
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
	
	// ELIMINAR UN INQUILINO //
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		String query = "DELETE FROM inquilino WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}