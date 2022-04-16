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
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "proveedor")
@XmlRootElement
public class Proveedor implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Integer idproveedor;

    @Column(name = "nombreProveedor")
    private String nombreproveedor;

    @Column(name = "rucProveedor")
    private String rucproveedor;

    @Column(name = "telefonoProveedor")
    private String telefonoproveedor;

    @Column(name = "direccionProveedor")
    private String direccionproveedor;

    @Column(name = "isActive")
    private Integer isactive;
}
