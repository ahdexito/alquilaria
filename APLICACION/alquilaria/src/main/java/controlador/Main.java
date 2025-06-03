package controlador;

import modelo.Database;
import modelo.*;
import vista.*;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Ángel García Smakula
 */
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
			opcion = InterfazMenu.menuPrincipal();
			
			// MENÚ PRINCIPAL //
			switch (opcion) {

				// OPCIÓN PROPIETARIO //
				case 1:
					do {
						/* Imprimir menú mantenimiento de propietario y solicitar opción menú */
						subopcion = InterfazMenu.submenu("PROPIETARIOS");
						System.out.println("");

						/* Instancia de objeto propietario */
						Propietario propietario = new Propietario();

						// PROPIETARIO - MENÚ PRINCIPAL //
						switch (subopcion) {
							case 1 -> Menu.crearPropietario(conex, propietario);
							case 2 -> Menu.consultarPropietario(conex, propietario, rs);
							case 3 -> Menu.modificarPropietario(conex, propietario, rs);
							case 4 -> Menu.eliminarPropietario(conex, propietario, rs);
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
						subopcion = InterfazMenu.submenu("INQUILINOS");
						System.out.println("");

						/* Instancia de objeto inquilino */
						Inquilino inquilino = new Inquilino();

						// INQUILINO - MENÚ PRINCIPAL //
						switch (subopcion) {
							case 1 -> Menu.crearInquilino(conex, inquilino);
							case 2 -> Menu.consultarInquilino(conex, inquilino,rs);
							case 3 -> Menu.modificarInquilino(conex, inquilino, rs);
							case 4 -> Menu.eliminarInquilino(conex, inquilino, rs);							
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
						subopcion = InterfazMenu.submenu("VIVIENDAS");
						System.out.println("");

						/* Instancia de objeto vivienda */
						Vivienda vivienda = new Vivienda();

						// VIVIENDA - MENÚ PRINCIPAL //
						switch (subopcion) {
							case 1 -> Menu.crearVivienda(conex, vivienda);
							case 2 -> Menu.consultarVivienda(conex, vivienda, rs);
							case 3 -> Menu.modificarVivienda(conex, vivienda, rs);
							case 4 -> Menu.eliminarVivienda(conex, vivienda, rs);
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
						subopcion = InterfazMenu.submenu("CONTRATOS");
						System.out.println("");
						
						Contrato contrato = new Contrato();

						switch (subopcion) {
							case 1 -> Menu.crearContrato(conex, contrato);
							case 2 -> Menu.consultarContrato(conex, contrato, rs);
							case 3 -> Menu.modificarContrato(conex, contrato, rs);
							case 4 -> Menu.eliminarContrato(conex, contrato, rs);
							case 5 -> {
								System.out.println("** OPCIÓN EN DESARROLLO... **");
								Thread.sleep(700);
								break;
							}
						}
					}
					while (subopcion != 0);

					break;

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// OPCIÓN ESTADÍSTICAS //
				case 5:
							
					subopcion = InterfazMenu.estadisticas();
					System.out.println("");

					switch (subopcion) {
						case 1 -> Menu.gastoInquilino(conex, rs);
						case 2 -> Menu.cantidadAlquileresPropietario(conex, rs);
						case 3 -> Menu.estadoContratos(conex, rs);
					}
					if (subopcion > 0 && subopcion < 5) {
						/* Detención del programa */
						System.out.print("\n--> CONTINUAR [ENTER] <--");
						sc.nextLine();
					}
					
					break;
				
				case 0:
					// OPCIÓN SALIR DEL PROGRAMA Y CERRAR CONEXIÓN //
					System.out.println("\n** SALIENDO... ¡HASTA PRONTO! **");
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