package modelo;

import java.sql.*;

public class Propietario {
    
	// MÉTODO PARA CREAR UN CLIENTE //
    public static void crear
			(Connection conex, String dni, String nombre, String apellido1, String apellido2, String email, String telefono) 
				throws SQLException {
		
		/* ASEGURAR QUE LA VARIABLE QUE SE ENVÍA SEA NULL SI NO SE INSERTÓ NINGÚN VALOR 
		PARA QUE SE RECOJA EL ERROR EN CAMPOS CON RESTRICCIÓN NOTNULL */
		dni = dni.isEmpty() ? null : dni;
		nombre = nombre.isEmpty() ? null : nombre;
		apellido1 = apellido1.isEmpty() ? null : apellido1;
		apellido2 = apellido2.isEmpty() ? null : apellido2;
		email = email.isEmpty() ? null : email;
		telefono = telefono.isEmpty() ? null : telefono;
		
		CallableStatement cs = conex.prepareCall("{call sp_insertCliente(?, ?, ?, ?, ?, ?)}");
		
		cs.setString(1, dni);
		cs.setString(2, nombre);
		cs.setString(3, apellido1);
		cs.setString(4, apellido2);
		cs.setString(5, email);
		cs.setString(6, telefono);
		
		int filas = cs.executeUpdate();
		
		System.out.println("\n" + CIAN + "  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA CONSULTAR UN CLIENTE //
	public static ResultSet consultar(Connection conex, int id) throws SQLException {
		
		CallableStatement cs = conex.prepareCall("{call sp_getCliente(?)}");
		cs.setInt(1, id);
		ResultSet rs = cs.executeQuery();
		return rs;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA MODIFICAR UN CLIENTE //
	public static void modificar
			(Connection conex, int id, String dni, String nombre, String apellido1, String apellido2, String email, String telefono) 
				throws SQLException {
		
		CallableStatement cs = conex.prepareCall("{call sp_modifyCliente(?, ?, ?, ?, ?, ?, ?)}");
		
		cs.setInt(1, id);
		cs.setString(2, dni);
		cs.setString(3, nombre);
		cs.setString(4, apellido1);
		cs.setString(5, apellido2);
		cs.setString(6, email);
		cs.setString(7, telefono);
		
		int filas = cs.executeUpdate();
		
		System.out.println("\n" + CIAN + "  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// MÉTODO PARA ELIMINAR UN CLIENTE //
	public static void eliminar(Connection conex, int id) throws SQLException {
		
		CallableStatement cs = conex.prepareCall("{call sp_deleteCliente(?)}");
		cs.setInt(1, id);
		
		int filas = cs.executeUpdate();
		
		System.out.println("\n" + CIAN + "  ** OPERACIÓN REALIZADA CON " + filas + " FILAS AFECTADAS **");
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// VARIABLES PARA DAR COLOR A LA SALIDA POR CONSOLA //
	public static final String RESET = "\u001B[0m", MORADO = "\u001B[35m", ROJO = "\u001B[31m",
			AZUL = "\u001B[34m", CIAN = "\u001B[36m", VERDE = "\u001B[32m", AMARILLO = "\u001B[33m";
}