package vista;

import java.sql.*;
import java.util.Scanner;
import modelo.*;

public class Menu {
	
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
	public static int submenu(String tabla) {
		
		System.out.print("\n"
			+ "---------- [ MANTENIMIENTO TABLA " + tabla + " ] ---------\n"
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
}