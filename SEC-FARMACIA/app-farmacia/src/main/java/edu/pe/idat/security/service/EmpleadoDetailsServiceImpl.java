package edu.pe.idat.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pe.idat.security.entity.Empleado;
import edu.pe.idat.security.entity.EmpleadoPrincipal;

@Service
public class EmpleadoDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmpleadoService empleadoService;

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Empleado empleado = empleadoService.getByCorreo(correo).get();
		return EmpleadoPrincipal.build(empleado);
	}

}
