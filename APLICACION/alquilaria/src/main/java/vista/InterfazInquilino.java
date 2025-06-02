package vista;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.*;

public class InterfazInquilino {
	
	private static Scanner sc = new Scanner(System.in);
    
	// OPCIÓN SOLICITAR ID INQUILINO //
	public static int solicitarID() {
				
		int id = -1;
		
		// Solicitar ID del sujeto a buscar //
		System.out.print("  - ID INQUILINO: ");		

		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE INQUILINO CON FORMATO TIPO TABLA //
	public static void imprimir(ResultSet rs) throws SQLException {
		
		System.out.println("\n" + ("-").repeat(170));
		System.out.printf("%-10s %-20s %-40s %-40s %-30s %-10s\n", 
			"|  ID", "|  DNI", "|  NOMBRE", "|  CORREO", "|  TELÉFONO", "|  MASCOTAS");
		System.out.println(("-").repeat(170));

		String mascotas = "NO";	
		if (rs.getInt("mascotas") == 1) mascotas = "SÍ";

		String telefono = rs.getString("telefono");
		if (telefono == null) telefono = "(VACÍO)";

		System.out.printf("%-10s %-20s %-40s %-40s %-30s %-10s",
			"|  " + rs.getInt("id"),
			"|  " + rs.getString("DNI"),
			"|  " + rs.getString("apellidos") + ", " + rs.getString("nombre"),
			"|  " + rs.getString("correo"),
			"|  " + telefono,
			"|  " + mascotas);

		System.out.println("\n" + ("-").repeat(170));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    // SOLICITAR DATOS DE INQUILINO PARA AÑADIR O MODIFICAR //
	public static Inquilino solicitarDatos(Inquilino inquilino, int id) {
		
		inquilino.setId(id);

		/* Solicitar los datos y guardarlos en sus atributos */
		System.out.print("  - DNI: "); 
		String dni = sc.nextLine().trim().toUpperCase();
		dni = dni.isEmpty() ? inquilino.getDni() : dni;
		
		
		System.out.print("  - NOMBRE: "); 
		String nombre = sc.nextLine().trim();
		if (!nombre.isEmpty()) {
			/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(nombre.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			nombre = (String.join(" ", lista));
		}	
		else nombre = inquilino.getNombre();

		
		System.out.print("  - APELLIDOS: "); 
		String apellidos = sc.nextLine();
		if (!apellidos.trim().isEmpty()) {
			/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(apellidos.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			apellidos = (String.join(" ", lista));
		}	
		else apellidos = inquilino.getApellidos();

		
		System.out.print("  - CORREO: "); 
		String correo = sc.nextLine().trim().toLowerCase();
		correo = correo.isEmpty() ? inquilino.getCorreo() : correo;

		
		System.out.print("  - TELÉFONO: "); 
		String telefono = sc.nextLine().trim();
		telefono = telefono.isEmpty() ? inquilino.getTelefono() : telefono;
		
		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine().trim();
		int mascotas;
		if (mascotasString.equalsIgnoreCase("S")) mascotas = 1;
		else if (mascotasString.equalsIgnoreCase("N")) mascotas = 0;
		else mascotas = inquilino.getMascotas();
		
		return new Inquilino(id, dni, nombre, apellidos, correo, telefono, mascotas);
	}	
}