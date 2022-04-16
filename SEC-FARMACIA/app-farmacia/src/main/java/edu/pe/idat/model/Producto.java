package edu.pe.idat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "producto")
@XmlRootElement
public class Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private Integer id_producto;

	@Column(name = "imagen_producto")
	private String imagenproducto;

	@Column(name = "nombre_producto")
	private String nombreproducto;

	@Column(name = "prescripcion")
	private String prescripcion;

	@Column(name = "presentacion")
	private String presentacion;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "precio_unitario")
	private Double preciounitario;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "is_active")
	private Integer is_active;

	@Column(name = "id_categoria_producto")
	private Integer idcategoria;

	@Column(name = "id_marca_producto")
	private Integer idmarca;
	
}
