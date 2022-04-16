package edu.pe.idat.model.sp;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class ProductoSp implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    private Integer id_producto;

    private String imagen_producto;

    private String nombre_producto;

    private String prescripcion;

    private String presentacion;

    private Double precio;

    private Double precio_unitario;

    private Integer stock;

    private Integer is_active;

    private Integer id_categoria_producto;

    private String nombrecategoria;

    private Integer id_marca_producto;

    private String nombremarca;

}
