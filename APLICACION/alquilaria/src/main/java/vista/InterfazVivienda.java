package vista;

import modelo.PropietarioCRUD;
import java.util.Scanner;
import java.sql.*;
import modelo.*;

/**
 * Clase que gestiona la interacción con el usuario para las operaciones relacionadas con viviendas.
 * Incluye métodos para solicitar datos de entrada, como el código de la vivienda, y para imprimir
 * los resultados de consultas de viviendas en un formato legible.
 *
 * @author Ángel García Smakula
 */

public class InterfazVivienda {
	
	private static Scanner sc = new Scanner(System.in);
    
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario el código de una vivienda.
     * Convierte la entrada a mayúsculas para estandarización.
     *
     * @return El código de la vivienda introducido por el usuario.
     */
	
	public static String solicitarCod() {
				
		// Solicitar ID del inquilino a buscar //
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): ");		

		String cod = sc.nextLine().toUpperCase();
		
		return cod;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Imprime los datos de una vivienda obtenidos de un ResultSet en un formato de tabla.
     * Formatea el precio con el símbolo del euro y la coma como separador decimal,
     * la superficie con unidades de m2 y la coma como separador decimal,
     * y el estado de las mascotas y el tipo de vivienda en mayúsculas para una presentación clara.
     * También muestra la descripción de la vivienda en una línea separada.
     *
     * @param rs El ResultSet que contiene los datos de la consulta de una vivienda.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
	
	public static void imprimir(ResultSet rs) throws SQLException {
		
		System.out.println(("-").repeat(170));
		System.out.printf("%-15s %-20s %-50s %-15s %-20s %-15s %-15s\n", 
			"|  CÓDIGO", "|  ID PROPIETARIO", "|  DIRECCIÓN", "|  PRECIO", "|  SUPERFICIE", "|  MASCOTAS", "|  TIPO");
		System.out.println(("-").repeat(170));

		String mascotas = "NO";	
		if (rs.getInt("mascotas") == 1) mascotas = "SÍ";

		String descripcion = rs.getString("descripcion");
		if (descripcion == null) descripcion = "(VACÍO)";

		System.out.printf("%-15s %-20s %-50s %-15s %-20s %-15s %-15s",
			"|  " + rs.getString("cod"),
			"|  " + rs.getInt("id_propietario"),
			"|  " + rs.getString("direccion"),
			"|  " + (rs.getFloat("precio") + " €").replace('.', ','),
			"|  " + (rs.getFloat("superficie") + " m2").replace('.', ','),
			"|  " + mascotas,
			"|  " + rs.getString("nombre").toUpperCase());

		System.out.println("\n" + ("-").repeat(170));

		System.out.println("|  DESCRIPCIÓN -> " + descripcion);

		System.out.println(("-").repeat(170));
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario los datos para la creación de una nueva vivienda.
     * Incluye la solicitud del código de la vivienda, ID del propietario, dirección, precio, superficie,
     * descripción, si permite mascotas y el tipo de vivienda.
     * Realiza validación de la existencia del propietario, formateo de texto y validación de entrada
     * para valores numéricos, asignando valores por defecto en caso de errores o entradas vacías.
     *
     * @param conex Conexión activa a la base de datos, necesaria para verificar la existencia del propietario.
     * @param vivienda Objeto Vivienda que se utiliza como base, aunque para una nueva vivienda se espera que sea un objeto vacío.
     * @return Un nuevo objeto Vivienda con los datos introducidos por el usuario o los valores por defecto.
     * @throws SQLException Si ocurre un error al consultar la base de datos (por ejemplo, al verificar el propietario).
     */
	
	public static Vivienda solicitarDatos(Connection conex, Vivienda vivienda) throws SQLException {
		
		System.out.print("  - CÓDIGO VIVIENDA -> (V000): ");
		String codVivienda = sc.nextLine().trim().toUpperCase();

		
		System.out.print("  - ID PROPIETARIO: ");
		int idPropietario = -1;
		if (sc.hasNextInt()) {
			idPropietario = sc.nextInt();
			sc.nextLine();
		} 
		else {
			sc.nextLine();
			System.out.println("** ENTRADA INCORRECTA. CANCELANDO OPERACIÓN **");
			return vivienda;
		}

		
		// VERIFICAR SI EXISTE PROPIETARIO //
		if (!PropietarioCRUD.consultar(conex, idPropietario).next()) {
			System.out.println("** EL ID INTRODUCIDO NO EXISTE **");
			return vivienda;
		}

		
		System.out.print("  - DIRECCIÓN: ");
		String direccion = sc.nextLine().trim();
		direccion = direccion.isEmpty() ? null : direccion;

		
		System.out.print("  - PRECIO -> (0,0): ");
		String precioString = sc.nextLine().trim().replace(',', '.');
		float precio;
		try {
			precio = Float.parseFloat(precioString);
		} catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0) **\n");
			precio = 0;
		}

		
		System.out.print("  - SUPERFICIE -> (0,0): ");
		String superficieString = sc.nextLine().trim().replace(',', '.');
		float superficie;
		try {
			superficie = Float.parseFloat(superficieString);
		} catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (0) **\n");
			superficie = 0;
		}

		
		System.out.print("  - DESCRIPCIÓN: ");
		String descripcion = sc.nextLine().trim();
		descripcion = descripcion.isEmpty() ? null : descripcion;

		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine().trim();
		int mascotas;
		if (mascotasString.equalsIgnoreCase("S")) {
			mascotas = 1;
		} else if (mascotasString.equalsIgnoreCase("N")) {
			mascotas = 0;
		} else {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (N) **\n");
			mascotas = 0;
		}

		
		System.out.print(""
			+ "  - TIPO VIVIENDA\n"
			+ "      1. Apartamento\n"
			+ "      2. Ático\n"
			+ "      3. Casa\n"
			+ "    OPCIÓN: "); 
		int tipo = 1;
		if (sc.hasNextInt()) {
			tipo = sc.nextInt();
			sc.nextLine();
		} else {
			sc.nextLine();
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR POR DEFECTO -> (1) **");
		}

		return new Vivienda(codVivienda, idPropietario, direccion, precio, superficie, descripcion, mascotas, tipo);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Solicita al usuario los datos para la modificación de una vivienda existente.
     * Mantiene el código de la vivienda original ya que es la clave de identificación.
     * Permite al usuario actualizar el ID del propietario, dirección, precio, superficie, descripción,
     * si permite mascotas y el tipo de vivienda.
     * Incluye validación de formato para números y maneja las entradas vacías, conservando los valores previos
     * del objeto Vivienda si la entrada es incorrecta o no se proporciona.
     *
     * @param vivienda Objeto Vivienda que contiene los datos actuales de la vivienda a modificar,
     * los cuales se utilizan como valores por defecto en caso de entradas vacías o incorrectas.
     * @return Un nuevo objeto Vivienda con los datos actualizados, manteniendo el código original.
     */
	
	public static Vivienda solicitarDatosMod(Vivienda vivienda) {
		
		String codVivienda = vivienda.getCod();
		
		System.out.println("\nINTRODUCE LOS NUEVOS DATOS:");
		
		System.out.print("  - ID PROPIETARIO: ");
		String idPropietarioString = sc.nextLine().trim();
		int idPropietario;
		try {
			idPropietario = idPropietarioString.isEmpty() ? 
				vivienda.getIdPropietario() : Integer.parseInt(idPropietarioString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			idPropietario = vivienda.getIdPropietario();
		}
		
		
		System.out.print("  - DIRECCIÓN: ");
		String direccion = sc.nextLine().trim();
		direccion = direccion.isEmpty() ? vivienda.getDireccion(): direccion;

		
		System.out.print("  - PRECIO -> (0,0): ");
		String precioString = sc.nextLine().trim().replace(',', '.');
		float precio;
		try {
			precio = precioString.isEmpty() ?
				vivienda.getPrecio() : Float.parseFloat(precioString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			precio = vivienda.getPrecio();
		}

		
		System.out.print("  - SUPERFICIE -> (0,0): "); 
		String superficieString = sc.nextLine().trim().replace(',', '.');
		float superficie;
		try {
			superficie = superficieString.isEmpty() ?
				vivienda.getSuperficie() : Float.parseFloat(superficieString);
		}
		catch (NumberFormatException e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			superficie = vivienda.getSuperficie();
		}

		
		System.out.print("  - DESCRIPCIÓN: "); 
		String descripcion = sc.nextLine().trim();
		descripcion = descripcion.isEmpty() ? vivienda.getDescripcion() : descripcion;

		
		System.out.print("  - MASCOTA(S) -> (S | N): ");
		String mascotasString = sc.nextLine().trim();
		int mascotas;
		if (mascotasString.isEmpty()) {
			mascotas = vivienda.getMascotas();
		}
		else if (mascotasString.equalsIgnoreCase("S")) {
			mascotas = 1;
		}
		else if (mascotasString.equalsIgnoreCase("N")) {
			mascotas = 0;
		}
		else {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			mascotas = vivienda.getMascotas();
		}
		
		
		System.out.print(""
			+ "  - TIPO VIVIENDA\n"
			+ "      1. Apartamento\n"
			+ "      2. Ático\n"
			+ "      3. Casa\n"
			+ "    OPCIÓN: "); 
		String tipoString = sc.nextLine().trim();
		int tipo;
		try {
			tipo = tipoString.isEmpty() ?
				vivienda.getTipo() : Integer.parseInt(tipoString);
		}
		catch (Exception e) {
			System.out.println("** ENTRADA INCORRECTA. SE ESTABLECERÁ EL VALOR PREVIO **\n");
			tipo = vivienda.getTipo();
		}
				
		return new Vivienda(codVivienda, idPropietario, direccion, precio, superficie, descripcion, mascotas, tipo);
	}
}