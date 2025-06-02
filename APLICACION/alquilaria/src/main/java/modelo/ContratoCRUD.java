package modelo;

import java.sql.*;

public class ContratoCRUD {
    
    // CREAR UN CONTRATO //
    public static void crear(Connection conex, Contrato contrato) throws SQLException {
		
		try {			
			String query = "INSERT INTO contrato(id_inquilino, cod_vivienda, fecha_inicio, fecha_fin, precio, estado) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setInt(1, contrato.getIdInquilino());
			ps.setString(2, contrato.getCodVivienda());
			ps.setDate(3, contrato.getFechaInicio());
			ps.setDate(4, contrato.getFechaFin());
			ps.setFloat(5, contrato.getPrecio());
			ps.setString(6, contrato.getEstado());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR UN CONTRATO //
	public static ResultSet consultar(Connection conex, int idInquilino, String codVivienda, Date fechaInicio) throws SQLException {
		
		String query = "SELECT * FROM contrato WHERE id_inquilino = ? AND cod_vivienda = ? AND fecha_inicio = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, idInquilino);
		ps.setString(2, codVivienda);
		ps.setDate(3, fechaInicio);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR UN CONTRATO //
	public static void modificar(Connection conex, Contrato contrato) throws SQLException {	
		
		try {
			String query = "UPDATE contrato SET fecha_fin = ?, precio = ?, estado = ? WHERE id_inquilino = ? AND cod_vivienda = ? AND fecha_inicio = ?";
			
			PreparedStatement ps = conex.prepareStatement(query);
			
			ps.setDate(1, contrato.getFechaFin());
			ps.setFloat(2, contrato.getPrecio());
			ps.setString(3, contrato.getEstado());
			ps.setInt(4, contrato.getIdInquilino());
			ps.setString(5, contrato.getCodVivienda());
			ps.setDate(6, contrato.getFechaInicio());

			int filas = ps.executeUpdate();

			System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
		}
		
		catch (SQLException e) {
			System.out.println("\nERROR: " + e.getMessage());
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// ELIMINAR UN CONTRATO //
	public static void eliminar(Connection conex, int idInquilino, String codVivienda, Date fechaInicio) throws SQLException {
		
		String query = "DELETE FROM contrato WHERE id_inquilino = ? AND cod_vivienda = ? AND fecha_inicio = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, idInquilino);
		ps.setString(2, codVivienda);
		ps.setDate(3, fechaInicio);
		
		int filas = ps.executeUpdate();
		
		System.out.println("\n** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
}
