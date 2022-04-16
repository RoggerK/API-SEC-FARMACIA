package edu.pe.idat.model.sp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class EmpleadoSp {

    @Id
    private String idempleado;

    private String nombreempleado;

    private String apellidoempleado;

    private String sexoempleado;

    private String telefonoempleado;

    private Integer isactive;

    private String idcargoempleado;

    private String nombrecargo;

}
