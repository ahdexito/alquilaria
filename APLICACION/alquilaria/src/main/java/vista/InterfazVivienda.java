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
				System.out.println("    ERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> 0\n");
			}

			
			System.out.print("  - SUPERFICIE -> (0,0): "); 
			String superficieString = sc.nextLine().replace(',', '.');
			try {
				float superficie = Float.parseFloat(superficieString);
				vivienda.setSuperficie(superficie);
			}
			catch (NumberFormatException e) {
				System.out.println("    ERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> 0\n");
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
			
			System.out.printf("%-15s %-20s %-50s %-15s %-20s %-15s %-15s",
				"|  " + rs.getString("cod"),
				"|  " + rs.getInt("id_propietario"),
				"|  " + rs.getString("direccion"),
				"|  " + (rs.getFloat("precio") + " €").replace('.', ','),
				"|  " + (rs.getFloat("superficie") + " m2").replace('.', ','),
				"|  " + mascotas,
				"|  " + rs.getString("nombre").toUpperCase());
			
			System.out.println("\n" + ("-").repeat(170));
			
			System.out.println("|  DESCRIPCIÓN -> " + rs.getString("descripcion"));
			
			System.out.println(("-").repeat(170));
		}
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	// SOLICITAR DATOS DE VIVIENDA PARA MODIFICAR //
	public static Vivienda solicitarDatosMod(Connection conex, Vivienda vivienda) throws SQLException {
		
		int idPropietario = -1;
		
		System.out.println("\nINTRODUCE LOS NUEVOS DATOS:");	
		
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): "); 
		vivienda.setCod(sc.nextLine().toUpperCase());
		
		/* Solicitar los datos de la vivienda y guardarlos en variables */
		System.out.print("  - ID PROPIETARIO: ");
		String idString = sc.nextLine();
		if (!idString.isBlank()) {
			try {
				idPropietario = Integer.parseInt(idString);
				System.out.println("es número");
				
			}
			catch (NumberFormatException e) {
				System.out.println("no es número");
			}
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
				System.out.println("    ERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> 0\n");
			}

			
			System.out.print("  - SUPERFICIE -> (0,0): "); 
			String superficieString = sc.nextLine().replace(',', '.');
			try {
				float superficie = Float.parseFloat(superficieString);
				vivienda.setSuperficie(superficie);
			}
			catch (NumberFormatException e) {
				System.out.println("    ERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> 0\n");
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
}