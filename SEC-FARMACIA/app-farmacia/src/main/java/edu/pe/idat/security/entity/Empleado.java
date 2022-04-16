package edu.pe.idat.security.entity;

import java.util.HashSet;
import java.util.Set;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@NoArgsConstructor
@Getter
@Setter
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpleado;

	@Column(name = "nombreEmpleado", length = 90, nullable = true)
	private String nombreEmpleado;

	@Column(name = "apellidoEmpleado", length = 90, nullable = true)
	private String apellidoEmpleado;

	@Column(name = "sexoEmpleado", length = 1, nullable = true)
	private String sexoEmpleado;

	@Column(name = "telefonoEmpleado", length = 9, nullable = true)
	private String telefonoEmpleado;

	@Column(name = "isActive", length = 1, nullable = false)
	private String isActive;

	@Column(name = "idCargoEmpleado", length = 1, nullable = true)
	private String idCargoEmpleado;

	@Column(name = "correo", length = 255, unique = true, nullable = false)
	private String correo;

	@Column(name = "clave", length = 255, nullable = false)
	private String clave;
	
	@Column(name = "dni", length = 15, unique = true ,nullable = false)
	private String dni;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	public Empleado(String nombreEmpleado, String apellidoEmpleado, String sexoEmpleado, String telefonoEmpleado,
			String isActive, String idCargoEmpleado, String correo, String clave, String dni) {
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoEmpleado = apellidoEmpleado;
		this.sexoEmpleado = sexoEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.isActive = isActive;
		this.idCargoEmpleado = idCargoEmpleado;
		this.correo = correo;
		this.clave = clave;
		this.dni = dni;
	}

	

	

}
