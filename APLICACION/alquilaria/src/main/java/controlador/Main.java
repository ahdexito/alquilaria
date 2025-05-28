package controlador;

import database.*;
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
			opcion = Imprimir.menuPrincipal();

			// MENÚ PRINCIPAL //
			switch (opcion) {

				// OPCIÓN PROPIETARIO //
				case 1:
					do {
						/* Imprimir menú mantenimiento de propietario y solicitar opción menú */
						subopcion = Imprimir.submenu("PROPIETARIO");
						System.out.println("");

						/* Instancia de objeto propietario */
						Propietario propietario = new Propietario();
						int id;

						// PROPIETARIO - MENÚ PRINCIPAL //
						switch (subopcion) {

							// PROPIETARIO - CREAR //
							case 1:
								/* Solicitar datos para crear propietario */
								InterfazPropietario.crear(propietario);

								/* Enviar propietario a la base de datos */
								propietario.crear(conex);

								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							// PROPIETARIO - CONSULTAR //
							case 2:									
								/* Solicitar ID a buscar */
								id = InterfazPropietario.solicitarID();

								System.out.println("");

								/* Llamada al método consultar-propietario para recibir un ResultSet */
								rs = Propietario.consultar(conex, id);

								/* Enviar el ResultSet al método para imprimir */
								InterfazPropietario.imprimir(rs);

								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							// PROPIETARIO - MODIFICAR //
							case 3:
								/* Solicitar ID a buscar */
								id = InterfazPropietario.solicitarID();

								System.out.println("");

								/* Llamada al método consultar-propietario para recibir un ResultSet */
								rs = Propietario.consultar(conex, id);

								InterfazPropietario.modificar(rs);

								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							case 4:
								// OPCIÓN ELIMINAR UN CLIENTE DADO SU ID //
								System.out.println("--------------- [ ELIMINAR CLIENTE ] ---------------\n");

								// SOLICITAR EL ID DEL CLIENTE A BORRAR //
								System.out.print("  - ID: ");

								id = -1;

								// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
								if (sc.hasNextInt()) id = sc.nextInt();
								sc.nextLine();

								// LLAMADA AL MÉTODO ELIMINAR CLIENTE ENVIANDO SU ID //
								Propietario.eliminar(conex, id);

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							case 0:
								// SALIR DEL MENÚ MANTENIMIENTO DE CLIENTE //
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
								break;

							default:
								// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
								System.out.println("ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
								Thread.sleep(700);
								break;
						}

						/* Detención del programa */
						System.out.print("\n--> CONTINUAR [ENTER] <--");
						sc.nextLine();
					}
					while (subopcion != 0);

					break;

					/*

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				case 2:
					// MENÚ MANTENIMIENTO DE PROYECTOS EN DESARROLLO... //
					do {
						subopcion = -1;

						Imprimir.menuProyecto();

						// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
						if (sc.hasNextInt()) subopcion = sc.nextInt();
						sc.nextLine();

						System.out.println("");

						switch (subopcion) {
							case 1: case 2: case 3: case 4:
								System.out.println("  ** OPCIÓN EN DESARROLLO... **");
								Thread.sleep(700);
								break;

							case 0:
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
								break;

							default:
								// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
								System.out.println("ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
								Thread.sleep(700);
								break;
						}
					}
					while (subopcion != 0);

					break;

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				case 3:
					// MENÚ MANTENIMIENTO DE DESARROLLADORES EN DESARROLLO... //
					do {
						subopcion = -1;

						Imprimir.menuDesarrollador();

						// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
						if (sc.hasNextInt()) subopcion = sc.nextInt();
						sc.nextLine();

						System.out.println("");

						switch (subopcion) {
							case 1: case 2: case 3: case 4:
								System.out.println("  ** OPCIÓN EN DESARROLLO... **");
								Thread.sleep(700);
								break;

							case 0:
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
								break;

							default:
								// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
								System.out.println("ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
								Thread.sleep(700);
								break;
						}
					}
					while (subopcion != 0);

					break;

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				case 4:
					// MENÚ MANTENIMIENTO DE ASIGNACIONES DE PROYECTOS EN DESARROLLO... //
					do {
						subopcion = -1;

						Imprimir.menuAsignacion();

						// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
						if (sc.hasNextInt()) subopcion = sc.nextInt();
						sc.nextLine();

						System.out.println("");

						switch (subopcion) {
							case 1: case 2: case 3: case 4:
								System.out.println("  ** OPCIÓN EN DESARROLLO... **");
								Thread.sleep(700);
								break;

							case 0:
								System.out.println("  ** REGRESANDO... **");
								Thread.sleep(700);
								break;

							default:
								// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
								System.out.println("ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
								Thread.sleep(700);
								break;
						}
					}
					while (subopcion != 0);

					break;

				//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				*/

				case 0:
					// OPCIÓN SALIR DEL PROGRAMA Y CERRAR CONEXIÓN //
					System.out.println("\n  ** SALIENDO... ¡HASTA PRONTO! **");
					Thread.sleep(700);

					db.cerrarConex();
					break;

				default:
					// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
					System.out.println("\nERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4");
					Thread.sleep(700);
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