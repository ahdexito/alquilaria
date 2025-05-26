package controlador;

import database.*;
import modelo.*;
import vista.*;
import java.util.Scanner;
import java.sql.*;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
		Scanner sc = new Scanner(System.in);
	
		int opcion;

		try {
			// CONEXIÓN A BASE DE DATOS
			Database db = Database.getConex("alquilaria");
			Connection conex = db.getConex();
			
			do {
				// IMPRIMIR MENÚ PRINCIPAL Y SOLICITAR OPCIÓN MENÚ //
				opcion = Interfaz.menuPrincipal();
				
				/* CREAR UN RESULTSET PARA LLAMAR A LAS FUNCIONES 
				QUE REALIZAN SELECT Y PODER CERRARLOS POSTERIORMENTE */
				ResultSet rs = null;

				int subopcion;

				switch (opcion) {
					case 1:
						do {
							// IMPRIMIR MENÚ MANTENIMIENTO DE PROPIETARIO Y SOLICITAR OPCIÓN MENÚ //
							subopcion = Interfaz.menuPropietario();
							
							System.out.println("");
							
							int id;
							String dni, nombre, apellido1, apellido2, email, telefono;
							
							switch (subopcion) {
								case 1:
									
									
									// LLAMADA AL MÉTODO DE CREACIÓN ENVIANDO LAS VARIABLES //
									Propietario.crear(conex, dni, nombre, apellido1, apellido2, email, telefono);
									
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n--> CONTINUAR [ENTER] <--");
									sc.nextLine();
									break;

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								case 2: 
									// OPCIÓN CONSULTAR UN CLIENTE DADO SU ID //
									System.out.println("--------------- [ CONSULTAR CLIENTE ] --------------\n");
									
									// SOLICIAR ID DEL CLIENTE A BUSCAR //
									System.out.print("  - ID: ");
									
									id = -1;
									
									// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
									if (sc.hasNextInt()) id = sc.nextInt();
									sc.nextLine();

									System.out.println("");
									
									// LLAMADA AL MÉTODO DE CONSULTA CLIENTE PARA RECIBIR UN RESULTSET //
									rs = Propietario.consultar(conex, id);
									
									// ENVIAR EL RESULTSER AL MÉTODO DE IMPRIMIR //
									Imprimir.consultaCliente(rs);
									
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n--> CONTINUAR [ENTER] <--");
									sc.nextLine();
									break;

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								case 3:
									// OPCIÓN MODIFICAR LOS DATOS DE UN CLIENTE ADAPTATIVAMENTE //
									System.out.println(""
										+ "---------- [ MODIFICAR DATOS DE CLIENTE ] ----------\n"
										+ "(Si no se desea modificar un campo, pulsar [ENTER])\n");
									
									// SOLICITAR EL ID OBLIGATORIAMENTE //
									System.out.print("  - ID (obligatorio): ");
									
									id = -1;
									
									// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
									if (sc.hasNextInt()) id = sc.nextInt();
									sc.nextLine();
									
									// COMPROBAR SI EL ID INTRODUCIDO EXISTE //
									if (Propietario.consultar(conex, id).next()) {
										
										// SOLICITAR EL RESTO DE DATOS SI SE DESEAN APORTAR //
										System.out.print("  - DNI: "); 
										dni = sc.nextLine();

										System.out.print("  - NOMBRE: "); 
										nombre = sc.nextLine();

										System.out.print("  - PRIMER APELLIDO: "); 
										apellido1 = sc.nextLine();

										System.out.print("  - SEGUNDO APELLIDO: "); 
										apellido2 = sc.nextLine();

										System.out.print("  - EMAIL: "); 
										email = sc.nextLine();

										System.out.print("  - TELÉFONO: "); 
										telefono = sc.nextLine();
									
										// LLAMADA AL MÉTODO DE MODIFICAR CLIENTE ENVIANDO LAS VARIABLES //
										Propietario.modificar(conex, id, dni, nombre, apellido1, apellido2, email, telefono);
									}
									// SI NO EXISTE SE CANCELA LA OPERACIÓN //
									else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
						
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n--> CONTINUAR [ENTER] <--");
									sc.nextLine();
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
									
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n--> CONTINUAR [ENTER] <--");
									sc.nextLine();
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
						}
						while (subopcion != 0);

						break;

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
		
		// RECOGER ERRORES DE SQL //
		catch (SQLException e) {
			System.out.println("\n" + e.getMessage());
			Thread.sleep(700);
		}
    }	
}