package edu.pe.idat.security.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginEmpleado {

	 	@NotBlank
	    private String correo;
	 	
	    @NotBlank
	    private String clave;
	   
}
