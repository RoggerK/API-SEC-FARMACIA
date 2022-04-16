package edu.pe.idat.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.repository.OrdenRepository;

@Service
public class OrdenService {
	
	@Autowired
	private OrdenRepository repository;
	
	public void registrar(Double totalorden) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
        repository.registrarOrden((dtf.format(LocalDateTime.now())), totalorden);
	}
	
	public String ultima(){
		return repository.ultimocodOrden();
	}
}
