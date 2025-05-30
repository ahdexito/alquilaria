package modelo;

public class Inquilino {
    
    // ATRIBUTOS //
	private int id, mascotas = -1;
	private String dni, nombre, apellidos, correo, telefono;

	// CONSTRUCTORES //
	public Inquilino() {
	}
	
	public Inquilino(int id, String dni, String nombre, String apellidos, String correo, String telefono, int mascotas) {
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.mascotas = mascotas;
	}
	
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
	
	public int getMascotas() {
		return mascotas;
	}
	
	public void setMascotas(int mascotas) {
		this.mascotas = mascotas;
	}
}