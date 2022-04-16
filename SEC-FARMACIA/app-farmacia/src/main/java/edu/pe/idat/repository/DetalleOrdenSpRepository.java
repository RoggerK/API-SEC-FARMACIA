package edu.pe.idat.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pe.idat.model.sp.DetalleOrdenSp;

public interface DetalleOrdenSpRepository extends JpaRepository<DetalleOrdenSp, Long>{
	
	@Query(value = "{Call sp_ListSPDetalleOrden()}", nativeQuery = true)
    List<DetalleOrdenSp> listarConsulta();
	
	@Transactional
    @Modifying
	@Query(value = "{Call sp_ListSPFilDetalleOrden(:_codcompra)}", nativeQuery = true)
    List<DetalleOrdenSp> listarfilConsulta(@Param("_codcompra") String cod);
}
