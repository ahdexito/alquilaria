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

		do {
			opcion = -1;
			
			try {
				// CONEXIÓN A BASE DE DATOS
				Database db = Database.getConex("pi_asignacion_proyectos");
				Connection conex = db.getConex();
				
				/* CREAR UN RESULTSET PARA LLAMAR A LAS FUNCIONES 
				QUE REALIZAN SELECT Y PODER CERRARLOS POSTERIORMENTE */
				ResultSet rs = null;
				
				// IMPRIMIR EL MENÚ PRINCIPAL //
				Imprimir.menuPrincipal();
				
				// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
				if (sc.hasNextInt()) opcion = sc.nextInt();
				sc.nextLine();

				int subopcion;

				switch (opcion) {
					case 1:
						do {
							subopcion = -1;
							
							// IMPRIMIR EL MENÚ MANTENIMIENTO DE CLIENTE //
							Imprimir.menuCliente();

							// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
							if (sc.hasNextInt()) subopcion = sc.nextInt();
							sc.nextLine();
							
							System.out.println("");
							
							int id;
							String dni, nombre, apellido1, apellido2, email, telefono;
							
							switch (subopcion) {
								case 1:
									// OPCIÓN AÑADIR NUEVO CLIENTE DADOS SUS DATOS //
									System.out.println(MORADO 
										+ "-------------- " + AMARILLO + "[ AÑADIR NUEVO CLIENTE ]" + MORADO + " ------------" + RESET + "\n");
									
									// SOLICITAR LOS DATOS DEL NUEVO CLIENTE Y GUARDARLOS EN VARIABLES //
									System.out.print(AMARILLO + "  - DNI: " + RESET); 
									dni = sc.nextLine();

									System.out.print(AMARILLO + "  - NOMBRE: " + RESET); 
									nombre = sc.nextLine();

									System.out.print(AMARILLO + "  - PRIMER APELLIDO: " + RESET); 
									apellido1 = sc.nextLine();

									System.out.print(AMARILLO + "  - SEGUNDO APELLIDO: " + RESET); 
									apellido2 = sc.nextLine();

									System.out.print(AMARILLO + "  - EMAIL: " + RESET); 
									email = sc.nextLine();

									System.out.print(AMARILLO + "  - TELÉFONO: " + RESET); 
									telefono = sc.nextLine();
									
									// LLAMADA AL MÉTODO DE CREACIÓN ENVIANDO LAS VARIABLES //
									Propietario.crear(conex, dni, nombre, apellido1, apellido2, email, telefono);
									
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n" + AMARILLO + "--> CONTINUAR [ENTER] <--" + RESET);
									sc.nextLine();
									break;

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								case 2: 
									// OPCIÓN CONSULTAR UN CLIENTE DADO SU ID //
									System.out.println(MORADO 
										+ "--------------- " + AMARILLO + "[ CONSULTAR CLIENTE ]" + MORADO + " --------------" + RESET + "\n");
									
									// SOLICIAR ID DEL CLIENTE A BUSCAR //
									System.out.print(AMARILLO + "  - ID: " + RESET);
									
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
									System.out.print("\n" + AMARILLO + "--> CONTINUAR [ENTER] <--" + RESET);
									sc.nextLine();
									break;

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								case 3:
									// OPCIÓN MODIFICAR LOS DATOS DE UN CLIENTE ADAPTATIVAMENTE //
									System.out.println(MORADO 
										+ "---------- " + AMARILLO + "[ MODIFICAR DATOS DE CLIENTE ]" + MORADO + " ----------\n" + MORADO
										+ "(Si no se desea modificar un campo, pulsar [ENTER])\n" + RESET);
									
									// SOLICITAR EL ID OBLIGATORIAMENTE //
									System.out.print(AMARILLO + "  - ID (obligatorio): " + RESET);
									
									id = -1;
									
									// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
									if (sc.hasNextInt()) id = sc.nextInt();
									sc.nextLine();
									
									// COMPROBAR SI EL ID INTRODUCIDO EXISTE //
									if (Propietario.consultar(conex, id).next()) {
										
										// SOLICITAR EL RESTO DE DATOS SI SE DESEAN APORTAR //
										System.out.print(AMARILLO + "  - DNI: " + RESET); 
										dni = sc.nextLine();

										System.out.print(AMARILLO + "  - NOMBRE: " + RESET); 
										nombre = sc.nextLine();

										System.out.print(AMARILLO + "  - PRIMER APELLIDO: " + RESET); 
										apellido1 = sc.nextLine();

										System.out.print(AMARILLO + "  - SEGUNDO APELLIDO: " + RESET); 
										apellido2 = sc.nextLine();

										System.out.print(AMARILLO + "  - EMAIL: " + RESET); 
										email = sc.nextLine();

										System.out.print(AMARILLO + "  - TELÉFONO: " + RESET); 
										telefono = sc.nextLine();
									
										// LLAMADA AL MÉTODO DE MODIFICAR CLIENTE ENVIANDO LAS VARIABLES //
										Propietario.modificar(conex, id, dni, nombre, apellido1, apellido2, email, telefono);
									}
									// SI NO EXISTE SE CANCELA LA OPERACIÓN //
									else System.out.println("\n" + CIAN + "  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **" + RESET);
						
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n" + AMARILLO + "--> CONTINUAR [ENTER] <--" + RESET);
									sc.nextLine();
									break;

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								case 4:
									// OPCIÓN ELIMINAR UN CLIENTE DADO SU ID //
									System.out.println(MORADO 
										+ "--------------- " + AMARILLO + "[ ELIMINAR CLIENTE ]" + MORADO + " ---------------" + RESET + "\n");
									
									// SOLICITAR EL ID DEL CLIENTE A BORRAR //
									System.out.print(AMARILLO + "  - ID: " + RESET);
									
									id = -1;
									
									// RECOGER ERROR DE ENTRADA POR VALOR NO NUMÉRICO //
									if (sc.hasNextInt()) id = sc.nextInt();
									sc.nextLine();
									
									// LLAMADA AL MÉTODO ELIMINAR CLIENTE ENVIANDO SU ID //
									Propietario.eliminar(conex, id);
									
									// DETENCIÓN DEL PROGRAMA PARA QUE EL USUARIO CONFIRME //
									System.out.print("\n" + AMARILLO + "--> CONTINUAR [ENTER] <--" + RESET);
									sc.nextLine();
									break;

								//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

								case 0:
									// SALIR DEL MENÚ MANTENIMIENTO DE CLIENTE //
									System.out.println(CIAN + "  ** REGRESANDO... **" + RESET);
									Thread.sleep(700);
									break;

								default:
									// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
									System.out.println(ROJO + "ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4" + RESET);
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
									System.out.println(CIAN + "  ** OPCIÓN EN DESARROLLO... **" + RESET);
									Thread.sleep(700);
									break;

								case 0:
									System.out.println(CIAN + "  ** REGRESANDO... **" + RESET);
									Thread.sleep(700);
									break;

								default:
									// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
									System.out.println(ROJO + "ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4" + RESET);
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
									System.out.println(CIAN + "  ** OPCIÓN EN DESARROLLO... **" + RESET);
									Thread.sleep(700);
									break;

								case 0:
									System.out.println(CIAN + "  ** REGRESANDO... **" + RESET);
									Thread.sleep(700);
									break;

								default:
									// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
									System.out.println(ROJO + "ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4" + RESET);
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
									System.out.println(CIAN + "  ** OPCIÓN EN DESARROLLO... **" + RESET);
									Thread.sleep(700);
									break;

								case 0:
									System.out.println(CIAN + "  ** REGRESANDO... **" + RESET);
									Thread.sleep(700);
									break;

								default:
									// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
									System.out.println(ROJO + "ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4" + RESET);
									Thread.sleep(700);
									break;
							}
						}
						while (subopcion != 0);

						break;

					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					case 0:
						// OPCIÓN SALIR DEL PROGRAMA Y CERRAR CONEXIÓN //
						System.out.println("\n" + CIAN + "  ** SALIENDO... ¡HASTA PRONTO! **" + RESET);
						Thread.sleep(700);
						
						db.cerrarConex();
						break;

					default:
						// RECIBIR ERROR DE ENTRADA POR OPCIÓN DIFERENTE DEL 0 AL 4 //
						System.out.println("\n" + ROJO + "ERROR: LA ENTRADA DEBE SER UN NÚMERO ENTERO DEL 0 AL 4" + RESET);
						Thread.sleep(700);
						break;
				}
				
				// CERRAR EL RESULTSET SI SE HA USADO //
				if (rs != null) {
					rs.getStatement().close();
					rs.close();
				}
			}
			
			// RECOGER ERRORES DE SQL //
			catch (SQLException e) {
				System.out.println("\n" + ROJO + "ERROR: " + e.getMessage() + RESET);
				Thread.sleep(700);
			}
		} while (opcion != 0);
    }
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// VARIABLES PARA DAR COLOR A LA SALIDA POR CONSOLA //
	public static final String RESET = "\u001B[0m", MORADO = "\u001B[35m", ROJO = "\u001B[31m",
			AZUL = "\u001B[34m", CIAN = "\u001B[36m", VERDE = "\u001B[32m", AMARILLO = "\u001B[33m";
}