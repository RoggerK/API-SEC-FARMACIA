package edu.pe.idat.model.sp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class DetalleOrdenSp {
	@Id
	private Long iddetalleorden;
	private String codcompra;
	private String fechaorden;
	private Double totalorden;
	private String nompro;
	private Integer canpro;
	private Double precpro;
	private Double total;
}
