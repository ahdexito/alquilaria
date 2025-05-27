package vista;

import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class OpcionPropietario {
	
	private static Scanner sc = new Scanner(System.in);
    
    // OPCIÓN AÑADIR NUEVO PROPIETARIO DADOS SUS DATOS //
	public static Propietario crearPropietario(Propietario propietario) {
				
		System.out.println("-------------- [ AÑADIR NUEVO PROPIETARIO ] ------------\n");
		sc.nextLine();
		
		// SOLICITAR LOS DATOS DEL NUEVO CLIENTE Y GUARDARLOS EN VARIABLES //
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
	public static int consultarPropietario() {
		
		System.out.println("--------------- [ CONSULTAR PROPIETARIO ] --------------\n");
		
		int id = -1;
		
		// SOLICIAR ID DEL CLIENTE A BUSCAR //
		System.out.print("  - ID: ");		

		// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE CLIENTE CON FORMATO TIPO TABLA //
	public static void imprimirPropietario(ResultSet rs) throws SQLException {
		
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
}