package vista;

import java.util.Scanner;

public class InterfazGeneral {
	
	private static Scanner sc = new Scanner(System.in);			
			
	// IMPRIMIR EL MENÚ PRINCIPAL //
    public static int menuPrincipal() throws InterruptedException {	
		
		while (true) {			
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

			if (sc.hasNextInt()) {
				int opcion = sc.nextInt();
				sc.nextLine();
				if (opcion >= 0 && opcion <= 4) return opcion;
			}
			else sc.nextLine();
			
			System.out.println("ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
			Thread.sleep(700);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// IMPRIMIR EL SUBMENÚ DE CADA TABLA //
	public static int submenu(String tabla) throws InterruptedException {
		
		boolean errorEntrada;
		int opcion = -1;
		String opcion5 = "";
		
		if (tabla.equals("CONTRATO")) opcion5 = "  5. CAMBIAR ESTADO\n";
		
		while (true) {			
			errorEntrada = false;
			
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
			
			
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				sc.nextLine();
				if (opcion < 0 || opcion > 4) errorEntrada = true;
			}
			else {
				errorEntrada = true;
				sc.nextLine();
			}		
			
			if (!tabla.equals("CONTRATO")) {
				switch (opcion) {
					case 1:
						System.out.print("\n------------- [ CREAR " + tabla + " ] -------------\n");
						break;
					case 2:
						System.out.print("\n------------- [ CONSULTAR " + tabla + " ] -------------\n");
						break;
					case 3:
						System.out.print("\n------------- [ MODIFICAR " + tabla + " ] -------------\n"
						+ "** Si no se desea modificar un campo, pulsar [ENTER] **\n");
						break;
					case 4:
						System.out.print("\n------------- [ ELIMINAR " + tabla + " ] -------------\n");
						break;
				}
			}
			else {
				switch (opcion) {
					case 1:
						System.out.print("\n------------- [ CREAR " + tabla + " ] -------------\n");
						break;
					case 2:
						System.out.print("\n------------- [ CONSULTAR " + tabla + " ] -------------\n");
						break;
					case 3:
						System.out.print("\n------------- [ MODIFICAR " + tabla + " ] -------------\n"
						+ "** Si no se desea modificar un campo, pulsar [ENTER] **\n");
						break;
					case 4:
						System.out.print("\n------------- [ ELIMINAR " + tabla + " ] -------------\n");
						break;
					case 5:
						System.out.print("\n------------- [ CAMBIAR ESTADO " + tabla + " ] -------------");
						break;
				}
			}
			
			if (errorEntrada) {
				System.out.println("ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
				Thread.sleep(700);
			}
			
			return opcion;
		}
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