package edu.pe.idat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detallorden")
@ToString
@EqualsAndHashCode
public class DetalleOrden {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddetalleorden")
	private Long iddetalleorden;
	@Column(name = "nompro")
	private String nompro;
	@Column(name = "canpro")
	private Integer canpro;
	@Column(name = "precpro")
	private Double precpro;
	@Column(name = "total")
	private Double total;
	@Column(name = "id_producto")
	private Integer id_producto;
	@Column(name = "id_compraorden")
	private Integer id_compraorden;
}
