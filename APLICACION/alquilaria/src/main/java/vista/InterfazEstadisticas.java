package vista;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Clase que gestiona la presentación de estadísticas a través de la interfaz de usuario
 * y permite la exportación de estos datos a un archivo JSON.
 *
 * @author Ángel García Smakula
 */

public class InterfazEstadisticas {
    
	private static final Scanner sc = new Scanner(System.in);
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Procesa y muestra los resultados de la consulta de gasto total por inquilino.
     * Imprime el nombre, apellidos y el gasto total de cada inquilino.
     * También formatea estos datos en una cadena JSON y los recolecta en un ArrayList.
     * Al finalizar, pregunta al usuario si desea exportar los datos a un archivo JSON.
     *
     * @param rs El ResultSet que contiene los datos de la consulta de gasto de inquilinos.
     * @return Un ArrayList de cadenas, donde cada cadena es un objeto JSON que representa un inquilino y su gasto.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
	
    public static ArrayList<String> gastoInquilino(ResultSet rs) throws SQLException {
		
		ArrayList<String> filas = new ArrayList<>();
		
		while (rs.next()) {
			String nombre = rs.getString("nombre");
			String apellidos = rs.getString("apellidos");
			float gasto = rs.getFloat("gasto_total");
			
			System.out.println("NOMBRE: " + apellidos + ", " + nombre + "  |||  GASTOS: " +  gasto + "€");
			
			String json = String.format(Locale.US, "  { \"nombre\": \"%s\", \"apellidos\": \"%s\", \"gasto_total\": %.2f }",
				nombre, apellidos, gasto
			);
			filas.add(json);
		}
		
		preguntarExportar("gasto_inquilino", filas);
		
		return filas;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Procesa y muestra los resultados de la consulta de cantidad de viviendas alquiladas por propietario.
     * Imprime el nombre, apellidos y el número de viviendas alquiladas de cada propietario.
     * También formatea estos datos en una cadena JSON y los recolecta en un ArrayList.
     * Al finalizar, pregunta al usuario si desea exportar los datos a un archivo JSON.
     *
     * @param rs El ResultSet que contiene los datos de la consulta de viviendas alquiladas por propietario.
     * @return Un ArrayList de cadenas, donde cada cadena es un objeto JSON que representa un propietario y sus viviendas alquiladas.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
	
	public static ArrayList<String> cantidadAlquileresPropietario(ResultSet rs) throws SQLException {
		
		ArrayList<String> filas = new ArrayList<String>();
		
		while (rs.next()) {
			String nombre = rs.getString("nombre");
			String apellidos = rs.getString("apellidos");
			int cantidad = rs.getInt("viviendas_alquiladas");
			
			System.out.println("NOMBRE: " + apellidos + ", " + nombre + "  |||  VIVIENDAS ALQUILADAS: " + cantidad);
			
			String json = String.format(
				"  { \"nombre\": \"%s\", \"apellidos\": \"%s\", \"viviendas_alquiladas\": %d }",
				nombre, apellidos, cantidad
			);
			filas.add(json);
		}
		
		preguntarExportar("alquileres_propietario", filas);
		
		return filas;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Procesa y muestra los resultados de la consulta del estado de los contratos.
     * Imprime cada estado de contrato y la cantidad de contratos en ese estado.
     * También formatea estos datos en una cadena JSON y los recolecta en un ArrayList.
     * Al finalizar, pregunta al usuario si desea exportar los datos a un archivo JSON.
     *
     * @param rs El ResultSet que contiene los datos de la consulta del estado de los contratos.
     * @return Un ArrayList de cadenas, donde cada cadena es un objeto JSON que representa un estado de contrato y su cantidad.
     * @throws SQLException Si ocurre un error al acceder a los datos del ResultSet.
     */
	
	public static ArrayList<String> estadoContratos(ResultSet rs) throws SQLException {
		
		ArrayList<String> filas = new ArrayList<String>();
		
		while (rs.next()) {
			String estado = rs.getString("estado");
			int cantidad = rs.getInt("cantidad");
			
			System.out.println("ESTADO: " + estado + "  |||  CANTIDAD: " + cantidad);
			
			String json = String.format(
				"  { \"estado\": \"%s\", \"cantidad\": %d }",
				estado, cantidad
			);
			filas.add(json);
		}
		
		preguntarExportar("estado_contratos", filas);
		
		return filas;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Pregunta al usuario si desea exportar los datos proporcionados a un archivo JSON.
     * Si el usuario confirma (ingresando 'S' o 'N'), crea una carpeta "export" si no existe,
     * genera un nombre de archivo único con prefijo y fecha/hora, y escribe los datos en formato JSON.
     * Maneja las excepciones de entrada/salida durante la escritura del archivo.
     *
     * @param prefijo Un prefijo para el nombre del archivo JSON exportado.
     * @param filas Un ArrayList de cadenas, donde cada cadena es un objeto JSON que representa una fila de datos.
     */
	
	public static void preguntarExportar(String prefijo, ArrayList<String> filas) {
		
		System.out.print("\n** ¿EXPORTAR DATOS EN FORMATO JSON? **\n   - (S / N): ");
		String entrada = sc.nextLine().trim();
		if (!entrada.equalsIgnoreCase("S")) return;
		
		/* Crear nueva carpeta si no existe */
		String carpeta = "export";
		File directorio = new File(carpeta);
		if (!directorio.exists()) directorio.mkdirs();
		
		/* Obtener fecha y hora actual para nombrar el archivo */
		String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String nombre = prefijo + "_" + fechaHora + ".json";
		File archivo = new File(directorio, nombre);
		
		/* Escritura del archivo */
		try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
			pw.println("[");
			for (int i = 0; i < filas.size(); i++) {
				pw.print(filas.get(i));
				if (i < filas.size() - 1) pw.println(",");
				else pw.println();
			}
			pw.println("]");
			System.out.println("\n** DATOS EXPORTADOS CORRECTAMENTE **");
			System.out.println("  --> " + archivo.getPath());
		} 
		catch (IOException e) {
			System.out.println("** ERROR AL EXPORTAR EL ARCHIVO: " + e.getMessage() + " **");
		}
	}
}