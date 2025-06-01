package vista;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.*;

public class InterfazPropietario {
	
	private static Scanner sc = new Scanner(System.in);
    
    // SOLICITAR DATOS DE PROPIETARIO PARA AÑADIR O MODIFICAR //
	public static Propietario solicitarDatos(int id) {
		
		Propietario propietario  = new Propietario();
		propietario.setId(id);
		
		/* Solicitar los datos y guardarlos en sus atributos */
		System.out.print("  - DNI: "); 
		propietario.setDni(sc.nextLine().toUpperCase());
		
		
		System.out.print("  - NOMBRE: "); 
		String nombre = sc.nextLine();
		
		/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
		if (!nombre.trim().isEmpty()) {
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(nombre.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			propietario.setNombre(String.join(" ", lista));
		}	
		else propietario.setNombre(nombre);
		
		
		System.out.print("  - APELLIDOS: "); 
		String apellidos = sc.nextLine();
		
		/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
		if (!apellidos.trim().isEmpty()) {
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(apellidos.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			propietario.setApellidos(String.join(" ", lista));
		}	
		else propietario.setApellidos(apellidos);
		
		
		System.out.print("  - CORREO: "); 
		propietario.setCorreo(sc.nextLine().toLowerCase());

		
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
			
			String telefono = rs.getString("telefono");
			if (telefono == null) telefono = "(VACÍO)";
			
			System.out.printf("%-10s %-20s %-40s %-40s %-20s",
				"|  " + rs.getInt("id"),
				"|  " + rs.getString("DNI"),
				"|  " + rs.getString("apellidos") + ", " + rs.getString("nombre"),
				"|  " + rs.getString("correo"),
				"|  " + telefono);
			
			System.out.println("\n" + ("-").repeat(150));
		}
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
}