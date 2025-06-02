package vista;

import modelo.PropietarioCRUD;
import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazVivienda {
	
	private static Scanner sc = new Scanner(System.in);
    
	// OPCIÓN CONSULTAR UNA VIVIENDA DADO SU CÓDIGO //
	public static String solicitarCod() {
				
		// Solicitar ID del inquilino a buscar //
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): ");		

		String cod = sc.nextLine().toUpperCase();
		
		return cod;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE VIVIENDA CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
		System.out.println(("-").repeat(170));
		System.out.printf("%-15s %-20s %-50s %-15s %-20s %-15s %-15s\n", 
			"|  CÓDIGO", "|  ID PROPIETARIO", "|  DIRECCIÓN", "|  PRECIO", "|  SUPERFICIE", "|  MASCOTAS", "|  TIPO");
		System.out.println(("-").repeat(170));

		String mascotas = "NO";	
		if (rs.getInt("mascotas") == 1) mascotas = "SÍ";

		String descripcion = rs.getString("descripcion");
		if (descripcion == null) descripcion = "(VACÍO)";

		System.out.printf("%-15s %-20s %-50s %-15s %-20s %-15s %-15s",
			"|  " + rs.getString("cod"),
			"|  " + rs.getInt("id_propietario"),
			"|  " + rs.getString("direccion"),
			"|  " + (rs.getFloat("precio") + " €").replace('.', ','),
			"|  " + (rs.getFloat("superficie") + " m2").replace('.', ','),
			"|  " + mascotas,
			"|  " + rs.getString("nombre").toUpperCase());

		System.out.println("\n" + ("-").repeat(170));

		System.out.println("|  DESCRIPCIÓN -> " + descripcion);

		System.out.println(("-").repeat(170));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    // SOLICITAR DATOS DE VIVIENDA PARA AÑADIR //
	public static Vivienda solicitarDatos(Connection conex, Vivienda vivienda) throws SQLException {
		
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): ");
		String codVivienda = sc.nextLine().trim().toUpperCase();

		
		System.out.print("  - ID PROPIETARIO: ");
		int idPropietario = -1;
		if (sc.hasNextInt()) {
			idPropietario = sc.nextInt();
			sc.nextLine();
		} 
		else {
			sc.nextLine();
			System.out.println("** ENTRADA INCORRECTA. CANCELANDO OPERACIÓN **");
			return vivienda;
		}

		
		// VERIFICAR SI EXISTE PROPIETARIO //
		if (!PropietarioCRUD.consultar(conex, idPropietario).next()) {
			System.out.println("** EL ID INTRODUCIDO NO EXISTE **");
			return vivienda;
		}

		
		System.out.print("  - DIRECCIÓN: ");
		String direccion = sc.nextLine().trim();
		direccion = direccion.isEmpty() ? null : direccion;

		
		System.out.print("  - PRECIO -> (0,0): ");
		String precioString = sc.nextLine().trim().replace(',', '.');
		float precio;
		try {
			precio = Float.parseFloat(precioString);
		} catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0) **\n");
			precio = 0;
		}

		
		System.out.print("  - SUPERFICIE -> (0,0): ");
		String superficieString = sc.nextLine().trim().replace(',', '.');
		float superficie;
		try {
			superficie = Float.parseFloat(superficieString);
		} catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0) **\n");
			superficie = 0;
		}

		
		System.out.print("  - DESCRIPCIÓN: ");
		String descripcion = sc.nextLine().trim();
		descripcion = descripcion.isEmpty() ? null : descripcion;

		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine().trim();
		int mascotas;
		if (mascotasString.equalsIgnoreCase("S")) {
			mascotas = 1;
		} else if (mascotasString.equalsIgnoreCase("N")) {
			mascotas = 0;
		} else {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (N) **\n");
			mascotas = 0;
		}

		
		System.out.print(""
			+ "  - TIPO VIVIENDA\n"
			+ "      1. Apartamento\n"
			+ "      2. Ático\n"
			+ "      3. Casa\n"
			+ "    OPCIÓN: "); 
		int tipo = 1;
		if (sc.hasNextInt()) {
			tipo = sc.nextInt();
			sc.nextLine();
		} else {
			sc.nextLine();
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (1) **");
		}

		return new Vivienda(codVivienda, idPropietario, direccion, precio, superficie, descripcion, mascotas, tipo);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SOLICITAR DATOS DE VIVIENDA PARA MODIFICAR //
	public static Vivienda solicitarDatosMod(Vivienda vivienda) {
		
		String codVivienda = vivienda.getCod();
		
		System.out.println("\nINTRODUCE LOS NUEVOS DATOS:");
		
		System.out.print("  - ID PROPIETARIO: ");
		String idPropietarioString = sc.nextLine().trim();
		int idPropietario;
		try {
			idPropietario = idPropietarioString.isEmpty() ? 
				vivienda.getIdPropietario() : Integer.parseInt(idPropietarioString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			idPropietario = vivienda.getIdPropietario();
		}
		
		
		System.out.print("  - DIRECCIÓN: ");
		String direccion = sc.nextLine().trim();
		direccion = direccion.isEmpty() ? vivienda.getDireccion(): direccion;

		
		System.out.print("  - PRECIO -> (0,0): ");
		String precioString = sc.nextLine().trim().replace(',', '.');
		float precio;
		try {
			precio = precioString.isEmpty() ?
				vivienda.getPrecio() : Float.parseFloat(precioString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			precio = vivienda.getPrecio();
		}

		
		System.out.print("  - SUPERFICIE -> (0,0): "); 
		String superficieString = sc.nextLine().trim().replace(',', '.');
		float superficie;
		try {
			superficie = superficieString.isEmpty() ?
				vivienda.getSuperficie() : Float.parseFloat(superficieString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			superficie = vivienda.getSuperficie();
		}

		
		System.out.print("  - DESCRIPCIÓN: "); 
		String descripcion = sc.nextLine().trim();
		descripcion = descripcion.isEmpty() ? vivienda.getDescripcion() : descripcion;

		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine().trim();
		int mascotas;
		if (mascotasString.isEmpty()) {
			mascotas = vivienda.getMascotas();
		}
		else if (mascotasString.equalsIgnoreCase("S")) {
			mascotas = 1;
		}
		else if (mascotasString.equalsIgnoreCase("N")) {
			mascotas = 0;
		}
		else {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			mascotas = vivienda.getMascotas();
		}
		
		
		System.out.print(""
			+ "  - TIPO VIVIENDA\n"
			+ "      1. Apartamento\n"
			+ "      2. Ático\n"
			+ "      3. Casa\n"
			+ "    OPCIÓN: "); 
		String tipoString = sc.nextLine().trim();
		int tipo;
		try {
			tipo = tipoString.isEmpty() ?
				vivienda.getTipo() : Integer.parseInt(tipoString);
		}
		catch (Exception e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			tipo = vivienda.getTipo();
		}
				
		return new Vivienda(codVivienda, idPropietario, direccion, precio, superficie, descripcion, mascotas, tipo);
	}
}