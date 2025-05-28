package vista;

import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazInquilino {
	
	private static Scanner sc = new Scanner(System.in);
    
    // OPCIÓN AÑADIR NUEVO INQUILINO DADOS SUS DATOS //
	public static Inquilino crear(Inquilino inquilino) {
				
		System.out.println("-------------- [ AÑADIR INQUILINO ] ------------\n");
		
		// Solicitar los datos del nuevo inquilino y guardarlos en variables //
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
		String mascotaString = sc.nextLine();
		if (mascotaString.trim().toUpperCase().equals("S")) inquilino.setMascota(true);
		else inquilino.setMascota(false);
		
		return inquilino;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// OPCIÓN CONSULTAR UN INQUILINO DADO SU ID //
	public static int solicitarID() {
		
		System.out.println("--------------- [ SOLICITUD DE ID ] --------------\n");
		
		int id = -1;
		
		// Solicitar ID del inquilino a buscar //
		System.out.print("  - ID: ");		

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
			
			/*
			// SI LOS CAMPOS SON NULOS, SE IMPRIMEN COMO CADENAS VACÍAS Y NO COMO 'NULL' //
			String telefono = rs.getString("telefono");
			telefono = telefono != null ? telefono : "";
			*/
			
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
		String mascotaString = sc.nextLine();
		if (mascotaString.trim().toUpperCase().equals("S")) inquilino.setMascota(true);
		else inquilino.setMascota(false);
		
		return inquilino;
	}
}