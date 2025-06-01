package modelo;

import java.sql.*;

public class InquilinoCRUD {
    
    // CREAR UN INQUILINO //
    public static void crear(Connection conex, Inquilino inquilino) throws SQLException {
		
		String dni = inquilino.getDni();
		String nombre = inquilino.getNombre();
		String apellidos = inquilino.getApellidos();
		String correo = inquilino.getCorreo();
		String telefono = inquilino.getTelefono();
		int mascotas = inquilino.getMascotas();
		
		/* Asegurar que la variable que se envía sea NULL si no se insertó ningún valor 
		para que se recoja el error en campos con restricción NOTNULL */
		dni = dni.isEmpty() ? null : dni;
		nombre = nombre.isEmpty() ? null : nombre;
		apellidos = apellidos.isEmpty() ? null : apellidos;
		correo = correo.isEmpty() ? null : correo;
		telefono = telefono.isEmpty() ? null : telefono;
		mascotas = (mascotas == -1) ? 0 : mascotas;
		
		try {			
			String query = "INSERT INTO inquilino(dni, nombre, apellidos, correo, telefono, mascotas) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, telefono);
			ps.setInt(6, mascotas);

			int filas = ps.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
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
	public static void modificar(Connection conex, Inquilino inquilinoSinMod, Inquilino inquilinoMod)throws SQLException {
		
		int id = inquilinoMod.getId();
		String dni = inquilinoMod.getDni();
		String nombre = inquilinoMod.getNombre();
		String apellidos = inquilinoMod.getApellidos();
		String correo = inquilinoMod.getCorreo();
		String telefono = inquilinoMod.getTelefono();
		int mascotas = inquilinoMod.getMascotas();
		
		/* Comprobar los campos vacíos. Si lo están se les asigna el valor previo */
		dni = dni.trim().isEmpty() ? inquilinoSinMod.getDni() : dni;
		nombre = nombre.trim().isEmpty() ? inquilinoSinMod.getNombre() : nombre;
		apellidos = apellidos.trim().isEmpty() ? inquilinoSinMod.getApellidos() : apellidos;
		correo = correo.trim().isEmpty() ? inquilinoSinMod.getCorreo() : correo;
		telefono = telefono.trim().isEmpty() ? inquilinoSinMod.getTelefono() : telefono;
		mascotas = (mascotas == -1) ? inquilinoSinMod.getMascotas() : mascotas;
		
		try {
			String query = "UPDATE inquilino SET dni = ?, nombre = ?, apellidos = ?, correo = ?, telefono = ?, mascotas = ? WHERE id = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, telefono);
			ps.setInt(6, mascotas);
			ps.setInt(7, id);

			int filas = ps.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
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
		
		System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}