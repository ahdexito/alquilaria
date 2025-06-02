package vista;

import modelo.PropietarioCRUD;
import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazVivienda {
	
	private static Scanner sc = new Scanner(System.in);
    
    // SOLICITAR DATOS DE VIVIENDA PARA AÑADIR //
	public static Vivienda solicitarDatos(Connection conex, Vivienda vivienda) throws SQLException {
		
		int idPropietario = -1;
				
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): "); 
		vivienda.setCod(sc.nextLine().toUpperCase());
		
		/* Solicitar los datos de la vivienda y guardarlos en variables */
		System.out.print("  - ID PROPIETARIO: "); 
		if (sc.hasNextInt()) {
			idPropietario = sc.nextInt();
			sc.nextLine();
		}
		
		/* Comprobar si existe el propietario */
		if (PropietarioCRUD.consultar(conex, idPropietario).next()) {
			
			vivienda.setIdPropietario(idPropietario);
			
			System.out.print("  - DIRECCIÓN: "); 
			vivienda.setDireccion(sc.nextLine());

			
			System.out.print("  - PRECIO -> (0,0): "); 
			String precioString = sc.nextLine().replace(',', '.');
			try {
				float precio = Float.parseFloat(precioString);
				vivienda.setPrecio(precio);
			}
			catch (Exception e) {
				System.out.println("    ERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0)\n");
			}

			
			System.out.print("  - SUPERFICIE -> (0,0): "); 
			String superficieString = sc.nextLine().replace(',', '.');
			try {
				float superficie = Float.parseFloat(superficieString);
				vivienda.setSuperficie(superficie);
			}
			catch (NumberFormatException e) {
				System.out.println("    ERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0)\n");
			}

			
			System.out.print("  - DESCRIPCIÓN: "); 
			vivienda.setDescripcion(sc.nextLine());

			
			System.out.print("  - MASCOTA(S) -> (S | N): ");
			String mascotasString = sc.nextLine();
			if (mascotasString.trim().toUpperCase().equals("S")) vivienda.setMascotas(1);
			if (mascotasString.trim().toUpperCase().equals("N")) vivienda.setMascotas(0);

			
			System.out.print(""
				+ "  - TIPO VIVIENDA\n"
				+ "      1. Apartamento\n"
				+ "      2. Ático\n"
				+ "      3. Casa\n"
				+ "    OPCIÓN: "); 
			if (sc.hasNextInt()) {
				vivienda.setTipo(sc.nextInt());
				sc.nextLine();
			}
			
			return vivienda;
		}
		else {
			System.out.print("\nEL ID INTRODUCIDO NO EXISTE");
			return vivienda;
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
		
		if (rs.next()) {
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
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	// SOLICITAR DATOS DE VIVIENDA PARA MODIFICAR //
	public static Vivienda solicitarDatosMod(Connection conex, Vivienda vivienda) throws SQLException {
		
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): ");
		String cod = sc.nextLine().trim().toUpperCase();
		cod = cod.isEmpty() ? vivienda.getCod() : cod;
		
		
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
				
		return new Vivienda(cod, idPropietario, direccion, precio, superficie, descripcion, mascotas, tipo);
	}
}