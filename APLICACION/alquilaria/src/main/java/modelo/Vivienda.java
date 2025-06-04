package modelo;

/**
 * Clase que representa una vivienda con sus características principales, como dirección, precio y tipo.
 *
 * @author Ángel García Smakula
 */

public class Vivienda {
    
    // ATRIBUTOS //
	private String cod, direccion, descripcion;
	private int idPropietario, tipo, mascotas;
	private float precio, superficie;
	
	// CONSTRUCTORES //
	public Vivienda() {
	}

	public Vivienda(String cod, int idPropietario, String direccion, float precio, float superficie, String descripcion, int mascotas, int tipo) {
		this.cod = cod;
		this.idPropietario = idPropietario;
		this.direccion = direccion;
		this.precio = precio;
		this.superficie = superficie;
		this.descripcion = descripcion;
		this.mascotas = mascotas;
		this.tipo = tipo;
	}
		
	// SETTERS Y GETTERS //
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getMascotas() {
		return mascotas;
	}

	public void setMascotas(int mascotas) {
		this.mascotas = mascotas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getSuperficie() {
		return superficie;
	}

	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}		
}