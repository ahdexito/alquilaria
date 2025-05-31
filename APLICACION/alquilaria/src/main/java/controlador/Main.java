package controlador;

import modelo.Database;
import modelo.*;
import vista.*;
import java.util.Scanner;
import java.sql.*;

public class Main {
    
    public static void main(String[] args) throws InterruptedException, SQLException {
        
		Scanner sc = new Scanner(System.in);
	
		int opcion, subopcion;
		
		// CONEXIÓN A BASE DE DATOS
		Database db = Database.getConex("alquilaria");
		Connection conex = db.getConex();
		
		do {
			/* Crear un ResultSet para llamar a las funciones 
			que realizan select y poder cerrarlos posteriormente */
			ResultSet rs = null;

			/* Imprimir menú principal y solicitar opción menú */
			opcion = InterfazGeneral.menuPrincipal();
			
			// MENÚ PRINCIPAL //
			switch (opcion) {

				// OPCIÓN PROPIETARIO //
				case 1:
					do {
						/* Imprimir menú mantenimiento de propietario y solicitar opción menú */
						subopcion = InterfazGeneral.submenu("PROPIETARIO");
						System.out.println("");

						/* Instancia de objeto propietario */
						Propietario propietario = new Propietario();

						// PROPIETARIO - MENÚ PRINCIPAL //
						switch (subopcion) {
							case 1 -> Menu.crearPropietario(conex, propietario);
							case 2 -> Menu.consultarPropietario(conex, propietario, rs);
							case 3 -> Menu.modificarPropietario(conex, propietario, rs);
							case 4 -> Menu.eliminarPropietario(conex, propietario);
							case 0 -> {
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
							}
						}
						
						if (subopcion > 0 && subopcion < 5) {
							/* Detención del programa */
							System.out.print("\n--> CONTINUAR [ENTER] <--");
							sc.nextLine();
						}
					}
					while (subopcion != 0);

					break;

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
				// OPCIÓN INQUILINO //
				case 2:
					do {
						/* Imprimir menú mantenimiento de inquilino y solicitar opción menú */
						subopcion = InterfazGeneral.submenu("INQUILINO");
						System.out.println("");

						/* Instancia de objeto inquilino */
						Inquilino inquilino = new Inquilino();

						// INQUILINO - MENÚ PRINCIPAL //
						switch (subopcion) {
							case 1 -> Menu.crearInquilino(conex, inquilino);
							case 2 -> Menu.consultarInquilino(conex, inquilino,rs);
							case 3 -> Menu.modificarInquilino(conex, inquilino, rs);
							case 4 -> Menu.eliminarInquilino(conex, inquilino);
							case 0 -> {
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
							}
						}
						
						if (subopcion > 0 && subopcion < 5) {
							/* Detención del programa */
							System.out.print("\n--> CONTINUAR [ENTER] <--");
							sc.nextLine();
						}
					}
					while (subopcion != 0);

					break;
					
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// OPCIÓN VIVIENDA //
				case 3:
					do {
						/* Imprimir menú mantenimiento de vivienda y solicitar opción menú */
						subopcion = InterfazGeneral.submenu("VIVIENDA");
						System.out.println("");

						/* Instancia de objeto vivienda */
						Vivienda vivienda = new Vivienda();

						// VIVIENDA - MENÚ PRINCIPAL //
						switch (subopcion) {
							case 1 -> Menu.crearVivienda(conex, vivienda);
							case 2 -> Menu.consultarVivienda(conex, vivienda, rs);
							case 3 -> Menu.modificarVivienda(conex, vivienda, rs);
							case 4 -> Menu.eliminarVivienda(conex, vivienda);
							case 0 -> {
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
							}
						}
						
						if (subopcion > 0 && subopcion < 5) {
							/* Detención del programa */
							System.out.print("\n--> CONTINUAR [ENTER] <--");
							sc.nextLine();
						}
					}
					while (subopcion != 0);

					break;
					
				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
				// OPCIÓN CONTRATO //
				case 4:
					do {
						subopcion = InterfazGeneral.submenu("CONTRATO");
						System.out.println("");
						
						
						// crear objeto


						switch (subopcion) {
							case 1: case 2: case 3: case 4:
								System.out.println("  ** OPCIÓN EN DESARROLLO... **");
								Thread.sleep(700);
								break;
							case 0:
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
								break;
						}
					}
					while (subopcion != 0);

					break;

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				case 0:
					// OPCIÓN SALIR DEL PROGRAMA Y CERRAR CONEXIÓN //
					System.out.println("\n  ** SALIENDO... ¡HASTA PRONTO! **");
					Thread.sleep(700);

					db.cerrarConex();
					sc.close();
					break;
			}

			// CERRAR EL RESULTSET SI SE HA USADO //
			if (rs != null) {
				rs.getStatement().close();
				rs.close();
			}
		} while (opcion != 0);
    }	
}