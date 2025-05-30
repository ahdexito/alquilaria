package vista;

import java.util.Scanner;
import java.sql.*;
import modelo.*;

public class InterfazInquilino {
	
	private static Scanner sc = new Scanner(System.in);
    
    // SOLICITAR DATOS DE INQUILINO PARA AÑADIR O MODIFICAR //
	public static Inquilino solicitarDatos(int id) {
		
		Inquilino inquilino = new Inquilino();
		inquilino.setId(id);

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
}