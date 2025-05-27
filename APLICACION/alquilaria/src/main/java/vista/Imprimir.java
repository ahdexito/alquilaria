package vista;

import java.sql.*;

public class Imprimir {
    
	// MÉTODO QUE IMPRIME EL MENÚ PRINCIPAL //
    public static void menuPrincipal() {
		
		System.out.print("\n" + MORADO
			+ "---------------- " + AMARILLO + "[ MENÚ PRINCIPAL ]" + MORADO + " ----------------\n" + AMARILLO
			+ "  1. " + CIAN + "CLIENTES\n" + AMARILLO
			+ "  2. " + CIAN + "PROYECTOS\n" + AMARILLO
			+ "  3. " + CIAN + "DESARROLLADORES\n" + AMARILLO
			+ "  4. " + CIAN + "ASIGNACIONES DE PROYECTOS\n\n" + AMARILLO
			+ "  0. " + ROJO + "SALIR\n" + MORADO
			+ "----------------------------------------------------\n"
			+ "\n" + AMARILLO
			+ "INTRODUCE OPCIÓN: " + RESET);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA CLIENTE //
	public static void menuCliente() {
		
		System.out.print("\n" + MORADO
			+ "---------- " + AMARILLO + "[ MANTENIMIENTO TABLA CLIENTE ]" + MORADO + " ---------\n" + AMARILLO
			+ "  1. " + CIAN + "CREAR\n" + AMARILLO
			+ "  2. " + CIAN + "CONSULTAR\n" + AMARILLO
			+ "  3. " + CIAN + "MODIFICAR\n" + AMARILLO
			+ "  4. " + CIAN + "ELIMINAR\n\n" + AMARILLO
			+ "  0. " + ROJO + "VOLVER\n" + MORADO
			+ "----------------------------------------------------\n"
			+ "\n" + AMARILLO
			+ "INTRODUCE OPCIÓN: " + RESET);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA IMPRIMIR UNA CONSULTA DE CLIENTE CON FORMATO TIPO TABLA //
	public static void consultaCliente(ResultSet rs) throws SQLException {
		
		if (rs.next()) {
			System.out.println((MORADO + "-").repeat(130));
			System.out.printf("%-20s %-25s %-50s %-45s %-25s\n", 
				MORADO + "|  " + AMARILLO + "ID", 
				MORADO + "|  " + AMARILLO + "DNI", 
				MORADO + "|  " + AMARILLO + "NOMBRE", 
				MORADO + "|  " + AMARILLO + "CORREO", 
				MORADO + "|  " + AMARILLO + "TELÉFONO");
			System.out.println((MORADO + "-").repeat(130));
			
			// SI LOS CAMPOS SON NULOS, SE IMPRIMEN COMO CADENAS VACÍAS Y NO COMO 'NULL' //
			String apellido2 = rs.getString("apellido2");
			apellido2 = apellido2 != null ? " " + apellido2 : "";
			String telefono = rs.getString("telefono");
			telefono = telefono != null ? telefono : "";
			
			System.out.printf("%-20s %-25s %-50s %-45s %-25s",
				MORADO + "|  " + CIAN + rs.getInt("id"),
				MORADO + "|  " + CIAN + rs.getString("DNI"),
				MORADO + "|  " + CIAN + rs.getString("apellido1") + apellido2 + ", " + rs.getString("nombre"),
				MORADO + "|  " + CIAN + rs.getString("correo"),
				MORADO + "|  " + CIAN + telefono);
			
			System.out.println("\n" + (MORADO + "-" + RESET).repeat(130));
		}
		
		else System.out.println(CIAN + "  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **" + RESET);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA PROYECTO //
	public static void menuProyecto() {
		
		System.out.print("\n" + MORADO
			+ "--------- " + AMARILLO + "[ MANTENIMIENTO TABLA PROYECTO ]" + MORADO + " ---------\n" + AMARILLO
			+ "  1. " + CIAN + "CREAR\n" + AMARILLO
			+ "  2. " + CIAN + "CONSULTAR\n" + AMARILLO
			+ "  3. " + CIAN + "MODIFICAR\n" + AMARILLO
			+ "  4. " + CIAN + "ELIMINAR\n\n" + AMARILLO
			+ "  0. " + ROJO + "VOLVER\n" + MORADO
			+ "----------------------------------------------------\n"
			+ "\n" + AMARILLO
			+ "INTRODUCE OPCIÓN: " + RESET);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA DESARROLLADOR //
	public static void menuDesarrollador() {
		
		System.out.print("\n" + MORADO
			+ "------- " + AMARILLO + "[ MANTENIMIENTO TABLA DESARROLLADOR ]" + MORADO + " ------\n" + AMARILLO
			+ "  1. " + CIAN + "CREAR\n" + AMARILLO
			+ "  2. " + CIAN + "CONSULTAR\n" + AMARILLO
			+ "  3. " + CIAN + "MODIFICAR\n" + AMARILLO
			+ "  4. " + CIAN + "ELIMINAR\n\n" + AMARILLO
			+ "  0. " + ROJO + "VOLVER\n" + MORADO
			+ "----------------------------------------------------\n"
			+ "\n" + AMARILLO
			+ "INTRODUCE OPCIÓN: " + RESET);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO QUE IMPRIME EL MENÚ DE TABLA ASIGNACIÓN //
	public static void menuAsignacion() {
		
		System.out.print("\n" + MORADO
			+ "-------- " + AMARILLO + "[ MANTENIMIENTO TABLA ASIGNACIÓN ]" + MORADO + " --------\n" + AMARILLO
			+ "  1. " + CIAN + "CREAR\n" + AMARILLO
			+ "  2. " + CIAN + "CONSULTAR\n" + AMARILLO
			+ "  3. " + CIAN + "MODIFICAR\n" + AMARILLO
			+ "  4. " + CIAN + "ELIMINAR\n\n" + AMARILLO
			+ "  0. " + ROJO + "VOLVER\n" + MORADO
			+ "----------------------------------------------------\n"
			+ "\n" + AMARILLO
			+ "INTRODUCE OPCIÓN: " + RESET);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// VARIABLES PARA DAR COLOR A LA SALIDA POR CONSOLA //
	public static final String RESET = "\u001B[0m", MORADO = "\u001B[35m", ROJO = "\u001B[31m",
			AZUL = "\u001B[34m", CIAN = "\u001B[36m", VERDE = "\u001B[32m", AMARILLO = "\u001B[33m";
}