package edu.pe.idat.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoEmpleado {

	@NotBlank
	private String nombreEmpleado;
	
	@NotBlank
	private String apellidoEmpleado;

	@NotBlank
	private String sexoEmpleado;
	
	@NotBlank
	private String telefonoEmpleado;
	
	@NotBlank
	private String isActive;

	@NotBlank
	private String idCargoEmpleado;

	@Email
	@NotBlank
	private String correo;

	@NotBlank
	private String clave;
	
	@NotBlank
	private String dni;

	private Set<String> roles = new HashSet<>();

}
