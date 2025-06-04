package modelo;

import java.sql.*;

/**
 * Clase que gestiona las operaciones CRUD sobre la tabla de contratos en la base de datos.
 * Incluye métodos para crear, consultar, modificar y eliminar contratos.
 * 
 * @author Ángel García Smakula
 */

public class ContratoCRUD {
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Inserta un nuevo contrato en la base de datos.
     * 
     * @param conex Conexión activa a la base de datos.
     * @param contrato Objeto contrato con los datos a insertar.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
	
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
    /**
     * Consulta un contrato en la base de datos según ID del inquilino, código de vivienda y fecha de inicio.
     * 
     * @param conex Conexión activa a la base de datos.
     * @param idInquilino ID del inquilino asociado al contrato.
     * @param codVivienda Código de la vivienda asociada.
     * @param fechaInicio Fecha de inicio del contrato.
     * @return Resultado de la consulta como un ResultSet.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */
	
	public static ResultSet consultar(Connection conex, int idInquilino, String codVivienda, Date fechaInicio) throws SQLException {
		
		String query = "SELECT * FROM contrato WHERE id_inquilino = ? AND cod_vivienda = ? AND fecha_inicio = ?";
		
		PreparedStatement ps = conex.prepareStatement(query);
		
		ps.setInt(1, idInquilino);
		ps.setString(2, codVivienda);
		ps.setDate(3, fechaInicio);
		
		return ps.executeQuery();
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Modifica un contrato existente en la base de datos.
     * 
     * @param conex Conexión activa a la base de datos.
     * @param contrato Objeto contrato con los nuevos datos.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */

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
    /**
     * Elimina un contrato de la base de datos según ID del inquilino, código de vivienda y fecha de inicio.
     * 
     * @param conex Conexión activa a la base de datos.
     * @param idInquilino ID del inquilino asociado al contrato.
     * @param codVivienda Código de la vivienda asociada.
     * @param fechaInicio Fecha de inicio del contrato.
     * @throws SQLException Si ocurre un error al ejecutar la consulta.
     */

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
