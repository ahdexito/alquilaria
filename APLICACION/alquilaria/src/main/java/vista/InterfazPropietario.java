package vista;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.*;

public class InterfazPropietario {
	
	private static Scanner sc = new Scanner(System.in);
    
	
	// OPCIÓN SOLICITAR ID PROPIETARIO //
	public static int solicitarID() {
				
		int id = -1;
		
		// Solicitar ID del sujeto a buscar //
		System.out.print("  - ID PROPIETARIO: ");		

		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE PROPIETARIO CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    // SOLICITAR DATOS DE PROPIETARIO PARA AÑADIR O MODIFICAR //
	public static Propietario solicitarDatos(Propietario propietario, int id) {
		
		propietario.setId(id);
		
		/* Solicitar los datos y guardarlos en sus atributos */
		System.out.print("  - DNI: "); 
		String dni = sc.nextLine().trim().toUpperCase();
		dni = dni.isEmpty() ? propietario.getDni() : dni;
		
		
		System.out.print("  - NOMBRE: "); 
		String nombre = sc.nextLine().trim();
		if (!nombre.isEmpty()) {
			/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(nombre.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			nombre = (String.join(" ", lista));
		}	
		else nombre = propietario.getNombre();
		
		
		System.out.print("  - APELLIDOS: "); 
		String apellidos = sc.nextLine();
		if (!apellidos.trim().isEmpty()) {
			/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(apellidos.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			apellidos = (String.join(" ", lista));
		}	
		else apellidos = propietario.getApellidos();
		
		
		System.out.print("  - CORREO: "); 
		String correo = sc.nextLine().trim().toLowerCase();
		correo = correo.isEmpty() ? propietario.getCorreo() : correo;

		
		System.out.print("  - TELÉFONO: "); 
		String telefono = sc.nextLine().trim();
		telefono = telefono.isEmpty() ? propietario.getTelefono() : telefono;
		
		return new Propietario(id, dni, nombre, apellidos, correo, telefono);
	}
}