package edu.pe.idat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Orden;

@Repository
public interface OrdenRepository  extends JpaRepository<Orden,Long>{
	
	@Transactional
	@Modifying
	@Query(value ="{call sp_RegistrarCompraOrden(:_fechaorden,:_totalorden)}",
			nativeQuery = true)
	void registrarOrden(@Param("_fechaorden") String fechaorden,
						@Param("_totalorden") Double totalorden);
	
	@Query(value ="select orden_ultimo()",
			nativeQuery = true)
	String ultimocodOrden();
}
