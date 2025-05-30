package modelo;

import modelo.Vivienda;
import java.sql.*;

public class ViviendaCRUD {
    
    // CREAR UNA VIVIENDA //
    public static void crear(Connection conex, Vivienda vivienda) throws SQLException {
		
		String cod = vivienda.getCod();
		int idPropietario = vivienda.getIdPropietario();
		String direccion = vivienda.getDireccion();
		float precio = vivienda.getPrecio();
		float superficie = vivienda.getSuperficie();
		String descripcion = vivienda.getDescripcion();
		int mascotas = vivienda.getMascotas();
		int tipo = vivienda.getTipo();
		
		/* Asegurar que la variable que se envía sea NULL si no se insertó ningún valor 
		para que se recoja el error en campos con restricción NOTNULL */
		cod = cod.isEmpty() ? null : cod;
		direccion = direccion.isEmpty() ? null : direccion;
		precio = (precio == -1) ? 0 : precio;
		superficie = (superficie == -1) ? 0 : superficie;
		descripcion = descripcion.isEmpty() ? null : descripcion;
		mascotas = (mascotas == -1) ? 1 : mascotas;
		
		try {			
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
	public static void modificar(Connection conex, Vivienda viviendaSinMod, Vivienda viviendaMod) throws SQLException {
		
		String cod = viviendaMod.getCod();
		int idPropietario = viviendaMod.getIdPropietario();
		String direccion = viviendaMod.getDireccion();
		float precio = viviendaMod.getPrecio();
		float superficie = viviendaMod.getSuperficie();
		String descripcion = viviendaMod.getDescripcion();
		int mascotas = viviendaMod.getMascotas();
		int tipo = viviendaMod.getTipo();
		
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
}