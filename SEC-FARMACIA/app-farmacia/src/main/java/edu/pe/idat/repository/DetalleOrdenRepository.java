package edu.pe.idat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.DetalleOrden;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long>{
	
	@Transactional
	@Modifying
	@Query(value ="{call sp_RegistrarDetalleOrden(:_nompro,:_canpro,:_precpro,:_total,:_id_producto)}",
			nativeQuery = true)
	void registrarDetalleOrden(@Param("_nompro") String nompro,
							   @Param("_canpro") Integer canpro,
							   @Param("_precpro") Double precpro,
							   @Param("_total") Double total,
							   @Param("_id_producto") Integer id_producto);
	
}
