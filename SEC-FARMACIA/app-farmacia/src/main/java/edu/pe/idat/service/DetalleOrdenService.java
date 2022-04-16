package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.DetalleOrden;
import edu.pe.idat.model.sp.DetalleOrdenSp;
import edu.pe.idat.repository.DetalleOrdenRepository;
import edu.pe.idat.repository.DetalleOrdenSpRepository;

@Service
public class DetalleOrdenService {
	
	@Autowired
	private DetalleOrdenRepository repository;
	
	@Autowired
	private DetalleOrdenSpRepository repositorysp;
	
	public void registrardatalle(DetalleOrden detalle) {
		repository.registrarDetalleOrden(detalle.getNompro(), detalle.getCanpro(), detalle.getPrecpro(), detalle.getTotal(), detalle.getId_producto());
	}
	
	public List<DetalleOrdenSp> listarorden(){
		return repositorysp.listarConsulta();
	}
	
	public List<DetalleOrdenSp> listarfilorden(String cod){
		if(cod.equals("")) {
			return repositorysp.listarConsulta();
		}else {
			return repositorysp.listarfilConsulta(cod);
		}
	}
}
