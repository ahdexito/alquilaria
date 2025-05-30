package vista;

import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazVivienda {
	
	private static Scanner sc = new Scanner(System.in);
    
    // OPCIÓN AÑADIR NUEVA VIVIENDA DADOS SUS DATOS //
	public static Vivienda crear(Connection conex, Vivienda vivienda) throws SQLException {
		
		int idPropietario = -1;
		
		System.out.println("-------------- [ AÑADIR VIVIENDA ] ------------\n");
		
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): "); 
		vivienda.setCod(sc.nextLine());
		
		/* Solicitar los datos de la nueva vivienda y guardarlos en variables */
		System.out.print("  - ID PROPIETARIO: "); 
		if (sc.hasNextInt()) {
			vivienda.setIdPropietario(sc.nextInt());
			sc.nextLine();
		}
			
		/* Comprobar si existe el propietario */
		if (Propietario.consultar(conex, idPropietario).next()) {
			
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
	public static int solicitarID() {
		
		System.out.println("--------------- [ SOLICITUD DE CÓDIGO ] --------------\n");
		
		int id = -1;
		
		// Solicitar ID del inquilino a buscar //
		System.out.print("  - CÓDIGO VIVIENDA: ");		

		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE INQUILINO CON FORMATO TIPO TABLA //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// OPCIÓN MODIFICAR LOS DATOS DE UN INQUILINO DADO SU ID //
	public static Inquilino modificar(int id) throws SQLException {
		
		Inquilino inquilino = new Inquilino();
		inquilino.setId(id);
		
		System.out.println(""
			+ "---------- [ MODIFICAR DATOS DE INQUILINO ] ----------\n"
			+ "(Si no se desea modificar un campo, pulsar [ENTER])\n");

		/* Solicitar el resto de datos si se desean aportar */
		System.out.print("  - DNI: "); 
		inquilino.setDni(sc.nextLine());

		System.out.print("  - NOMBRE: "); 
		inquilino.setNombre(sc.nextLine());

		System.out.print("  - APELLIDOS: "); 
		inquilino.setApellidos(sc.nextLine());

		System.out.print("  - CORREO: "); 
		inquilino.setCorreo(sc.nextLine());

		System.out.print("  - TELÉFONO: "); 
		inquilino.setTelefono(sc.nextLine());
		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine();
		if (mascotasString.trim().toUpperCase().equals("S")) inquilino.setMascotas(1);
		if (mascotasString.trim().toUpperCase().equals("N")) inquilino.setMascotas(0);
		
		return inquilino;
	}
}