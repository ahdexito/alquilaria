package controlador;

import modelo.ViviendaCRUD;
import modelo.InquilinoCRUD;
import modelo.PropietarioCRUD;
import java.sql.*;
import modelo.*;
import vista.*;

/**
 * Clase Menu que contiene métodos estáticos para gestionar
 * operaciones CRUD de Propietarios, Inquilinos, Viviendas y Contratos.
 * 
 * @author Ángel García Smakula
 */

public class Menu {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Crea un nuevo propietario solicitando datos por consola y guardándolo en la base de datos.
	 * 
	 * @param conex
	 * @param propietario
	 * @throws SQLException 
	 */

    public static void crearPropietario(Connection conex, Propietario propietario) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		propietario = InterfazPropietario.solicitarDatos(propietario, 0);

		/* Ejecutar sentencia de creación */
		PropietarioCRUD.crear(conex, propietario);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Consulta y muestra un propietario por su ID.
	 * 
	 * @param conex
	 * @param propietario
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void consultarPropietario(Connection conex, Propietario propietario, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idPropietario = InterfazPropietario.solicitarID();

		System.out.println("");

		/* Ejecutar sentencia de consulta */
		rs = PropietarioCRUD.consultar(conex, idPropietario);

		/* Comprobar si ha habido resultado e imprimirlo */
		if (rs.next()) InterfazPropietario.imprimir(rs);
		
		/* Si no existe se cancela la operación */
		else System.out.println("** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica los datos de un propietario existente, solicitando nuevos datos por consola.
	 * 
	 * @param conex
	 * @param propietario
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void modificarPropietario(Connection conex, Propietario propietario, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idPropietario = InterfazPropietario.solicitarID();

		/* Ejecutar sentencia de consulta */
		rs = PropietarioCRUD.consultar(conex, idPropietario);

		/* Comprobar si ha habido resultado */
		if (rs.next()) {
			
			/* Guardar los datos de esa consulta en un objeto */
			propietario = new Propietario
				(idPropietario, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"));

			/* Sobreescribir objeto con los nuevos datos */
			propietario = InterfazPropietario.solicitarDatos(propietario, idPropietario);

			/* Ejecutar sentencia de modificación */			
			PropietarioCRUD.modificar(conex, propietario);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina un propietario según su ID.
	 * 
	 * @param conex
	 * @param propietario
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void eliminarPropietario(Connection conex, Propietario propietario, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idPropietario = InterfazPropietario.solicitarID();
		
		/* Ejecutar sentencia de consulta */
		rs = PropietarioCRUD.consultar(conex, idPropietario);
		
		/* Comprobar si ha habido resultado y eliminarlo */
		if (rs.next()) {
			
			// Ejecutar sentencia de eliminación //
			PropietarioCRUD.eliminar(conex, idPropietario);
		}
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	/**
     * Crea un nuevo inquilino solicitando datos por consola y guardándolo en la base de datos.
	 * 
	 * @param conex
	 * @param inquilino
	 * @throws SQLException 
	 */

	public static void crearInquilino(Connection conex, Inquilino inquilino) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		inquilino = InterfazInquilino.solicitarDatos(inquilino, 0);

		/* Ejecutar sentencia de creación */
		InquilinoCRUD.crear(conex, inquilino);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Consulta y muestra un inquilino por su ID.
	 * 
	 * @param conex
	 * @param inquilino
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void consultarInquilino(Connection conex, Inquilino inquilino, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idInquilino = InterfazInquilino.solicitarID();

		/* Ejecutar sentencia de consulta */
		rs = InquilinoCRUD.consultar(conex, idInquilino);
		
		/* Comprobar si ha habido resultado e imprimirlo */
		if (rs.next()) InterfazInquilino.imprimir(rs);
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica los datos de un inquilino existente, solicitando nuevos datos por consola.
	 * 
	 * @param conex
	 * @param inquilino
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void modificarInquilino(Connection conex, Inquilino inquilino, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idInquilino = InterfazInquilino.solicitarID();

		/* Ejecutar sentencia de consulta */
		rs = InquilinoCRUD.consultar(conex, idInquilino);

		/* Comprobar si ha habido resultado */
		if (rs.next()) {
			
			/* Guardar los datos de esa consulta en un objeto */
			inquilino = new Inquilino
				(idInquilino, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"), rs.getInt("mascotas"));

			/* Sobreescribir objeto con los nuevos datos */
			inquilino = InterfazInquilino.solicitarDatos(inquilino, idInquilino);

			/* Ejecutar sentencia de modificación */
			InquilinoCRUD.modificar(conex, inquilino);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");						
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina un inquilino según su ID.
	 * 
	 * @param conex
	 * @param inquilino
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void eliminarInquilino(Connection conex, Inquilino inquilino, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idInquilino = InterfazInquilino.solicitarID();
		
		/* Ejecutar sentencia de consulta */
		rs = InquilinoCRUD.consultar(conex, idInquilino);
		
		/* Comprobar si ha habido resultado y eliminarlo */
		if (rs.next()) {
			
			// Ejecutar sentencia de eliminación //
			InquilinoCRUD.eliminar(conex, idInquilino);
		}
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	/**
     * Crea una nueva vivienda solicitando datos por consola y guardándola en la base de datos.
	 * 
	 * @param conex
	 * @param vivienda
	 * @throws SQLException 
	 */
	
	public static void crearVivienda(Connection conex, Vivienda vivienda) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		vivienda = InterfazVivienda.solicitarDatos(conex, vivienda);

		/* Ejecutar sentencia de creación */
		ViviendaCRUD.crear(conex, vivienda);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Consulta y muestra una vivienda por su código.
	 * 
	 * @param conex
	 * @param vivienda
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void consultarVivienda(Connection conex, Vivienda vivienda, ResultSet rs) throws SQLException {
		
		/* Solicitar COD */
		String cod = InterfazVivienda.solicitarCod();

		System.out.println("");
		
		/* Ejecutar sentencia de consulta */
		rs = ViviendaCRUD.consultar(conex, cod);
		
		/* Comprobar si ha habido resultado e imprimirlo */
		if (rs.next()) InterfazVivienda.imprimir(rs);
		
		/* Si no existe se cancela la operación */
		else System.out.println("** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica los datos de una vivienda existente, solicitando nuevos datos por consola.
	 * 
	 * @param conex
	 * @param vivienda
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void modificarVivienda(Connection conex, Vivienda vivienda, ResultSet rs) throws SQLException {
		
		/* Solicitar COD */
		String cod = InterfazVivienda.solicitarCod();
		
		/* Ejecutar sentencia de consulta */
		rs = ViviendaCRUD.consultar(conex, cod);

		/* Comprobar si ha habido resultado */
		if (rs.next()) {
			
			/* Guardar los datos de esa consulta en un objeto */
			vivienda = new Vivienda
				(cod, rs.getInt("id_propietario"), rs.getString("direccion"), rs.getFloat("precio"), rs.getFloat("superficie"), rs.getString("descripcion"), rs.getInt("mascotas"), rs.getInt("tipo"));
			
			/* Sobreescribir objeto con los nuevos datos */
			vivienda = InterfazVivienda.solicitarDatosMod(vivienda);

			/* Ejecutar sentencia de modificación */
			ViviendaCRUD.modificar(conex, vivienda);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE CÓDIGO **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina una vivienda según su código.
	 * 
	 * @param conex
	 * @param vivienda
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void eliminarVivienda(Connection conex, Vivienda vivienda, ResultSet rs) throws SQLException {
		
		/* Solicitar COD */
		String codVivienda = InterfazVivienda.solicitarCod();
		
		/* Ejecutar sentencia de consulta */
		rs = ViviendaCRUD.consultar(conex, codVivienda);
		
		/* Comprobar si ha habido resultado y eliminarlo */
		if (rs.next()) {
			
			// Ejecutar sentencia de eliminación //
			ViviendaCRUD.eliminar(conex, codVivienda);
		}
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	/**
     * Crea un nuevo contrato solicitando datos por consola y guardándolo en la base de datos.
	 * 
	 * @param conex
	 * @param contrato
	 * @throws SQLException 
	 */

	public static void crearContrato(Connection conex, Contrato contrato) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		contrato = InterfazContrato.solicitarDatos(contrato);
		
		/* Ejecutar sentencia de creación */
		ContratoCRUD.crear(conex, contrato);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Consulta y muestra un contrato según su clave compuesta: idInquilino, codVivienda y fechaInicio.
	 * 
	 * @param conex
	 * @param contrato
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void consultarContrato(Connection conex, Contrato contrato, ResultSet rs) throws SQLException {
		
		/* Solicitar ID, COD, y fechaInicio (clave primaria conjunta) */
		int idInquilino = InterfazInquilino.solicitarID();
		String codVivienda = InterfazVivienda.solicitarCod();
		Date fechaInicio = InterfazContrato.solicitarFecha();
		
		System.out.println("");
		
		/* Ejecutar sentencia de consulta */
		rs = ContratoCRUD.consultar(conex, idInquilino, codVivienda, fechaInicio);
		
		/* Comprobar si ha habido resultado e imprimirlo */
		if (rs.next()) InterfazContrato.imprimir(rs);
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Modifica los datos de un contrato existente, solicitando nuevos datos por consola.
	 * 
	 * @param conex
	 * @param contrato
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void modificarContrato(Connection conex, Contrato contrato, ResultSet rs) throws SQLException {
		
		/* Solicitar ID, COD, y fechaInicio (clave primaria conjunta) */
		int idInquilino = InterfazInquilino.solicitarID();
		String codVivienda = InterfazVivienda.solicitarCod();
		Date fechaInicio = InterfazContrato.solicitarFecha();
		
		/* Ejecutar sentencia de consulta */
		rs = ContratoCRUD.consultar(conex, idInquilino, codVivienda, fechaInicio);

		/* Comprobar si ha habido resultado */
		if (rs.next()) {
			
			/* Guardar los datos de esa consulta en un objeto */
			contrato = new Contrato
				(idInquilino, codVivienda, fechaInicio, rs.getDate("fecha_fin"), rs.getFloat("precio"), rs.getString("estado"));
			
			/* Sobreescribir objeto con los nuevos datos */	
			contrato = InterfazContrato.solicitarDatosMod(contrato);

			/* Ejecutar sentencia de modificación */
			ContratoCRUD.modificar(conex, contrato);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE CÓDIGO **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Elimina un contrato según su clave compuesta: idInquilino, codVivienda y fechaInicio.
	 * 
	 * @param conex
	 * @param contrato
	 * @param rs
	 * @throws SQLException 
	 */
	
	public static void eliminarContrato(Connection conex, Contrato contrato, ResultSet rs) throws SQLException {
		
		/* Solicitar ID, COD, y fechaInicio (clave primaria conjunta) */
		int idInquilino = InterfazInquilino.solicitarID();
		String codVivienda = InterfazVivienda.solicitarCod();
		Date fechaInicio = InterfazContrato.solicitarFecha();
		
		/* Ejecutar sentencia de consulta */
		rs = ContratoCRUD.consultar(conex, idInquilino, codVivienda, fechaInicio);
		
		/* Comprobar si ha habido resultado */
		if (rs.next()) {
			
			/* Ejecutar sentencia de eliminación */
			ContratoCRUD.eliminar(conex, idInquilino, codVivienda, fechaInicio);
		}
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param conex
	 * @param contrato 
	 */
	
	public static void cambiarEstadoContrato(Connection conex, Contrato contrato) {
		
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	/**
	 * Calcula y muestra el gasto total en alquiler de cada inquilino.
	 * 
	 * @param conex
	 * @param rs
	 * @throws java.sql.SQLException
	 */
		
	public static void gastoInquilino(Connection conex, ResultSet rs) throws SQLException {
		
		rs = EstadisticasCRUD.gastoInquilino(conex);
		
		InterfazEstadisticas.gastoInquilino(rs);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Calcula y muestra cuántas viviendas en alquiler tiene cada propietario.
	 * 
	 * @param conex
	 * @param rs
	 * @throws SQLException 
	 */
		
	public static void cantidadAlquileresPropietario(Connection conex, ResultSet rs) throws SQLException {
		
		rs = EstadisticasCRUD.cantidadAlquileresPropietario(conex);
		
		InterfazEstadisticas.cantidadAlquileresPropietario(rs);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Cuenta y muestra cuántos contratos existen en cada estado (por ejemplo: activos, finalizados).
	 * 
	 * @param conex
	 * @param rs
	 * @throws SQLException 
	 */
		
	public static void estadoContratos(Connection conex, ResultSet rs) throws SQLException {
		
		rs = EstadisticasCRUD.estadoContratos(conex);
		
		InterfazEstadisticas.estadoContratos(rs);
	}
}