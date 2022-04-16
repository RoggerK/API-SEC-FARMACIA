package edu.pe.idat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.pe.idat.model.Marca;
import edu.pe.idat.repository.MarcaRepository;

@Service
@Transactional
public class MarcaService {
    
	@Autowired
    private MarcaRepository marcarepository;

    public List<Marca> listarMarca() {
        return marcarepository.findAll();
    }
    
    public Marca crearMarca(Marca marca) {
        return marcarepository.save(marca);
    }
    
    public Marca buscarMarcaId(Integer id) {
        return marcarepository.findById(id).get();
    }
    
    public void eliminarMarcaId(Integer id) {
        marcarepository.deleteById(id);
    }
    
}
