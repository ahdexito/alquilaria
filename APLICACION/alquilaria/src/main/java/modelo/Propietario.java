package modelo;

import java.sql.*;

public class Propietario {
    
	private int id;
	private String dni, nombre, apellidos, email, telefono;

	public Propietario() {
	}
	
	public Propietario(int id, String dni, String nombre, String apellidos, String email, String telefono) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA CREAR UN CLIENTE //
    public static void crear
			(Connection conex, String dni, String nombre, String apellido1, String apellido2, String email, String telefono) 
				throws SQLException {
		
		try {
			/* ASEGURAR QUE LA VARIABLE QUE SE ENVÍA SEA NULL SI NO SE INSERTÓ NINGÚN VALOR 
			PARA QUE SE RECOJA EL ERROR EN CAMPOS CON RESTRICCIÓN NOTNULL */
			dni = dni.isEmpty() ? null : dni;
			nombre = nombre.isEmpty() ? null : nombre;
			apellido1 = apellido1.isEmpty() ? null : apellido1;
			apellido2 = apellido2.isEmpty() ? null : apellido2;
			email = email.isEmpty() ? null : email;
			telefono = telefono.isEmpty() ? null : telefono;

			CallableStatement cs = conex.prepareCall("{call sp_insertCliente(?, ?, ?, ?, ?, ?)}");

			cs.setString(1, dni);
			cs.setString(2, nombre);
			cs.setString(3, apellido1);
			cs.setString(4, apellido2);
			cs.setString(5, email);
			cs.setString(6, telefono);

			int filas = cs.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA CONSULTAR UN CLIENTE //
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		CallableStatement cs = conex.prepareCall("{call sp_getCliente(?)}");
		cs.setInt(1, id);
		ResultSet rs = cs.executeQuery();
		return rs;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA MODIFICAR UN CLIENTE //
	public static void modificar
			(Connection conex, int id, String dni, String nombre, String apellido1, String apellido2, String email, String telefono) 
				throws SQLException {
		
		try {	
			CallableStatement cs = conex.prepareCall("{call sp_modifyCliente(?, ?, ?, ?, ?, ?, ?)}");

			cs.setInt(1, id);
			cs.setString(2, dni);
			cs.setString(3, nombre);
			cs.setString(4, apellido1);
			cs.setString(5, apellido2);
			cs.setString(6, email);
			cs.setString(7, telefono);

			int filas = cs.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA ELIMINAR UN CLIENTE //
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		CallableStatement cs = conex.prepareCall("{call sp_deleteCliente(?)}");
		cs.setInt(1, id);
		
		int filas = cs.executeUpdate();
		
		System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}