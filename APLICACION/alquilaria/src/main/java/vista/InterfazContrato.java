package vista;

import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import modelo.*;

/**
 *
 * @author Ángel García Smakula
 */
public class InterfazContrato {
	
	private static Scanner sc = new Scanner(System.in);
    
	// SOLICITAR FECHA DE INICIO DE CONTRATO //
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
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE CONTRATO CON FORMATO TIPO TABLA //
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
	
    // SOLICITAR DATOS DE CONTRATO PARA AÑADIR //
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
	
	// SOLICITAR DATOS DE CONTRATO PARA MODIFICAR //
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