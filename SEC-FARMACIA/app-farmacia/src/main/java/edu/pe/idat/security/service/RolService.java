package edu.pe.idat.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pe.idat.security.entity.Rol;
import edu.pe.idat.security.enums.RolNombre;
import edu.pe.idat.security.repository.RolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepository rolRepository;

	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolRepository.findByNombreRol(rolNombre);
	}

	public void save(Rol rol) {
		rolRepository.save(rol);
	}
}
