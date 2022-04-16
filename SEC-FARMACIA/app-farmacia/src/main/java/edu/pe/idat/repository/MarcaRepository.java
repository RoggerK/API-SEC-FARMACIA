package edu.pe.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
