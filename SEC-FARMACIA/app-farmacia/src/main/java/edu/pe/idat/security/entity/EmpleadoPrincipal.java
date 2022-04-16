package edu.pe.idat.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmpleadoPrincipal implements UserDetails {


	private static final long serialVersionUID = 2230986947551153765L;
	
	private String nombreEmpleado;
	private String apellidoEmpleado;
	private String sexoEmpleado;
	private String telefonoEmpleado;
	private String isActive;
	private String idCargoEmpleado;
	private String correo;
	private String clave;
	private String dni;
	private Collection<? extends GrantedAuthority> authorities;
	
	public EmpleadoPrincipal(String nombreEmpleado, String apellidoEmpleado, String sexoEmpleado,
			String telefonoEmpleado, String isActive, String idCargoEmpleado, String correo, String clave, String dni,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoEmpleado = apellidoEmpleado;
		this.sexoEmpleado = sexoEmpleado;
		this.telefonoEmpleado = telefonoEmpleado;
		this.isActive = isActive;
		this.idCargoEmpleado = idCargoEmpleado;
		this.correo = correo;
		this.clave = clave;
		this.dni = dni;
		this.authorities = authorities;
	}

	public static EmpleadoPrincipal build(Empleado usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombreRol().name())).collect(Collectors.toList());

		return new EmpleadoPrincipal(usuario.getNombreEmpleado(), 
				usuario.getApellidoEmpleado(), usuario.getSexoEmpleado(), 
				usuario.getTelefonoEmpleado(), usuario.getIsActive(), usuario.getIdCargoEmpleado(),
				usuario.getCorreo(), usuario.getClave(), usuario.getDni(),
				authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return clave;
	}
	@Override
	public String getUsername() {
		return correo;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	
	public String getCorreo() {
		return correo;
	}
	
}
