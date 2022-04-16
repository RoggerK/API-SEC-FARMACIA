package idat.edu.pe.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.pe.idat.security.entity.Rol;
import edu.pe.idat.security.enums.RolNombre;
import edu.pe.idat.security.service.RolService;
/*
@Component
public class CreateRoles implements CommandLineRunner {
	
	//se ejecuta solo una vez para crear los roles
	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {
		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
		Rol rolUser = new Rol(RolNombre.ROLE_USER);
		rolService.save(rolAdmin);
		rolService.save(rolUser);
	}
	
}
*/
