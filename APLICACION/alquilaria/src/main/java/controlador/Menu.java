package controlador;

import java.sql.*;
import modelo.*;
import vista.*;
import database.*;

public class Menu {
    
    public static void crearPropietario(Connection conex, Propietario propietario) throws SQLException {
		
		/* Solicitar datos para crear propietario */
		propietario = InterfazPropietario.solicitarDatos(0);

		/* Enviar propietario a la base de datos */
		PropietarioCRUD.crear(conex, propietario);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarPropietario(Connection conex, Propietario propietario, ResultSet rs) throws SQLException {
		
		/* Solicitar ID a buscar */
		int id = InterfazGeneral.solicitarID();

		System.out.println("");

		/* Llamada al método consultar-propietario para recibir un ResultSet */
		rs = PropietarioCRUD.consultar(conex, id);

		/* Enviar el ResultSet al método para imprimir */
		InterfazPropietario.imprimir(rs);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarPropietario(Connection conex, Propietario propietario, ResultSet rs) throws SQLException {
		
		/* Solicitar ID a modificar */
		int id = InterfazGeneral.solicitarID();

		/* Realizar consulta con el ID */
		rs = PropietarioCRUD.consultar(conex, id);

		/* Comprobar si existe algún campo con ese ID */
		if (rs.next()) {
			/* Guardar los datos de esa consulta en un objeto */
			Propietario propietarioSinMod = new Propietario
				(id, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"));

			/* Solicitar los nuevos datos y guardarlos en otro objeto */
			propietario = InterfazPropietario.solicitarDatos(id);

			/* Modificar el objeto recibido con los datos solicitados */			
			PropietarioCRUD.modificar(conex, propietarioSinMod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarPropietario(Connection conex, Propietario propietario) throws SQLException {
		
		/* Solicitar ID a eliminar */
		int id = InterfazGeneral.solicitarID();

		// Llamada al método eliminar //
		PropietarioCRUD.eliminar(conex, id);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void crearInquilino(Connection conex, Inquilino inquilino) {
		
		/* Solicitar datos para crear inquilino */
		InterfazInquilino.crear(inquilino);

		/* Enviar inquilino a la base de datos */
		inquilino.crear(conex);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarInquilino(Connection conex, Inquilino inquilino) {
		
		/* Solicitar ID a buscar */
		id = InterfazInquilino.solicitarID();

		System.out.println("");

		/* Llamada al método consultar-inquilino para recibir un ResultSet */
		rs = Inquilino.consultar(conex, id);

		/* Enviar el ResultSet al método para imprimir */
		InterfazInquilino.imprimir(rs);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarInquilino(Connection conex, Inquilino inquilino) {
		
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
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarInquilino(Connection conex, Inquilino inquilino) {
		
		/* Solicitar ID a eliminar */
		id = InterfazInquilino.solicitarID();

		// Llamada al método eliminar //
		Inquilino.eliminar(conex, id);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void crearVivienda(Connection conex, Vivienda vivienda) {
		
		/* Solicitar datos para crear vivienda */
		InterfazVivienda.crear(conex, vivienda);

		/* Enviar vivienda a la base de datos */
		vivienda.crear(conex);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarVivienda(Connection conex, Vivienda vivienda) {
		
		/* Solicitar ID a buscar */
		cod = InterfazVivienda.solicitarID();

		System.out.println("");

		/* Llamada al método consultar-vivienda para recibir un ResultSet */
		rs = Vivienda.consultar(conex, cod);

		/* Enviar el ResultSet al método para imprimir */
		InterfazVivienda.imprimir(rs);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarVivienda(Connection conex, Vivienda vivienda) {
		
		/* Solicitar ID a modificar */
		cod = InterfazVivienda.solicitarID();

		/* Realizar consulta con el ID */
		rs = Vivienda.consultar(conex, cod);

		/* Comprobar si existe algún campo con ese ID */
		if (rs.next()) {
			/* Guardar los datos de esa consulta en un objeto */
			Vivienda viviendaSinMod = new Vivienda
				(cod, rs.getInt("id_propietario"), rs.getString("direccion"), rs.getFloat("precio"), rs.getFloat("superficie"), rs.getString("descripcion"), rs.getInt("mascotas"), rs.getInt("tipo"));

			/* Solicitar los nuevos datos y guardarlos en otro objeto */
			vivienda = InterfazVivienda.modificar(cod);

			/* Modificar el objeto recibido con los datos solicitados */
			vivienda.modificar(conex, viviendaSinMod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarVivienda(Connection conex, Vivienda vivienda) {
		
		/* Solicitar ID a eliminar */
		cod = InterfazVivienda.solicitarID();

		// Llamada al método eliminar //
		Vivienda.eliminar(conex, cod);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void crearContrato(Connection conex, Contrato contrato) {
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarContrato(Connection conex, Contrato contrato) {
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarContrato(Connection conex, Contrato contrato) {
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarContrato(Connection conex, Contrato contrato) {
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void cambiarEstadoContrato(Connection conex, Contrato contrato) {
		
	}
}