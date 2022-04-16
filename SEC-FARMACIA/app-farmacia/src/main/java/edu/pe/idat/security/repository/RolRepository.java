package edu.pe.idat.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pe.idat.security.entity.Rol;
import edu.pe.idat.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	 Optional<Rol> findByNombreRol(RolNombre nombreRol);
}
