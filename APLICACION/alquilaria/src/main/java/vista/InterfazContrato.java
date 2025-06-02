package vista;

import modelo.ContratoCRUD;
import java.util.Scanner;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import modelo.*;

public class InterfazContrato {
	
	private static Scanner sc = new Scanner(System.in);
    
    // SOLICITAR DATOS DE CONTRATO PARA AÑADIR O MODIFICAR //
	public static Contrato solicitarDatos(Connection conex, Contrato contrato) throws SQLException {
		
		System.out.print("  - ID INQUILINO: ");
		String idInquilinoString = sc.nextLine().trim();
		int idInquilino;
		try {
			idInquilino = idInquilinoString.isEmpty() ? 
				contrato.getIdInquilino() : Integer.parseInt(idInquilinoString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
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
				System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
				fechaInicio = contrato.getFechaInicio();
			}
		}
		
		
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
		String estado = sc.nextLine().trim();
		estado = estado.isEmpty() ? contrato.getEstado() : estado;
		switch (estado) {
			case "1" -> estado = "PENDIENTE";
			case "2" -> estado = "ACTIVO";
			case "3" -> estado = "VENCIDO";
			default -> estado = "PENDIENTE";
		}
		
		return new Contrato(idInquilino, estado, codVivienda, fechaInicio, fechaFin, precio);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SOLICITAR FECHA DE INICIO DE CONTRATO //
	public static Date solicitarIdCod() {
				
		// Solicitar fecha del contrato a buscar //
		System.out.print("  - FECHA INICIO -> (dd/mm/aaaa): ");
		String fechaInicioString = sc.nextLine().trim();
		if (!fechaInicioString.isEmpty()) {
			try {
				LocalDate fechaInicioLocal = LocalDate.parse(fechaInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				return Date.valueOf(fechaInicioLocal);
			}
			catch (DateTimeParseException e) {
				System.out.println("** ENTRADA INCORRECTA. INTÉNTALO DE NUEVO **\n");
				return null;
			}
		}
		else {
			System.out.println("** ENTRADA INCORRECTA. INTÉNTALO DE NUEVO **\n");
			return null;
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE VIVIENDA CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
		if (rs.next()) {
			System.out.println(("-").repeat(170));
			System.out.printf("%-20s %-20s %-30 %-30s %-20s %-30s\n", 
				"|  ID INQUILINO", "|  COD VIVIENDA", "|  FECHA INICIO", "|  FECHA FIN", "|  PRECIO", "|  ESTADO");
			System.out.println(("-").repeat(170));
			
			System.out.printf("%-20s %-20s %-30 %-30s %-20s %-30s",
				"|  " + rs.getInt("id_inquilino"),
				"|  " + rs.getString("cod_vivienda"),
				"|  " + rs.getDate("fecha_inicio"),
				"|  " + rs.getDate("fecha_fin"),
				"|  " + (rs.getFloat("precio") + " €").replace('.', ','),
				"|  " + rs.getString("estado").toUpperCase());
			
			System.out.println("\n" + ("-").repeat(170));
						
			System.out.println(("-").repeat(170));
		}
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
}