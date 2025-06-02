package controlador;

import modelo.ViviendaCRUD;
import modelo.InquilinoCRUD;
import modelo.PropietarioCRUD;
import java.sql.*;
import modelo.*;
import vista.*;

public class Menu {
    
	// CREAR PROPIETARIO //
    public static void crearPropietario(Connection conex, Propietario propietario) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		propietario = InterfazPropietario.solicitarDatos(propietario, 0);

		/* Ejecutar sentencia de creación */
		PropietarioCRUD.crear(conex, propietario);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR PROPIETARIO //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR PROPIETARIO //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// ELIMINAR PROPIETARIO //
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
	
	// CREAR INQUILINO //
	public static void crearInquilino(Connection conex, Inquilino inquilino) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		inquilino = InterfazInquilino.solicitarDatos(inquilino, 0);

		/* Ejecutar sentencia de creación */
		InquilinoCRUD.crear(conex, inquilino);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR INQUILINO //
	public static void consultarInquilino(Connection conex, Inquilino inquilino, ResultSet rs) throws SQLException {
		
		/* Solicitar ID */
		int idInquilino = InterfazInquilino.solicitarID();

		System.out.println("");

		/* Ejecutar sentencia de consulta */
		rs = InquilinoCRUD.consultar(conex, idInquilino);
		
		/* Comprobar si ha habido resultado e imprimirlo */
		if (rs.next()) InterfazInquilino.imprimir(rs);
		
		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR INQUILINO //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// ELIMINAR INQUILINO //
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
	
	// CREAR VIVIENDA //
	public static void crearVivienda(Connection conex, Vivienda vivienda) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		vivienda = InterfazVivienda.solicitarDatos(conex, vivienda);

		/* Ejecutar sentencia de creación */
		ViviendaCRUD.crear(conex, vivienda);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR VIVIENDA //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR VIVIENDA //
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
			ViviendaCRUD.modificar(conex, vivienda, cod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE CÓDIGO **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// ELIMINAR VIVIENDA //
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
	
	// CREAR CONTRATO //
	public static void crearContrato(Connection conex, Contrato contrato) throws SQLException {
		
		/* Solicitar datos para crear objeto */
		contrato = InterfazContrato.solicitarDatos(contrato);
		
		/* Ejecutar sentencia de creación */
		ContratoCRUD.crear(conex, contrato);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CONSULTAR CONTRATO //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MODIFICAR CONTRATO //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// ELIMINAR CONTRATO //
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// CAMBIAR ESTADO CONTRATO //
	public static void cambiarEstadoContrato(Connection conex, Contrato contrato) {
		
	}
}