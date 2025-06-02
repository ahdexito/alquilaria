package modelo;

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
		
		try {
			cod = cod.isEmpty() ? null : cod;
			direccion = direccion.isEmpty() ? null : direccion;
			precio = (precio == -1) ? 0 : precio;
			superficie = (superficie == -1) ? 0 : superficie;
			descripcion = descripcion.isEmpty() ? null : descripcion;
			mascotas = (mascotas == -1) ? 1 : mascotas;
		}
		catch (NullPointerException e) {
			System.out.print("ERROR: ALGUNO DE LOS CAMPOS NO ES CORRECTO");
		}
		
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

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR UNA VIVIENDA //
	public static ResultSet consultar(Connection conex, String cod) throws SQLException {
		
		String query = "SELECT * FROM vivienda v JOIN tipo_vivienda t ON v.tipo = t.numero WHERE cod = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setString(1, cod);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR UNA VIVIENDA //
	public static void modificar(Connection conex, Vivienda vivienda, String cod) throws SQLException {	
		
		try {
			String query = "UPDATE vivienda SET cod = ?, id_propietario = ?, direccion = ?, precio = ?, superficie = ?, descripcion = ?, mascotas = ?, tipo = ? WHERE cod = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setString(1, vivienda.getCod());
			ps.setInt(2, vivienda.getIdPropietario());
			ps.setString(3, vivienda.getDireccion());
			ps.setFloat(4, vivienda.getPrecio());
			ps.setFloat(5, vivienda.getSuperficie());
			ps.setString(6, vivienda.getDescripcion());
			ps.setInt(7, vivienda.getMascotas());
			ps.setInt(8, vivienda.getTipo());
			ps.setString(9, cod);

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
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
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}