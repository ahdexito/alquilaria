package modelo;

import java.sql.*;

public class Vivienda {
    
    // ATRIBUTOS //
	private String cod, direccion, descripcion;
	private int idPropietario = -1, tipo, mascotas = -1;
	private float precio = -1, superficie = -1;
	
	// CONSTRUCTORES //
	public Vivienda() {
	}

	public Vivienda(String cod, int idPropietario, String direccion, float precio, float superficie, String descripcion, int mascotas, int tipo) {
		this.cod = cod;
		this.idPropietario = idPropietario;
		this.direccion = direccion;
		this.precio = precio;
		this.superficie = superficie;
		this.descripcion = descripcion;
		this.mascotas = mascotas;
		this.tipo = tipo;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CREAR UNA VIVIENDA //
    public void crear(Connection conex) throws SQLException {
		
		try {
			/* Asegurar que la variable que se envía sea NULL si no se insertó ningún valor 
			para que se recoja el error en campos con restricción NOTNULL */
			cod = cod.isEmpty() ? null : cod;
			direccion = direccion.isEmpty() ? null : direccion;
			precio = (precio == -1) ? 0 : precio;
			superficie = (superficie == -1) ? 0 : superficie;
			descripcion = descripcion.isEmpty() ? null : descripcion;
			mascotas = (mascotas == -1) ? 1 : mascotas;
			
			String query = "INSERT INTO vivienda(cod, id_propietario, direccion, precio, superficie, descripcion, mascotas, tipo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);

			ps.setString(1, cod);
			ps.setInt(2, idPropietario);
			ps.setString(3, direccion);
			ps.setFloat(4, precio);
			ps.setFloat(5, superficie);
			ps.setString(6, descripcion);
			ps.setInt(7, mascotas);
			ps.setInt(8, tipo);

			int filas = ps.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR UNA VIVIENDA //
	public static ResultSet consultar(Connection conex, String cod) throws SQLException {
		
		String query = "SELECT * FROM vivienda WHERE cod = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setString(1, cod);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR UNA VIVIENDA //
	public void modificar(Connection conex, Vivienda viviendaSinMod) throws SQLException {
		
		/* Comprobar los campos vacíos. Si lo están se les asigna el valor previo */
		
		cod = cod.trim().isEmpty() ? viviendaSinMod.getCod() : cod;
		idPropietario = (idPropietario == -1) ? viviendaSinMod.getIdPropietario() : idPropietario;
		direccion = direccion.trim().isEmpty() ? viviendaSinMod.getDireccion() : direccion;
		precio = (precio == -1) ? viviendaSinMod.getPrecio() : precio;
		superficie = (superficie == -1) ? viviendaSinMod.getSuperficie(): superficie;
		descripcion = descripcion.trim().isEmpty() ? viviendaSinMod.getDescripcion() : descripcion;
		mascotas = (mascotas == -1) ? viviendaSinMod.getMascotas() : mascotas;
		tipo = (tipo == -1) ? viviendaSinMod.getTipo(): tipo;
		
		try {
			String query = "UPDATE vivienda SET id_propietario = ?, direccion = ?, precio = ?, superficie = ?, descripcion = ?, mascotas = ?, tipo = ? WHERE cod = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setInt(1, idPropietario);
			ps.setString(2, direccion);
			ps.setFloat(3, precio);
			ps.setFloat(4, superficie);
			ps.setString(5, descripcion);
			ps.setInt(6, mascotas);
			ps.setInt(7, tipo);
			ps.setString(8, cod);

			int filas = ps.executeUpdate();

			System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// ELIMINAR UNA VIVIENDA //
	public static void eliminar(Connection conex, String cod) throws SQLException {
		
		String query = "DELETE FROM vivienda WHERE cod = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setString(1, cod);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SETTERS Y GETTERS //
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getMascotas() {
		return mascotas;
	}

	public void setMascotas(int mascotas) {
		this.mascotas = mascotas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getSuperficie() {
		return superficie;
	}

	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}		
}