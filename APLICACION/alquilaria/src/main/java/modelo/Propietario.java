package modelo;

import java.sql.*;

public class Propietario {
    
	// ATRIBUTOS DE PROPIETARIO //
	private int id;
	private String dni, nombre, apellidos, correo, telefono;

	// CONSTRUCTORES //
	public Propietario() {
	}
	
	public Propietario(int id, String dni, String nombre, String apellidos, String correo, String telefono) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA CREAR UN PROPIETARIO //
    public void crear(Connection conex) throws SQLException {
		
		try {
			/* ASEGURAR QUE LA VARIABLE QUE SE ENVÍA SEA NULL SI NO SE INSERTÓ NINGÚN VALOR 
			PARA QUE SE RECOJA EL ERROR EN CAMPOS CON RESTRICCIÓN NOTNULL */
			dni = dni.isEmpty() ? null : dni;
			nombre = nombre.isEmpty() ? null : nombre;
			apellidos = apellidos.isEmpty() ? null : apellidos;
			correo = correo.isEmpty() ? null : correo;
			telefono = telefono.isEmpty() ? null : telefono;
			
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
	
	// MÉTODO PARA CONSULTAR UN PROPIETARIO //
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		String query = "SELECT * FROM propietario WHERE id = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, id);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA MODIFICAR UN PROPIETARIO //
	public void modificar(Connection conex, Propietario propiSinModificar) throws SQLException {
		
		/* Comprobar los campos vacíos. Si lo están se les asigna el valor previo */
		
		// CUANDO HAY ESPACIOS SOLO FUNCIONA CON DNI //
		dni = dni.trim().isEmpty() ? propiSinModificar.getDni() : dni;
		nombre = nombre.trim().isEmpty() ? propiSinModificar.getNombre() : nombre;
		apellidos = apellidos.trim().isEmpty() ? propiSinModificar.getApellidos() : apellidos;
		correo = correo.trim().isEmpty() ? propiSinModificar.getCorreo() : correo;
		telefono = telefono.trim().isEmpty() ? propiSinModificar.getTelefono() : telefono;
		
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
	
	// MÉTODO PARA ELIMINAR UN PROPIETARIO //
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		CallableStatement cs = conex.prepareCall("{call sp_deleteCliente(?)}");
		cs.setInt(1, id);
		
		int filas = cs.executeUpdate();
		
		System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SETTERS Y GETTERS //
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}