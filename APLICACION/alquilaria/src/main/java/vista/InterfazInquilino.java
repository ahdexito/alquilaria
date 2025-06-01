package vista;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.*;

public class InterfazInquilino {
	
	private static Scanner sc = new Scanner(System.in);
    
    // SOLICITAR DATOS DE INQUILINO PARA AÑADIR O MODIFICAR //
	public static Inquilino solicitarDatos(int id) {
		
		Inquilino inquilino = new Inquilino();
		inquilino.setId(id);

		/* Solicitar los datos y guardarlos en sus atributos */
		System.out.print("  - DNI: "); 
		inquilino.setDni(sc.nextLine().toUpperCase());

		
		System.out.print("  - NOMBRE: "); 
		String nombre = sc.nextLine();
		
		/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
		if (!nombre.trim().isEmpty()) {
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(nombre.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			inquilino.setNombre(String.join(" ", lista));
		}	
		else inquilino.setNombre(nombre);

		
		System.out.print("  - APELLIDOS: "); 
		String apellidos = sc.nextLine();
		
		/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
		if (!apellidos.trim().isEmpty()) {
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(apellidos.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			inquilino.setApellidos(String.join(" ", lista));
		}	
		else inquilino.setApellidos(apellidos);

		
		System.out.print("  - CORREO: "); 
		inquilino.setCorreo(sc.nextLine().toLowerCase());

		
		System.out.print("  - TELÉFONO: "); 
		inquilino.setTelefono(sc.nextLine());
		
		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine();
		if (mascotasString.trim().toUpperCase().equals("S")) inquilino.setMascotas(1);
		if (mascotasString.trim().toUpperCase().equals("N")) inquilino.setMascotas(0);
		
		return inquilino;
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
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}	
}