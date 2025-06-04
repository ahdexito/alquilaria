package vista;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.*;

/**
 * Clase que gestiona la interacción con el usuario para las operaciones relacionadas con propietarios.
 * Incluye métodos para solicitar datos de entrada, como el ID, y para imprimir los resultados de consultas
 * de propietarios en un formato legible.
 *
 * @author Ángel García Smakula
 */

public class InterfazPropietario {
	
	private static Scanner sc = new Scanner(System.in);
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario el ID de un propietario.
     * Gestiona la entrada de datos para asegurar que sea un valor numérico.
     *
     * @return El ID del propietario introducido por el usuario, o -1 si la entrada no es un número válido.
     */
	
	public static int solicitarID() {
				
		int id = -1;
		
		// Solicitar ID del sujeto a buscar //
		System.out.print("  - ID PROPIETARIO: ");		

		// Recoger error de entrada por valor no numérico //
		if (sc.hasNextInt()) id = sc.nextInt();
		
		sc.nextLine();
		
		return id;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Imprime los datos de un propietario obtenidos de un ResultSet en un formato de tabla.
     * Formatea los datos de ID, DNI, nombre, apellidos, correo y teléfono para una presentación clara.
     *
     * @param rs El ResultSet que contiene los datos de la consulta de un propietario.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
	
	public static void imprimir(ResultSet rs) throws SQLException {
		
		System.out.println(("-").repeat(150));
		System.out.printf("%-10s %-20s %-40s %-40s %-20s\n", 
			"|  ID", "|  DNI", "|  NOMBRE", "|  CORREO", "|  TELÉFONO");
		System.out.println(("-").repeat(150));

		String telefono = rs.getString("telefono");
		if (telefono == null) telefono = "(VACÍO)";

		System.out.printf("%-10s %-20s %-40s %-40s %-20s",
			"|  " + rs.getInt("id"),
			"|  " + rs.getString("DNI"),
			"|  " + rs.getString("apellidos") + ", " + rs.getString("nombre"),
			"|  " + rs.getString("correo"),
			"|  " + telefono);

		System.out.println("\n" + ("-").repeat(150));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario los datos de un propietario para su creación o modificación.
     * Si se proporciona un objeto Propietario existente y un ID, se utilizan sus atributos
     * como valores predeterminados para que el usuario pueda dejarlos vacíos si no desea modificarlos.
     * Realiza formateo de texto para campos como nombre y apellidos (capitalización).
     *
     * @param propietario Objeto Propietario con datos preexistentes (para modificación) o un objeto vacío (para creación).
     * @param id El ID del propietario a establecer.
     * @return Un nuevo objeto Propietario con los datos introducidos por el usuario o los valores preexistentes.
     */
	
	public static Propietario solicitarDatos(Propietario propietario, int id) {
		
		propietario.setId(id);
		
		/* Solicitar los datos y guardarlos en sus atributos */
		System.out.print("  - DNI: "); 
		String dni = sc.nextLine().trim().toUpperCase();
		dni = dni.isEmpty() ? propietario.getDni() : dni;
		
		
		System.out.print("  - NOMBRE: "); 
		String nombre = sc.nextLine().trim();
		if (!nombre.isEmpty()) {
			/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(nombre.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			nombre = (String.join(" ", lista));
		}	
		else nombre = propietario.getNombre();
		
		
		System.out.print("  - APELLIDOS: "); 
		String apellidos = sc.nextLine();
		if (!apellidos.trim().isEmpty()) {
			/* Formatear entrada para tener letras capitales mayúscula y el resto minúscula */
			ArrayList<String> lista = new ArrayList<>(Arrays.asList(apellidos.toLowerCase().trim().split("\\s+")));
			lista.replaceAll(p -> p.substring(0,1).toUpperCase() + p.substring(1));
			apellidos = (String.join(" ", lista));
		}	
		else apellidos = propietario.getApellidos();
		
		
		System.out.print("  - CORREO: "); 
		String correo = sc.nextLine().trim().toLowerCase();
		correo = correo.isEmpty() ? propietario.getCorreo() : correo;

		
		System.out.print("  - TELÉFONO: "); 
		String telefono = sc.nextLine().trim();
		telefono = telefono.isEmpty() ? propietario.getTelefono() : telefono;
		
		return new Propietario(id, dni, nombre, apellidos, correo, telefono);
	}
}