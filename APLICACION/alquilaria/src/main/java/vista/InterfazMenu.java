package vista;

import java.util.Scanner;

/**
 * Clase que gestiona la interfaz de menú en consola para las distintas secciones del programa.
 * Permite al usuario navegar por las opciones del sistema y seleccionar acciones específicas.
 * 
 * @author Ángel García Smakula
 */

public class InterfazMenu {
	
	private static Scanner sc = new Scanner(System.in);			
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Muestra el menú principal del sistema y solicita al usuario una opción válida.
	 *
	 * @return
	 * @throws InterruptedException
	 * 
	 */ 
	
    public static int menuPrincipal() throws InterruptedException {	
		
		do {			
			System.out.print("\n"
				+ "---------------- [ MENÚ PRINCIPAL ] ----------------\n"
				+ "  1. PROPIETARIOS\n"
				+ "  2. INQUILINOS\n"
				+ "  3. VIVIENDAS\n"
				+ "  4. CONTRATOS\n"
				+ "  5. ESTADÍSTICAS\n"
				+ "\n"
				+ "  0. SALIR\n"
				+ "----------------------------------------------------\n"
				+ "\n"
				+ "INTRODUCE OPCIÓN: ");

			if (sc.hasNextInt()) {
				int opcion = sc.nextInt();
				sc.nextLine();
				if (opcion >= 0 && opcion <= 5) {
					Thread.sleep(500);
					return opcion;
				}
			}
			else sc.nextLine();
			
			System.out.println("\n** LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4 **");
			Thread.sleep(500);
			
		} while (true);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Muestra el submenú de gestión correspondiente a una tabla específica (como propietarios, inquilinos, etc.)
	 * y solicita al usuario una opción válida
	 * 
	 * @param tabla
	 * @return
	 * @throws InterruptedException 
	 */
	
	public static int submenu(String tabla) throws InterruptedException {
		
		int opcion = -1;
		String opcion5 = "";

		if (tabla.equals("CONTRATOS")) opcion5 = "  5. CAMBIAR ESTADO\n";
			
		do {
			System.out.print("\n\n"
				+ "---------- [ GESTIÓN DE " + tabla + " ] ---------\n"
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
				
				if (
					(opcion >= 0 && opcion <= 4) ||
					(tabla.equals("CONTRATOS") && opcion == 5)
				) {
					switch (opcion) {
						case 1 -> System.out.print("\n\n------------- [ CREAR " + tabla + " ] -------------\n");
						case 2 -> System.out.print("\n\n------------- [ CONSULTAR " + tabla + " ] -------------\n");
						case 3 -> System.out.print("\n\n------------- [ MODIFICAR " + tabla + " ] -------------\n"
							+ "** Si no se desea modificar un campo, pulsar [ENTER] **\n");
						case 4 -> System.out.print("\n\n------------- [ ELIMINAR " + tabla + " ] -------------\n");
						case 5 -> System.out.print("\n\n------------- [ CAMBIAR ESTADO " + tabla + " ] -------------\n");
						case 0 -> {
							System.out.print("\n** REGRESANDO... **");
							System.out.println("");
							Thread.sleep(500);
						}
					}
					break;
				}
				
				else {
					System.out.println("\n** LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4 **");
					Thread.sleep(500);
				}
			}
			
			else {
				sc.nextLine();
				System.out.println("\n** LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4 **");
				Thread.sleep(500);
			}
			
		} while (true);
		
		return opcion;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Muestra el menú de estadísticas y solicita al usuario una opción válida.
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	
	public static int estadisticas() throws InterruptedException {
		
		int opcion;
		
		do {			
			System.out.print("\n\n"
			+ "------------- [ MENÚ ESTADÍSTICAS ] --------------\n"
			+ "  1. GASTO TOTAL DE CADA INQUILINO\n"
			+ "  2. CANTIDAD DE VIVIENDAS EN ALQUILER POR PROPIETARIO\n"
			+ "  3. CANTIDAD DE CONTRATOS SEGÚN ESTADO DE FIRMA\n"
			+ "\n"
			+ "  0. VOLVER\n"
			+ "---------------------------------------------------\n"
			+ "\n"
			+ "INTRODUCE OPCIÓN: ");
			
			opcion = -1;
			
			if (sc.hasNextInt()) {
				opcion = sc.nextInt();
				sc.nextLine();
				
				if (opcion == 0) {
					System.out.print("\n** REGRESANDO... **");
					System.out.println("");
					Thread.sleep(500);
					return opcion;
				}
				
				else if (opcion >= 1 && opcion <= 3) return opcion;
				
				else {
					System.out.println("\n** LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4 **");
					Thread.sleep(500);
				}
			}
			else {
				System.out.println("\n** LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4 **");
				Thread.sleep(500);
				sc.nextLine();
			}
		}
		while (true);
	} 
}