package vista;

import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazPropietario {
	
	private static Scanner sc = new Scanner(System.in);
    
    // SOLICITAR DATOS DE PROPIETARIO PARA AÑADIR O MODIFICAR //
	public static Propietario solicitarDatos(int id) {
		
		Propietario propietario  = new Propietario();
		propietario.setId(id);
		
		// Solicitar los datos del nuevo propietario y guardarlos en sus atributos //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE INQUILINO CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
		if (rs.next()) {
			System.out.println(("-").repeat(150));
			System.out.printf("%-10s %-20s %-40s %-40s %-20s\n", 
				"|  ID", "|  DNI", "|  NOMBRE", "|  CORREO", "|  TELÉFONO");
			System.out.println(("-").repeat(150));
			
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