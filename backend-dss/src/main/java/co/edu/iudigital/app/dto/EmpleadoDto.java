package co.edu.iudigital.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpleadoDto {
	
	private Long id;
	private String nombre;
	private String apellido;
	private Double sueldo;
	
	@JsonProperty("id_usuario")
	private Long idUsuario;
	/**
	 * @return the id
	 */
	public Long getId() { 
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	/**
	 * @return the sueldo
	 */
	public Double getSueldo() {
		return sueldo;
	}
	/**
	 * @param sueldo the sueldo to set
	 */
	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}
	/**
	 * @return the idUsuario
	 */
	public Long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	

}
