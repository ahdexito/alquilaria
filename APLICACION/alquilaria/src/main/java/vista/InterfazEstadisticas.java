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

public class InterfazEstadisticas {
    
	private static final Scanner sc = new Scanner(System.in);
	
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