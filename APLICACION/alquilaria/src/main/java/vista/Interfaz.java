package vista;

import java.sql.*;
import java.util.Scanner;
import modelo.*;

public class Interfaz {
	
	private static Scanner sc = new Scanner(System.in);
    
	// MÉTODO QUE IMPRIME EL MENÚ PRINCIPAL //
    public static int menuPrincipal() {
				
		System.out.print("\n"
			+ "---------------- [ MENÚ PRINCIPAL ] ----------------\n"
			+ "  1. PROPIETARIOS\n"
			+ "  2. INQUILINOS\n"
			+ "  3. VIVIENDAS\n"
			+ "  4. CONTRATOS\n"
			+ "\n"
			+ "  0. SALIR\n"
			+ "----------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
		
		// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
		if (sc.hasNextInt()) {
			return sc.nextInt();
		}
		sc.nextLine();
		
		return -1;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA PROPIETARIO //
	public static int menuPropietario() {
		
		System.out.print("\n"
			+ "---------- [ MANTENIMIENTO TABLA PROPIETARIO ] ---------\n"
			+ "  1. CREAR\n"
			+ "  2. CONSULTAR\n"
			+ "  3. MODIFICAR\n"
			+ "  4. ELIMINAR\n"
			+ "\n"
			+ "  0. VOLVER\n"
			+ "----------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
		
		// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
		if (sc.hasNextInt()) {
			return sc.nextInt();
		}
		sc.nextLine();
		
		return -1;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
			System.out.println(("-").repeat(130));
			System.out.printf("%-20s %-25s %-50s %-45s %-25s\n", 
				"|  ID", "|  DNI", "|  NOMBRE", "|  CORREO", "|  TELÉFONO");
			System.out.println(("-").repeat(130));
			
			// SI LOS CAMPOS SON NULOS, SE IMPRIMEN COMO CADENAS VACÍAS Y NO COMO 'NULL' //
			String apellido2 = rs.getString("apellido2");
			apellido2 = apellido2 != null ? " " + apellido2 : "";
			String telefono = rs.getString("telefono");
			telefono = telefono != null ? telefono : "";
			
			System.out.printf("%-20s %-25s %-50s %-45s %-25s",
				"|  " + rs.getInt("id"),
				"|  " + rs.getString("DNI"),
				"|  " + rs.getString("apellido1") + apellido2 + ", " + rs.getString("nombre"),
				"|  " + rs.getString("correo"),
				"|  " + telefono);
			
			System.out.println("\n" + ("-").repeat(130));
		}
		
		else System.out.println("  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA PROYECTO //
	public static void menuProyecto() {
		
		System.out.print("\n"
			+ "--------- [ MANTENIMIENTO TABLA PROYECTO ] ---------\n"
			+ "  1. CREAR\n"
			+ "  2. CONSULTAR\n"
			+ "  3. MODIFICAR\n"
			+ "  4. ELIMINAR\n"
			+ "\n"
			+ "  0. VOLVER\n"
			+ "----------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA DESARROLLADOR //
	public static void menuDesarrollador() {
		
		System.out.print("\n"
			+ "------- [ MANTENIMIENTO TABLA DESARROLLADOR ] ------\n"
			+ "  1. CREAR\n"
			+ "  2. CONSULTAR\n"
			+ "  3. MODIFICAR\n"
			+ "  4. ELIMINAR\n"
			+ "\n"
			+ "  0. VOLVER\n"
			+ "----------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA ASIGNACIÓN //
	public static void menuAsignacion() {
		
		System.out.print("\n"
			+ "-------- [ MANTENIMIENTO TABLA ASIGNACIÓN ] --------\n"
			+ "  1. CREAR\n"
			+ "  2. CONSULTAR\n"
			+ "  3. MODIFICAR\n"
			+ "  4. ELIMINAR\n"
			+ "\n"
			+ "  0. VOLVER\n"
			+ "----------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
	}
}