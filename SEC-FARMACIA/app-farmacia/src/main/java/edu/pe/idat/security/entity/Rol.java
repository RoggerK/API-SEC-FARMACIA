package edu.pe.idat.security.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import edu.pe.idat.security.enums.RolNombre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rol_usuario")
@Getter
@Setter
@NoArgsConstructor
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol_Usuario;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolNombre nombreRol;
	
	public Rol(@NotNull RolNombre nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	
}
