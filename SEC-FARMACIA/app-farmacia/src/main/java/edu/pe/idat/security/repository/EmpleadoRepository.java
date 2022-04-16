package edu.pe.idat.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pe.idat.security.entity.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	Optional<Empleado> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    
    Optional<Empleado> findByDni(String dni);
    boolean existsByDni(String dni);
}
