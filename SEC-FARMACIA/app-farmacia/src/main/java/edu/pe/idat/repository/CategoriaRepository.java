package edu.pe.idat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

}
