package edu.pe.idat.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.model.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

	@Transactional
    @Modifying
    @Query(value = "{call sp_MantRegistrarProducto(:imagen_producto, :nombre_producto, :prescripcion, :presentacion,:precio,:precio_unitario,:stock,  :isactive,:id_categoria_producto,:id_marca_producto)}", nativeQuery = true)
    void registrarProducto(
                    @Param("imagen_producto") String imagenproducto,
                    @Param("nombre_producto") String nombreproducto,
                    @Param("prescripcion") String prescripcion,
                    @Param("presentacion") String presentacion,
                    @Param("precio") Double precio,
                    @Param("precio_unitario") Double preciounitario,
                    @Param("stock") Integer stock,
                    @Param("isactive") Integer isactive,
                    @Param("id_categoria_producto") Integer idcategoria,
                    @Param("id_marca_producto") Integer idmarca);

    @Transactional
    @Modifying
    @Query(value = "{call sp_MantActualizarProducto(:id_producto,:imagen_producto, :nombre_producto, :prescripcion, :presentacion,:precio,:precio_unitario,:stock,  :isactive,:id_categoria_producto,:id_marca_producto)}", nativeQuery = true)
    void actualizarProducto(
                    @Param("id_producto") Integer id_producto,
                    @Param("imagen_producto") String imagenproducto,
                    @Param("nombre_producto") String nombreproducto,
                    @Param("prescripcion") String prescripcion,
                    @Param("presentacion") String presentacion,
                    @Param("precio") Double precio,
                    @Param("precio_unitario") Double preciounitario,
                    @Param("stock") Integer stock,
                    @Param("isactive") Integer isactive,
                    @Param("id_categoria_producto") Integer idcategoria,
                    @Param("id_marca_producto") Integer idmarca);

}
