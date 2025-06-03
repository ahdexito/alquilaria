import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import modelo.Propietario;
import modelo.PropietarioCRUD;

/**
 *
 * @author Ángel García Smakula
 */
public class PropietarioCRUDTest {
	
	private static Connection conex;
	
	// CREAR UNA BASE DE DATOS DE PRUEBA Y LA TABLA DE PROPIETARIO //
	@BeforeAll
	public static void crearTabla() throws SQLException {
		conex = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
		String sql = """
        CREATE TABLE propietario (
            id INT PRIMARY KEY AUTO_INCREMENT,
            dni VARCHAR(10) NOT NULL UNIQUE,
            nombre VARCHAR(20) NOT NULL,
            apellidos VARCHAR(50) NOT NULL,
            correo VARCHAR(100) NOT NULL,
            telefono VARCHAR(15)
			);
		""";
		conex.createStatement().execute(sql);
	}
	
	@Test
	public void testCrearConsultar() throws SQLException {
		
		/* Crear nuevo objeto */
		Propietario propietario  = new Propietario
			(0, "12345678A", "Pepe", "García González", "pepe@gmail.com", "655433211");
		
		/* Ejecutar sentencia de creación */
		PropietarioCRUD.crear(conex, propietario);
		
		/* Declarar sentencia de consulta */
		String query = "SELECT * FROM propietario WHERE dni = '12345678A'";
		
		/* Crear objeto ResultSet y ejecutar consulta */
		ResultSet rs = conex.createStatement().executeQuery(query);
		
		/* Verificar que ha habido consulta */
		assertTrue(rs.next());
		
		/* Verificar que nombre coincide */
		assertEquals("Pepe", rs.getString("nombre"));
		
		/* Verificar que telefono coincide */
		assertEquals("655433211", rs.getString("telefono"));
	}
	
	@Test
	public void testModificar() throws SQLException {
	
		/* Crear nuevo objeto */
		Propietario propietario = new Propietario
			(0, "98765432R", "Jose", "Pérez Gómez", "jose@gmail.com", "667889000");
		
		/* Ejecutar sentencia de creación */
		PropietarioCRUD.crear(conex, propietario);
		
		/* Declarar sentencia de consulta */
		String query = "SELECT *  FROM propietario WHERE dni = '98765432R'";
		
		/* Crear objeto ResultSet y ejecutar consulta */
		ResultSet rs = conex.createStatement().executeQuery(query);
	
		/* Obtener ID del campo obtenido */
		rs.next();
		int id = rs.getInt("id");		
		
		/* Crear nuevo objeto que modificará el anterior */
		propietario = new Propietario
			(id, "98765432R", "Jose Manuel", "Pérez Gómez", "jose@gmail.com", "666555444");
		
		/* Ejecutar sentencia de modificación */
		PropietarioCRUD.modificar(conex, propietario);
		
		/* Declarar sentencia de consulta */
		query = "SELECT * FROM propietario WHERE id = " + id;
		
		/* Declarar ResultSet y ejecutar consulta */
		rs = conex.createStatement().executeQuery(query);
		
		rs.next();
		
		/* Verificar que el nuevo nombre coincide */
		assertEquals("Jose Manuel", rs.getString("nombre"));
		
		/* Verificar que el nuevo teléfono coincide */
		assertEquals("666555444", rs.getString("telefono"));
	}
	
	@Test
	public void testEliminar() throws SQLException {
		
		/* Crear nuevo objeto */
		Propietario propietario = new Propietario
			(0, "11223344C", "Alberto", "López Rodríguez", "alberto@gmail.com", "665544333");
		
		/* Ejecutar sentencia de creación */
		PropietarioCRUD.crear(conex, propietario);
		
		/* Declarar sentencia de consulta */
		String query = "SELECT * FROM propietario WHERE dni = '11223344C'";
		
		/* Crear objeto ResultSet y ejecutar consulta */
		ResultSet rs = conex.createStatement().executeQuery(query);
		
		/* Obtener ID del campo obtenido */
		rs.next();
		int id = rs.getInt("id");
		
		/* Ejecutar sentencia de eliminación */
		PropietarioCRUD.eliminar(conex, id);
		
		/* Declarar sentencia de consulta */
		query = "SELECT * FROM propietario WHERE id = " + id;
		
		/* Declarar ResultSet y ejecutar consulta */
		rs = conex.createStatement().executeQuery(query);
		
		/* Verificar que la consulta no devuelve nada */
		assertFalse(rs.next());
	}
	
	@AfterAll
	public static void cerrar() throws SQLException {
		conex.close();
	}
}
