package co.edu.iudigital.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Email requerido")
	@Email(message = "Debe ser un email")
	@Column(unique = true, length = 120)
	private String username;
	
	@NotEmpty(message = "Nombre requerido")
	private String nombre;
	
	private String apellido;
	
	@NotEmpty(message = "Contraseña requerida")
	private String password;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "roles_usuarios", 
			joinColumns = 
		{@JoinColumn(name = "usuarios_id")},
			inverseJoinColumns = 
		{@JoinColumn(name = "roles_id")}
	)
	private List<Role> roles;
	
	
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
