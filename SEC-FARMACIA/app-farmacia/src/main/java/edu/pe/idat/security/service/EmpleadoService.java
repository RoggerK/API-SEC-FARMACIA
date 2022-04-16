package edu.pe.idat.security.service;

import edu.pe.idat.security.entity.Empleado;
import edu.pe.idat.security.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	public Optional<Empleado> getByCorreo(String correo) {
		return empleadoRepository.findByCorreo(correo);
	}
	
	public boolean existsByCorreo(String correo) {
		return empleadoRepository.existsByCorreo(correo);
	}

	public List<Empleado> list() {
		return empleadoRepository.findAll();
	}

	public Optional<Empleado> getOne(int id) {
		return empleadoRepository.findById(id);
	}
	

	
	public Long totalU() {
		return empleadoRepository.count();
	}

	public void delete(int id) {
		empleadoRepository.deleteById(id);
	}

	
	public boolean existsByDni(String dni) {
		return empleadoRepository.existsByDni(dni);
	}

	public boolean existsById(int id) {
		return empleadoRepository.existsById(id);
	}

	public void save(Empleado usuario) {
		empleadoRepository.save(usuario);
	}
}
