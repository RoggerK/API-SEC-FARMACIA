package edu.pe.idat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Proveedor;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Integer> {

    @Transactional
    @Modifying
    @Query(value = "{call sp_MantRegistrarProveedor(:nombreproveedor, :rucproveedor, :telefonoproveedor, :direccionproveedor, :isactive)}", nativeQuery = true)
    void registrarProveedor(@Param("nombreproveedor") String nombreproveedor,
            @Param("rucproveedor") String rucproveedor,
            @Param("telefonoproveedor") String telefonoproveedor,
            @Param("direccionproveedor") String direccionproveedor,
            @Param("isactive") Integer isactive);

    @Transactional
    @Modifying
    @Query(value = "{call sp_MantActualizarProveedor(:idproveedor, :nombreproveedor, :rucproveedor, :telefonoproveedor, :direccionproveedor, :isactive)}", nativeQuery = true)
    void actualizarProveedor(@Param("idproveedor") Integer idproveedor,
            @Param("nombreproveedor") String nombreproveedor,
            @Param("rucproveedor") String rucproveedor,
            @Param("telefonoproveedor") String telefonoproveedor,
            @Param("direccionproveedor") String direccionproveedor,
            @Param("isactive") Integer isactive);
}