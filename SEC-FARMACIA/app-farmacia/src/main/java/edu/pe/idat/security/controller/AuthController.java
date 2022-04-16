package edu.pe.idat.security.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pe.idat.model.Mensaje;
import edu.pe.idat.security.dto.JwtDto;
import edu.pe.idat.security.dto.LoginEmpleado;
import edu.pe.idat.security.dto.NuevoEmpleado;
import edu.pe.idat.security.entity.Empleado;
import edu.pe.idat.security.entity.Rol;
import edu.pe.idat.security.enums.RolNombre;
import edu.pe.idat.security.jwt.JwtProvider;
import edu.pe.idat.security.service.EmpleadoService;
import edu.pe.idat.security.service.RolService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Empleado>> list() {
		List<Empleado> list = empleadoService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoEmpleado nuevoEmpleado, BindingResult bindingResult) {
	        if(bindingResult.hasErrors())
	            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);

	        if(empleadoService.existsByDni(nuevoEmpleado.getDni()))
	            return new ResponseEntity(new Mensaje("El DNI ya existe"), HttpStatus.BAD_REQUEST);
	        	        
	        if(empleadoService.existsByCorreo(nuevoEmpleado.getCorreo()))
	            return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);

		Empleado empleado = new Empleado(nuevoEmpleado.getNombreEmpleado(), nuevoEmpleado.getApellidoEmpleado(), 
				nuevoEmpleado.getSexoEmpleado(), nuevoEmpleado.getTelefonoEmpleado(), nuevoEmpleado.getIsActive(), nuevoEmpleado.getIdCargoEmpleado(),
				nuevoEmpleado.getCorreo(), passwordEncoder.encode(nuevoEmpleado.getClave()), nuevoEmpleado.getDni());

		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

		if (nuevoEmpleado.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

		empleado.setRoles(roles);
		empleadoService.save(empleado);

		return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Empleado empleadoEnt) {
		if (!empleadoService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);


		Empleado persona = empleadoService.getOne(id).get();
		
		persona.setNombreEmpleado(empleadoEnt.getNombreEmpleado());
		persona.setApellidoEmpleado(empleadoEnt.getApellidoEmpleado());
		persona.setSexoEmpleado(empleadoEnt.getSexoEmpleado());
		persona.setTelefonoEmpleado(empleadoEnt.getTelefonoEmpleado());
		persona.setIsActive(empleadoEnt.getIsActive());
		persona.setIdCargoEmpleado(empleadoEnt.getIdCargoEmpleado());
		persona.setCorreo(empleadoEnt.getCorreo());
		persona.setClave(passwordEncoder.encode(empleadoEnt.getClave()));
		persona.setDni(empleadoEnt.getDni());
		persona.setRoles(empleadoEnt.getRoles());

		empleadoService.save(persona);
		return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/detail/{id}")
	public ResponseEntity<Empleado> getById(@PathVariable("id") int id) {
		if (!empleadoService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Empleado persona = empleadoService.getOne(id).get();
		return new ResponseEntity(persona, HttpStatus.OK);
	}
	

    @GetMapping("/cantidad")
    public ResponseEntity totalUsuarios(){
        Long cantidad = empleadoService.totalU();
        return new ResponseEntity(cantidad, HttpStatus.OK);
    }
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!empleadoService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		empleadoService.delete(id);
		return new ResponseEntity(new Mensaje("usuario eliminado"), HttpStatus.OK);
	}

	@PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginEmpleado loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(),
                		loginUsuario.getClave()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
	

	@PostMapping("/refresh")
	public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
		String token = jwtProvider.refreshToken(jwtDto);
		JwtDto jwt = new JwtDto(token);
		return new ResponseEntity(jwt, HttpStatus.OK);
	}
}
