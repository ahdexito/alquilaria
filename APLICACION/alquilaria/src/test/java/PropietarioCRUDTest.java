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
			(0, "12345678A", "Pepe", "García", "Gonzalez", "655433211");
		
		/* Ejecutar sentencia de creación */
		PropietarioCRUD.crear(conex, propietario);
		
		/* Declarar sentencia de consulta */
		String query = "SELECT * FROM propietario WHERE dni = '12345678A'";
		
		/* Crear objeto ResultSet en base a la consulta */
		ResultSet rs = conex.createStatement().executeQuery(query);
		
		/* Verificar que ha habido consulta */
		assertTrue(rs.next());
		
		/* Verificar que nombre coincide */
		assertEquals("Pepe", rs.getString("nombre"));
		
		/* Verificar que telefono coincide */
		assertEquals("655433211", rs.getString("telefono"));
	}
	
	@AfterAll
	public static void cerrar() throws SQLException {
		conex.close();
	}
}
