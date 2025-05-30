package vista;

import modelo.PropietarioCRUD;
import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazVivienda {
	
	private static Scanner sc = new Scanner(System.in);
    
    // OPCIÓN AÑADIR NUEVA VIVIENDA DADOS SUS DATOS //
	public static Vivienda solicitarDatos(Connection conex, Vivienda vivienda) throws SQLException {
		
		int idPropietario = -1;
				
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): "); 
		vivienda.setCod(sc.nextLine());
		
		/* Solicitar los datos de la nueva vivienda y guardarlos en variables */
		System.out.print("  - ID PROPIETARIO: "); 
		if (sc.hasNextInt()) {
			vivienda.setIdPropietario(sc.nextInt());
			sc.nextLine();
		}
			
		/* Comprobar si existe el propietario */
		if (PropietarioCRUD.consultar(conex, idPropietario).next()) {
			System.out.println("EL USUARIO EXISTE");
		}
		else System.out.println("ERROR: EL ID INTRODUCIDO NO EXISTE");
			
		System.out.print("  - DIRECCIÓN: "); 
		vivienda.setDireccion(sc.nextLine());

		System.out.print("  - PRECIO -> (0,0): "); 
		String precioString = sc.nextLine().replace(',', '.');
		try {
			float precio = Float.parseFloat(precioString);
			vivienda.setPrecio(precio);
		}
		catch (Exception e) {
			System.out.println("\nERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> 0\n");
		}

		System.out.print("  - SUPERFICIE -> (0,0): "); 
		String superficieString = sc.nextLine().replace(',', '.');
		try {
			float superficie = Float.parseFloat(superficieString);
			vivienda.setSuperficie(superficie);
		}
		catch (NumberFormatException e) {
			System.out.println("\nERROR: VALOR INCORRECTO. SE ESTABLECERÁ EL VALOR POR DEFECTO -> 0\n");
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// OPCIÓN CONSULTAR UNA VIVIENDA DADO SU CÓDIGO //
	public static String solicitarCod() {
		
		System.out.println("--------------- [ SOLICITUD DE CÓDIGO ] --------------\n");
		
		// Solicitar ID del inquilino a buscar //
		System.out.print("  - CÓDIGO VIVIENDA: ");		

		String cod = sc.nextLine();
		
		return cod;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE VIVIENDA CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
		if (rs.next()) {
			System.out.println(("-").repeat(170));
			System.out.printf("%-10s %-20s %-40s %-40s %-30s %-10s\n", 
				"|  ID", "|  DNI", "|  NOMBRE", "|  CORREO", "|  TELÉFONO", "|  MASCOTAS");
			System.out.println(("-").repeat(170));
			
			String mascotas = "NO";	
			if (rs.getInt("mascota") == 1) mascotas = "SÍ";
			
			System.out.printf("%-10s %-20s %-40s %-40s %-30s %-10s",
				"|  " + rs.getInt("id"),
				"|  " + rs.getString("DNI"),
				"|  " + rs.getString("apellidos") + ", " + rs.getString("nombre"),
				"|  " + rs.getString("correo"),
				"|  " + rs.getString("telefono"),
				"|  " + mascotas);
			
			System.out.println("\n" + ("-").repeat(170));
		}
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
}