package modelo;

import java.sql.*;

public class Inquilino {
    
    // ATRIBUTOS DE INQUILINO //
	private int id, mascota = -1;
	private String dni, nombre, apellidos, correo, telefono;

	// CONSTRUCTORES //
	public Inquilino() {
	}
	
	public Inquilino(int id, String dni, String nombre, String apellidos, String correo, String telefono, int mascota) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.mascota = mascota;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CREAR UN INQUILINO //
    public void crear(Connection conex) throws SQLException {
		
		try {
			/* Asegurar que la variable que se envía sea NULL si no se insertó ningún valor 
			para que se recoja el error en campos con restricción NOTNULL */
			dni = dni.isEmpty() ? null : dni;
			nombre = nombre.isEmpty() ? null : nombre;
			apellidos = apellidos.isEmpty() ? null : apellidos;
			correo = correo.isEmpty() ? null : correo;
			telefono = telefono.isEmpty() ? "(VACÍO)" : telefono;
			
			String query = "INSERT INTO inquilino(dni, nombre, apellidos, correo, telefono, mascota) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, telefono);
			ps.setInt(6, mascota);

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
	public void modificar(Connection conex, Inquilino inquiSinModificar) throws SQLException {
		
		/* Comprobar los campos vacíos. Si lo están se les asigna el valor previo */
		
		dni = dni.trim().isEmpty() ? inquiSinModificar.getDni() : dni;
		nombre = nombre.trim().isEmpty() ? inquiSinModificar.getNombre() : nombre;
		apellidos = apellidos.trim().isEmpty() ? inquiSinModificar.getApellidos() : apellidos;
		correo = correo.trim().isEmpty() ? inquiSinModificar.getCorreo() : correo;
		telefono = telefono.trim().isEmpty() ? inquiSinModificar.getTelefono() : telefono;
		mascota = (mascota == -1) ? inquiSinModificar.getMascota() : mascota;
		
		try {
			String query = "UPDATE inquilino SET dni = ?, nombre = ?, apellidos = ?, correo = ?, telefono = ?, mascota = ? WHERE id = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellidos);
			ps.setString(4, correo);
			ps.setString(5, telefono);
			ps.setInt(6, mascota);
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
	
	public int getMascota() {
		return mascota;
	}
	
	public void setMascota(int mascota) {
		this.mascota = mascota;
	}
}