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
@Table(name = "compraorden")
@ToString
@EqualsAndHashCode
public class Orden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcompraorden")
	private Long idcompraorden;
	@Column(name = "codcompra")
	private String codcompra;
	@Column(name = "fechaorden")
	private String fechaorden;
	@Column(name = "totalorden")
	private Double totalorden;
}
