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
								/* Solicitar ID a modificar */
								id = InterfazPropietario.solicitarID();
								
								/* Realizar consulta con el ID */
								rs = Propietario.consultar(conex, id);
								
								/* Comprobar si existe algún campo con ese ID */
								if (rs.next()) {
									/* Guardar los datos de esa consulta en un objeto */
									Propietario propietarioSinMod = new Propietario
										(id, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"));
									
									/* Solicitar los nuevos datos y guardarlos en otro objeto */
									propietario = InterfazPropietario.modificar(id);
									
									/* Modificar el objeto recibido con los datos solicitados */
									propietario.modificar(conex, propietarioSinMod);
								}
								
								/* Si no existe se cancela la operación */
								else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
								
								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								
							// PROPIETARIO - ELIMINAR //
							case 4:								
								/* Solicitar ID a eliminar */
								id = InterfazPropietario.solicitarID();

								// Llamada al método eliminar //
								Propietario.eliminar(conex, id);
								
								break;

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
						subopcion = Imprimir.submenu("INQUILINO");
						System.out.println("");

						/* Instancia de objeto inquilino */
						Inquilino inquilino = new Inquilino();
						int id;

						// INQUILINO - MENÚ PRINCIPAL //
						switch (subopcion) {

							// INQUILINO - CREAR //
							case 1:
								/* Solicitar datos para crear inquilino */
								InterfazInquilino.crear(inquilino);

								/* Enviar inquilino a la base de datos */
								inquilino.crear(conex);

								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							// INQUILINO - CONSULTAR //
							case 2:									
								/* Solicitar ID a buscar */
								id = InterfazInquilino.solicitarID();

								System.out.println("");

								/* Llamada al método consultar-inquilino para recibir un ResultSet */
								rs = Inquilino.consultar(conex, id);

								/* Enviar el ResultSet al método para imprimir */
								InterfazInquilino.imprimir(rs);

								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							// INQUILINO - MODIFICAR //
							case 3:
								/* Solicitar ID a modificar */
								id = InterfazInquilino.solicitarID();
								
								/* Realizar consulta con el ID */
								rs = Inquilino.consultar(conex, id);
								
								/* Comprobar si existe algún campo con ese ID */
								if (rs.next()) {
									/* Guardar los datos de esa consulta en un objeto */
									Inquilino inquilinoSinMod = new Inquilino
										(id, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"), rs.getInt("mascota"));
									
									/* Solicitar los nuevos datos y guardarlos en otro objeto */
									inquilino = InterfazInquilino.modificar(id);
									
									/* Modificar el objeto recibido con los datos solicitados */
									inquilino.modificar(conex, inquilinoSinMod);
								}
								
								/* Si no existe se cancela la operación */
								else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
								
								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								
							// INQUILINO - ELIMINAR //
							case 4:								
								/* Solicitar ID a eliminar */
								id = InterfazInquilino.solicitarID();

								// Llamada al método eliminar //
								Inquilino.eliminar(conex, id);
								
								break;

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
						/* Imprimir menú mantenimiento de viivenda y solicitar opción menú */
						subopcion = Imprimir.submenu("VIVIENDA");
						System.out.println("");

						/* Instancia de objeto vivienda */
						Vivienda vivienda = new Vivienda();
						String cod;

						// VIVIENDA - MENÚ PRINCIPAL //
						switch (subopcion) {

							// VIVIENDA - CREAR //
							case 1:
								/* Solicitar datos para crear vivienda */
								InterfazVivienda.crear(vivienda);

								/* Enviar vivienda a la base de datos */
								vivienda.crear(conex);

								break;

							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//							// VIVIENDA - CONSULTAR //
//							case 2:									
//								/* Solicitar ID a buscar */
//								cod = InterfazVivienda.solicitarID();
//
//								System.out.println("");
//
//								/* Llamada al método consultar-vivienda para recibir un ResultSet */
//								rs = Vivienda.consultar(conex, cod);
//
//								/* Enviar el ResultSet al método para imprimir */
//								InterfazVivienda.imprimir(rs);
//
//								break;
//
//							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//							// VIVIENDA - MODIFICAR //
//							case 3:
//								/* Solicitar ID a modificar */
//								cod = InterfazVivienda.solicitarID();
//								
//								/* Realizar consulta con el ID */
//								rs = Vivienda.consultar(conex, cod);
//								
//								/* Comprobar si existe algún campo con ese ID */
//								if (rs.next()) {
//									/* Guardar los datos de esa consulta en un objeto */
//									Vivienda viviendaSinMod = new Vivienda
//										(cod, rs.getInt("id_propietario"), rs.getString("direccion"), rs.getFloat("precio"), rs.getFloat("superficie"), rs.getString("descripcion"), rs.getInt("mascotas"), rs.getInt("tipo"));
//									
//									/* Solicitar los nuevos datos y guardarlos en otro objeto */
//									vivienda = InterfazVivienda.modificar(cod);
//									
//									/* Modificar el objeto recibido con los datos solicitados */
//									vivienda.modificar(conex, viviendaSinMod);
//								}
//								
//								/* Si no existe se cancela la operación */
//								else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
//								
//								break;
//
//							//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//								
//							// VIVIENDA - ELIMINAR //
//							case 4:								
//								/* Solicitar ID a eliminar */
//								cod = InterfazVivienda.solicitarID();
//
//								// Llamada al método eliminar //
//								Vivienda.eliminar(conex, cod);
//								
//								break;

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
						
						if (subopcion > 0 && subopcion < 5) {
							/* Detención del programa */
							System.out.print("\n--> CONTINUAR [ENTER] <--");
							sc.nextLine();
						}
					}
					while (subopcion != 0);

					break;
					
					/*
					
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