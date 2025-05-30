package controlador;

public class Menu {
    
    public static void crearPropietario() {
		
		/* Solicitar datos para crear propietario */
		propietario = InterfazPropietario.solicitarDatos(0);

		/* Enviar propietario a la base de datos */
		propietario.crear(conex);
	}
	
	public static void consultarPropietario() {
		
		/* Solicitar ID a buscar */
		id = Imprimir.solicitarID();

		System.out.println("");

		/* Llamada al método consultar-propietario para recibir un ResultSet */
		rs = Propietario.consultar(conex, id);

		/* Enviar el ResultSet al método para imprimir */
		InterfazPropietario.imprimir(rs);
	}
	
	public static void modificarPropietario() {
		
		/* Solicitar ID a modificar */
		id = Imprimir.solicitarID();

		/* Realizar consulta con el ID */
		rs = Propietario.consultar(conex, id);

		/* Comprobar si existe algún campo con ese ID */
		if (rs.next()) {
			/* Guardar los datos de esa consulta en un objeto */
			Propietario propietarioSinMod = new Propietario
				(id, rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("correo"), rs.getString("telefono"));

			/* Solicitar los nuevos datos y guardarlos en otro objeto */
			propietario = InterfazPropietario.solicitarDatos(id);

			/* Modificar el objeto recibido con los datos solicitados */
			propietario.modificar(conex, propietarioSinMod);
		}

		/* Si no existe se cancela la operación */
		else System.out.println("\n  ** NO SE HAN ENCONTRADO REGISTROS PARA ESE ID **");
	}
	
	public static void eliminarPropietario() {
		
		/* Solicitar ID a eliminar */
		id = Imprimir.solicitarID();

		// Llamada al método eliminar //
		Propietario.eliminar(conex, id);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void crearInquilino() {
		
		/* Solicitar datos para crear inquilino */
		InterfazInquilino.crear(inquilino);

		/* Enviar inquilino a la base de datos */
		inquilino.crear(conex);
	}
	
	public static void consultarInquilino() {
		
		/* Solicitar ID a buscar */
		id = InterfazInquilino.solicitarID();

		System.out.println("");

		/* Llamada al método consultar-inquilino para recibir un ResultSet */
		rs = Inquilino.consultar(conex, id);

		/* Enviar el ResultSet al método para imprimir */
		InterfazInquilino.imprimir(rs);
	}
	
	public static void modificarInquilino() {
		
	}
	
	public static void eliminarInquilino() {
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void crearVivienda() {
		
	}
	
	public static void consultarVivienda() {
		
	}
	
	public static void modificarVivienda() {
		
	}
	
	public static void eliminarVivienda() {
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void crearContrato() {
		
	}
	
	public static void consultarContrato() {
		
	}
	
	public static void modificarContrato() {
		
	}
	
	public static void eliminarContrato() {
		
	}
	
	public static void cambiarEstadoContrato() {
		
	}
}