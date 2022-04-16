package edu.pe.idat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.model.Categoria;
import edu.pe.idat.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
    
	@Autowired
    private CategoriaRepository categoriarepository;

    public List<Categoria> listarCategoria() {
        return (List<Categoria>) categoriarepository.findAll();
    }
    
    public Categoria crearCategoria(Categoria c) {
        return categoriarepository.save(c);
    }
    
    public Categoria buscarCategoriaId(Integer id) {
        return categoriarepository.findById(id).get();
    }
    
    public void eliminarCategoriaId(Integer id) {
        categoriarepository.deleteById(id);
    }
    
}
