/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "Cita")
public class Cita implements Serializable {
    static final long serialVersionUID=43L;

    @Id
    @Column(name = "idcita")
    private int idcita;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "idcliente")
    private int idcliente;

    @Column(name = "idmascota")
    private int idmascota;

    public Cita() {
    }

    public Cita(int idcita, String motivo, Date fecha, int idcliente, int idmascota) {
        this.idcita = idcita;
        this.motivo = motivo;
        this.fecha = fecha;
        this.idcliente = idcliente;
        this.idmascota = idmascota;
    }
    
    public Cita(String motivo, Date fecha, int idcliente, int idmascota) {
        this.motivo = motivo;
        this.fecha = fecha;
        this.idcliente = idcliente;
        this.idmascota = idmascota;
    }   

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdcita() {
        return idcita;
    }

    public String getMotivo() {
        return motivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }
    
}
