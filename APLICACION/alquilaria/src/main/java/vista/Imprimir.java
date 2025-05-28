package vista;

import java.util.Scanner;

public class Imprimir {
	
	private static Scanner sc = new Scanner(System.in);			
			
	// IMPRIMIR EL MENÚ PRINCIPAL //
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
		
		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) return sc.nextInt();
		
		sc.nextLine();
		
		return -1;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// IMPRIMIR EL SUBMENÚ DE CADA TABLA //
	public static int submenu(String tabla) {
		
		String opcion5 = "";
		if (tabla.equals("CONTRATO")) opcion5 = "  5. CAMBIAR ESTADO\n";
		
		System.out.print("\n"
			+ "---------- [ MANTENIMIENTO TABLA " + tabla + " ] ---------\n"
			+ "  1. CREAR\n"
			+ "  2. CONSULTAR\n"
			+ "  3. MODIFICAR\n"
			+ "  4. ELIMINAR\n" + opcion5
			+ "\n"
			+ "  0. VOLVER\n"
			+ "----------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
		
		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) return sc.nextInt();
		
		sc.nextLine();
		
		return -1;
	}
}