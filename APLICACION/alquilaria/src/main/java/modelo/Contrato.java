package modelo;

import java.sql.Date;

public class Contrato {
    
    // ATRIBUTOS //
	private int idInquilino = -1;
	private String codVivienda, estado;
	private Date fechaInicio, fechaFin;
	private float precio = -1;
	
	// CONSTRUCTORES //

	public Contrato() {
	}

	public Contrato(int idInquilino, String codVivienda, Date fechaInicio, Date fechaFin, float precio, String estado) {
		this.idInquilino = idInquilino;
		this.estado = estado;
		this.codVivienda = codVivienda;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}
	
	// SETTERS Y GETTERS
	public int getIdInquilino() {
		return idInquilino;
	}

	public void setIdInquilino(int idInquilino) {
		this.idInquilino = idInquilino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodVivienda() {
		return codVivienda;
	}

	public void setCodVivienda(String codVivienda) {
		this.codVivienda = codVivienda;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}