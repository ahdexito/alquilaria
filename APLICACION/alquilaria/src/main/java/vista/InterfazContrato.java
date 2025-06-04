package vista;

import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import modelo.*;

/**
 * Clase que gestiona la interacción con el usuario para las operaciones relacionadas con contratos.
 * Incluye métodos para solicitar datos de entrada, como fechas y detalles del contrato,
 * y para imprimir los resultados de consultas de contratos en un formato legible.
 *
 * @author Ángel García Smakula
 */

public class InterfazContrato {
	
	private static Scanner sc = new Scanner(System.in);
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario una fecha en formato "dd/mm/aaaa" y la convierte a un objeto `java.sql.Date`.
     * Valida el formato de la fecha introducida y maneja posibles errores de parsing.
     *
     * @return Un objeto `java.sql.Date` con la fecha introducida, o `null` si la entrada es incorrecta o vacía.
     */
	
	public static Date solicitarFecha() {
				
		// Solicitar fecha del contrato a buscar //
		System.out.print("  - FECHA INICIO -> (dd/mm/aaaa): ");
		String fechaInicioString = sc.nextLine().trim();
		if (!fechaInicioString.isEmpty()) {
			try {
				LocalDate fechaInicioLocal = LocalDate.parse(fechaInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				return Date.valueOf(fechaInicioLocal);
			}
			catch (DateTimeParseException e) {
				System.out.println("** ENTRADA INCORRECTA. INTÉNTALO DE NUEVO **");
				return null;
			}
		}
		else {
			System.out.println("** ENTRADA INCORRECTA. INTÉNTALO DE NUEVO **");
			return null;
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Imprime los datos de un contrato obtenidos de un ResultSet en un formato de tabla.
     * Formatea las fechas a "dd/MM/yyyy", el precio con el símbolo del euro y la coma como separador decimal,
     * y el estado en mayúsculas para una presentación clara.
     *
     * @param rs El ResultSet que contiene los datos de la consulta de un contrato.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
	
	public static void imprimir(ResultSet rs) throws SQLException {
		
		System.out.println(("-").repeat(160));
		System.out.printf("%-20s %-20s %-30s %-30s %-20s %-30s\n", 
			"|  ID INQUILINO", "|  COD VIVIENDA", "|  FECHA INICIO", "|  FECHA FIN", "|  PRECIO", "|  ESTADO");
		System.out.println(("-").repeat(160));

		String fechaInicio = rs.getDate("fecha_inicio").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String fechaFin = rs.getDate("fecha_fin").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		System.out.printf("%-20s %-20s %-30s %-30s %-20s %-30s",
			"|  " + rs.getInt("id_inquilino"),
			"|  " + rs.getString("cod_vivienda"),
			"|  " + fechaInicio,
			"|  " + fechaFin,
			"|  " + (rs.getFloat("precio") + " €").replace('.', ','),
			"|  " + rs.getString("estado").toUpperCase());

		System.out.println("\n" + ("-").repeat(160));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario los datos para la creación de un nuevo contrato.
     * Permite al usuario introducir el ID del inquilino, el código de la vivienda,
     * las fechas de inicio y fin, el precio y el estado del contrato.
     * Incluye validación de formato para fechas y números, así como manejo de valores por defecto
     * en caso de entradas incorrectas o vacías.
     *
     * @param contrato Objeto Contrato que se utiliza para obtener valores por defecto si el usuario deja campos vacíos.
     * @return Un nuevo objeto Contrato con los datos introducidos o los valores por defecto.
     */
	
	public static Contrato solicitarDatos(Contrato contrato) {
		
		System.out.print("  - ID INQUILINO: ");
		String idInquilinoString = sc.nextLine().trim();
		int idInquilino;
		try {
			idInquilino = idInquilinoString.isEmpty() ? 
				contrato.getIdInquilino() : Integer.parseInt(idInquilinoString);
		}
		catch (NumberFormatException e) {
			idInquilino = contrato.getIdInquilino();
		}
		
		
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): ");
		String codVivienda = sc.nextLine().trim().toUpperCase();
		codVivienda = codVivienda.isEmpty() ? contrato.getCodVivienda() : codVivienda;
		
		
		System.out.print("  - FECHA INICIO -> (dd/mm/aaaa): ");
		String fechaInicioString = sc.nextLine().trim();
		Date fechaInicio = contrato.getFechaInicio();
		if (!fechaInicioString.isEmpty()) {
			try {
				LocalDate fechaInicioLocal = LocalDate.parse(fechaInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				fechaInicio = Date.valueOf(fechaInicioLocal);
			}
			catch (DateTimeParseException e) {
				fechaInicio = contrato.getFechaInicio();
			}
		}
		System.out.println("fecha: " + fechaInicio);
		
		System.out.print("  - FECHA FIN -> (dd/mm/aaaa): ");
		String fechaFinString = sc.nextLine().trim();
		Date fechaFin = contrato.getFechaFin();
		if (!fechaFinString.isEmpty()) {
			try {
				LocalDate fechaFinLocal = LocalDate.parse(fechaFinString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				fechaFin = Date.valueOf(fechaFinLocal);
			}
			catch (DateTimeParseException e) {
				fechaFin = contrato.getFechaFin();
			}
		}
		
		
		System.out.print("  - PRECIO -> (0,0): ");
		String precioString = sc.nextLine().trim().replace(',', '.');
		float precio;
		
		if (!precioString.isEmpty()) {
			try {
				precio = Float.parseFloat(precioString);
			}
			catch (NumberFormatException e) {
				System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0) **\n");
				precio = 0;
			}
		}
		else {
			precio = 0;
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0) **\n");
		}
		
		
		System.out.print(""
				+ "  - ESTADO\n"
				+ "      1. Pendiente\n"
				+ "      2. Activo\n"
				+ "      3. Vencido\n"
				+ "    OPCIÓN: "); 
		String estado = sc.nextLine().trim();
		switch (estado) {
			case "1" -> estado = "PENDIENTE";
			case "2" -> estado = "ACTIVO";
			case "3" -> estado = "VENCIDO";
			default -> {
				estado = "PENDIENTE";
				System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (PENDIENTE) **");
			}			
		}
		
		return new Contrato(idInquilino, codVivienda, fechaInicio, fechaFin, precio, estado);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario los datos para la modificación de un contrato existente.
     * Los campos como ID de inquilino, código de vivienda y fecha de inicio no se solicitan
     * ya que son las claves primarias para identificar el contrato a modificar.
     * Permite al usuario actualizar la fecha de fin, el precio y el estado del contrato.
     * Incluye validación de formato para fechas y números, y mantiene los valores previos
     * si la entrada es incorrecta o vacía.
     *
     * @param contrato Objeto Contrato que contiene los datos actuales del contrato a modificar,
     * los cuales se utilizan como valores por defecto.
     * @return Un nuevo objeto Contrato con los datos actualizados, manteniendo las claves primarias originales.
     */
	
	public static Contrato solicitarDatosMod(Contrato contrato) {
		
		int idInquilino = contrato.getIdInquilino();
		String codVivienda = contrato.getCodVivienda();
		Date fechaInicio = contrato.getFechaInicio();
		
		System.out.println("\nINTRODUCE LOS NUEVOS DATOS:");
		
		System.out.print("  - FECHA FIN -> (dd/mm/aaaa): ");
		String fechaFinString = sc.nextLine().trim();
		Date fechaFin = contrato.getFechaFin();
		if (!fechaFinString.isEmpty()) {
			try {
				LocalDate fechaFinLocal = LocalDate.parse(fechaFinString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				fechaFin = Date.valueOf(fechaFinLocal);
			}
			catch (DateTimeParseException e) {
				System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
				fechaFin = contrato.getFechaFin();
			}
		}
		
		
		System.out.print("  - PRECIO -> (0,0): ");
		String precioString = sc.nextLine().trim().replace(',', '.');
		float precio;
		try {
			precio = precioString.isEmpty() ?
				contrato.getPrecio() : Float.parseFloat(precioString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			precio = contrato.getPrecio();
		}
		
		
		System.out.print(""
				+ "  - ESTADO\n"
				+ "      1. Pendiente\n"
				+ "      2. Activo\n"
				+ "      3. Vencido\n"
				+ "    OPCIÓN: "); 
		String estadoEntrada = sc.nextLine().trim();
		String estado = switch (estadoEntrada) {
			case "1" -> "PENDIENTE";
			case "2" -> "ACTIVO";
			case "3" -> "VENCIDO";
			case "" -> contrato.getEstado() == null ? "PENDIENTE" : contrato.getEstado();
			default -> contrato.getEstado() == null ? "PENDIENTE" : contrato.getEstado();
		};
		
		return new Contrato(idInquilino, codVivienda, fechaInicio, fechaFin, precio, estado);
	}
}