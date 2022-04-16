package edu.pe.idat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.sp.ProductoSp;

@Repository
public interface ProductoSpRepository extends JpaRepository<ProductoSp, Integer> {
    @Query(value = "{Call sp_MantListarProducto()}", nativeQuery = true)
    List<ProductoSp> listarProducto();
    
    
    @Query(value = "{Call sp_MantProductoSpId(:_idpro)}", nativeQuery = true)
    ProductoSp listaProductoId(
                    @Param("_idpro") Integer idpro);
}
