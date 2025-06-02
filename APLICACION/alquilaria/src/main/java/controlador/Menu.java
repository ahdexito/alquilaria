package controlador;

import modelo.ViviendaCRUD;
import modelo.InquilinoCRUD;
import modelo.PropietarioCRUD;
import java.sql.*;
import modelo.*;
import vista.*;

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
	
	public static void modificarPropietario(Connection conex, Propietario propietarioMod, ResultSet rs) throws SQLException {
		
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
			propietarioMod = InterfazPropietario.solicitarDatos(id);

			/* Modificar el objeto recibido con los datos solicitados */			
			PropietarioCRUD.modificar(conex, propietarioSinMod, propietarioMod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarPropietario(Connection conex, Propietario propietario) throws SQLException {
		
		/* Solicitar ID a eliminar */
		int id = InterfazGeneral.solicitarID();

		// Llamada al método eliminar //
		PropietarioCRUD.eliminar(conex, id);
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	
	public static void crearInquilino(Connection conex, Inquilino inquilino) throws SQLException {
		
		/* Solicitar datos para crear inquilino */
		inquilino = InterfazInquilino.solicitarDatos(0);

		/* Enviar inquilino a la base de datos */
		InquilinoCRUD.crear(conex, inquilino);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarInquilino(Connection conex, Inquilino inquilino, ResultSet rs) throws SQLException {
		
		/* Solicitar ID a buscar */
		int id = InterfazGeneral.solicitarID();

		System.out.println("");

		/* Llamada al método consultar-inquilino para recibir un ResultSet */
		rs = InquilinoCRUD.consultar(conex, id);

		/* Enviar el ResultSet al método para imprimir */
		InterfazInquilino.imprimir(rs);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarInquilino(Connection conex, Inquilino inquilinoMod, ResultSet rs) throws SQLException {
		
		/* Solicitar ID a modificar */
		int id = InterfazGeneral.solicitarID();

		/* Realizar consulta con el ID */
		rs = InquilinoCRUD.consultar(conex, id);

		/* Comprobar si existe algún campo con ese ID */
		if (rs.next()) {
			/* Guardar los datos de esa consulta en un objeto */
			Inquilino inquilinoSinMod = new Inquilino
				(id, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"), rs.getInt("mascotas"));

			/* Solicitar los nuevos datos y guardarlos en otro objeto */
			inquilinoMod = InterfazInquilino.solicitarDatos(id);

			/* Modificar el objeto recibido con los datos solicitados */
			InquilinoCRUD.modificar(conex, inquilinoSinMod, inquilinoMod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");						
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarInquilino(Connection conex, Inquilino inquilino) throws SQLException {
		
		/* Solicitar ID a eliminar */
		int id = InterfazGeneral.solicitarID();

		/* Llamada al método eliminar */
		InquilinoCRUD.eliminar(conex, id);
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	
	public static void crearVivienda(Connection conex, Vivienda vivienda) throws SQLException {
		
		/* Solicitar datos para crear vivienda */
		vivienda = InterfazVivienda.solicitarDatos(conex, vivienda);

		/* Enviar vivienda a la base de datos */
		ViviendaCRUD.crear(conex, vivienda);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarVivienda(Connection conex, Vivienda vivienda, ResultSet rs) throws SQLException {
		
		/* Solicitar ID a buscar */
		String cod = InterfazVivienda.solicitarCod();

		System.out.println("");

		/* Llamada al método consultar-vivienda para recibir un ResultSet */
		rs = ViviendaCRUD.consultar(conex, cod);

		/* Enviar el ResultSet al método para imprimir */
		InterfazVivienda.imprimir(rs);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarVivienda(Connection conex, Vivienda vivienda, ResultSet rs) throws SQLException {
		
		/* Solicitar COD a modificar */
		String cod = InterfazVivienda.solicitarCod();
		
		/* Realizar consulta con el COD */
		rs = ViviendaCRUD.consultar(conex, cod);

		/* Comprobar si existe algún campo con ese COD */
		if (rs.next()) {
			/* Guardar los datos de esa consulta en un objeto */
			vivienda = new Vivienda
				(cod, rs.getInt("id_propietario"), rs.getString("direccion"), rs.getFloat("precio"), rs.getFloat("superficie"), rs.getString("descripcion"), rs.getInt("mascotas"), rs.getInt("tipo"));
			
			/* Solicitar los nuevos datos y guardarlos */
			System.out.println("\nINTRODUCE LOS NUEVOS DATOS:");	
			vivienda = InterfazVivienda.solicitarDatosMod(conex, vivienda);

			/* Modificar el objeto recibido con los datos solicitados */
			ViviendaCRUD.modificar(conex, vivienda, cod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE CÓDIGO **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarVivienda(Connection conex, Vivienda vivienda) throws SQLException {
		
		/* Solicitar ID a eliminar */
		String cod = InterfazVivienda.solicitarCod();
		
		

		/* Llamada al método eliminar */
		ViviendaCRUD.eliminar(conex, cod);
	}
	
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	// / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /
	
	public static void crearContrato(Connection conex, Contrato contrato) throws SQLException {
		
		contrato = InterfazContrato.solicitarDatos(conex, contrato);
		
		ContratoCRUD.crear(conex, contrato);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void consultarContrato(Connection conex, Contrato contrato, ResultSet rs) throws SQLException {
		
		int idInquilino = InterfazGeneral.solicitarID();
		String codVivienda = InterfazVivienda.solicitarCod();
		Date fechaInicio = InterfazContrato.solicitarFecha();
		
		System.out.println("");
		
		rs = ContratoCRUD.consultar(conex, idInquilino, codVivienda, fechaInicio);
		
		InterfazContrato.imprimir(rs);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void modificarContrato(Connection conex, Contrato contrato, ResultSet rs) throws SQLException {
		
		/* Solicitar COD a modificar */
		int idPropietario = InterfazGeneral.solicitarID();
		String codVivienda = InterfazVivienda.solicitarCod();
		Date fechaInicio = InterfazContrato.solicitarFecha();
		
		/* Realizar consulta con el COD */
		rs = ContratoCRUD.consultar(conex, idPropietario, codVivienda, fechaInicio);

		/* Comprobar si existe algún campo con ese COD */
		if (rs.next()) {
			/* Guardar los datos de esa consulta en un objeto */
			contrato = new Contrato
				(idPropietario, codVivienda, fechaInicio, rs.getDate("fecha_fin"), rs.getFloat("precio"), rs.getString("estado"));
			
			/* Solicitar los nuevos datos y guardarlos */
			System.out.println("\nINTRODUCE LOS NUEVOS DATOS:");	
			contrato = InterfazContrato.solicitarDatosMod(conex, contrato);

			/* Modificar el objeto recibido con los datos solicitados */
			ContratoCRUD.modificar(conex, contrato);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n** NO SE HAN ENCONTRADO REGISTROS PARA ESE CÓDIGO **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void eliminarContrato(Connection conex, Contrato contrato) throws SQLException {
		
		int idInquilino = InterfazGeneral.solicitarID();
		String codVivienda = InterfazVivienda.solicitarCod();
		Date fechaInicio = InterfazContrato.solicitarFecha();
		
		ContratoCRUD.eliminar(conex, idInquilino, codVivienda, fechaInicio);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void cambiarEstadoContrato(Connection conex, Contrato contrato) {
		
	}
}