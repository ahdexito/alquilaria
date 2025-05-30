package modelo;

import java.sql.*;

public class PropietarioCRUD {
	
    // CREAR UN PROPIETARIO //
    public static void crear(Connection conex, Propietario propietario) throws SQLException {
		
		String dni = propietario.getDni();
		String nombre = propietario.getNombre();
		String apellidos = propietario.getApellidos();
		String correo = propietario.getCorreo();
		String telefono = propietario.getTelefono();
		
		/* Asegurar que la variable que se envía sea NULL si no se insertó ningún valor 
		para que se recoja el error en campos con restricción NOTNULL */
		dni = dni.isEmpty() ? null : dni;
		nombre = nombre.isEmpty() ? null : nombre;
		apellidos = apellidos.isEmpty() ? null : apellidos;
		correo = correo.isEmpty() ? null : correo;
		telefono = telefono.isEmpty() ? null : telefono;
		
		try {
			String query = "INSERT INTO propietario(dni, nombre, apellidos, correo, telefono) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, telefono);

			int filas = ps.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
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
	
	// COMPROBAR SI EXISTE //
	public static boolean existe(Connection conex, Propietario propietario) throws SQLException {
		boolean existe = false;
		
		int id = propietario.getId();
		
		if (consultar(conex, id).next()) existe = true;
		
		return existe;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR UN PROPIETARIO //
	public static void modificar(Connection conex, Propietario propietarioSinMod, Propietario propietarioMod) throws SQLException {
		
		int id = propietarioMod.getId();
		String dni = propietarioMod.getDni();
		String nombre = propietarioMod.getNombre();
		String apellidos = propietarioMod.getApellidos();
		String correo = propietarioMod.getCorreo();
		String telefono = propietarioMod.getTelefono();
		
		/* Comprobar los campos vacíos. Si lo están se les asigna el valor previo */
		dni = dni.trim().isEmpty() ? propietarioSinMod.getDni() : dni;
		nombre = nombre.trim().isEmpty() ? propietarioSinMod.getNombre() : nombre;
		apellidos = apellidos.trim().isEmpty() ? propietarioSinMod.getApellidos() : apellidos;
		correo = correo.trim().isEmpty() ? propietarioSinMod.getCorreo() : correo;
		telefono = telefono.trim().isEmpty() ? propietarioSinMod.getTelefono() : telefono;
		
		try {
			String query = "UPDATE propietario SET dni = ?, nombre = ?, apellidos = ?, correo = ?, telefono = ? WHERE id = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, telefono);
			ps.setInt(6, id);

			int filas = ps.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
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
		
		System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}