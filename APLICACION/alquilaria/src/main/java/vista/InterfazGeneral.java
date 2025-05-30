package vista;

import java.util.Scanner;

public class InterfazGeneral {
	
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
		
		int entrada = -1;
		
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
		if (sc.hasNextInt()) entrada = sc.nextInt();
		sc.nextLine();
		
		switch (entrada) {
			case 1:
				System.out.print("\n------------- [ CREAR " + tabla + " ] -------------\n");
				break;
			case 2:
				System.out.print("\n------------- [ CONSULTAR " + tabla + " ] -------------\n");
				break;
			case 3:
				System.out.print("\n------------- [ MODIFICAR " + tabla + " ] -------------\n"
				+ "     (Si no se desea modificar un campo, pulsar [ENTER])\n");
				break;
			case 4:
				System.out.print("\n------------- [ ELIMINAR " + tabla + " ] -------------\n");
				break;
		}
		return entrada;
	}
	
	// OPCIÓN SOLICITAR ID DE PROPIETARIO O INQUILINO PARA CONSULTAR O ELIMINAR //
	public static int solicitarID() {
				
		int id = -1;
		
		// Solicitar ID del sujeto a buscar //
		System.out.print("  - ID PROPIETARIO: ");		

		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
}