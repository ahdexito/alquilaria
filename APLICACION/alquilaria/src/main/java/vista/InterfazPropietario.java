package vista;

import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazPropietario {
	
	private static Scanner sc = new Scanner(System.in);
    
    // OPCIÓN AÑADIR NUEVO PROPIETARIO DADOS SUS DATOS //
	public static Propietario crear(Propietario propietario) {
				
		System.out.println("-------------- [ AÑADIR NUEVO PROPIETARIO ] ------------\n");
		
		// Solicitar los datosdel nuevo cliente y guardarlos en variables //
		System.out.print("  - DNI: "); 
		propietario.setDni(sc.nextLine());
			
		System.out.print("  - NOMBRE: "); 
		propietario.setNombre(sc.nextLine());

		System.out.print("  - APELLIDOS: "); 
		propietario.setApellidos(sc.nextLine());

		System.out.print("  - CORREO: "); 
		propietario.setCorreo(sc.nextLine());

		System.out.print("  - TELÉFONO: "); 
		propietario.setTelefono(sc.nextLine());
		
		return propietario;
	}
	
	// OPCIÓN CONSULTAR UN CLIENTE DADO SU ID //
	public static int solicitarID() {
		
		System.out.println("--------------- [ SOLICITUD DE ID ] --------------\n");
		
		int id = -1;
		
		// Solicitar ID del cliente a buscar //
		System.out.print("  - ID: ");		

		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE CLIENTE CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
		if (rs.next()) {
			System.out.println(("-").repeat(150));
			System.out.printf("%-10s %-20s %-40s %-40s %-20s\n", 
				"|  ID", "|  DNI", "|  NOMBRE", "|  CORREO", "|  TELÉFONO");
			System.out.println(("-").repeat(150));
			
			/*
			// SI LOS CAMPOS SON NULOS, SE IMPRIMEN COMO CADENAS VACÍAS Y NO COMO 'NULL' //
			String telefono = rs.getString("telefono");
			telefono = telefono != null ? telefono : "";
			*/
			
			System.out.printf("%-10s %-20s %-40s %-40s %-20s",
				"|  " + rs.getInt("id"),
				"|  " + rs.getString("DNI"),
				"|  " + rs.getString("apellidos") + ", " + rs.getString("nombre"),
				"|  " + rs.getString("correo"),
				"|  " + rs.getString("telefono"));
			
			System.out.println("\n" + ("-").repeat(150));
		}
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	// OPCIÓN MODIFICAR LOS DATOS (DINÁMICAMENTE) DE UN PROPIETARIO DADO SU ID //
	public static Propietario modificar(ResultSet rs) throws SQLException {
		
		Propietario propietario = new Propietario();
		
		System.out.println(""
			+ "---------- [ MODIFICAR DATOS DE CLIENTE ] ----------\n"
			+ "(Si no se desea modificar un campo, pulsar [ENTER])\n");

		/* Comprobar si el ID introducido existe */

		/* Solicitar el resto de datos si se desean aportar */
		System.out.print("  - DNI: "); 
		propietario.setDni(sc.nextLine());

		System.out.print("  - NOMBRE: "); 
		propietario.setNombre(sc.nextLine());

		System.out.print("  - APELLIDOS: "); 
		propietario.setApellidos(sc.nextLine());

		System.out.print("  - CORREO: "); 
		propietario.setCorreo(sc.nextLine());

		System.out.print("  - TELÉFONO: "); 
		propietario.setTelefono(sc.nextLine());
		
		return propietario;
	}
}