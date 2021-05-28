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
@Table(name = "HistorialClinico")
public class HistorialClinico implements Serializable{
    static final long serialVersionUID=36L;
    
    @Id
    @Column(name = "idhistorialclinico")
    private int idhistorialclinico;
    
    @Column(name = "juicioclinico")
    private String juicicoclinico;
    
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "pagado")
    private String pagado;
    
    @Column(name = "idmascota")
    private int idmascota;
    
    @Column(name = "idveterinario")
    private int idveterinario;
    
    @Column(name = "idtratamiento")
    private int idtratamiento;

    public HistorialClinico() {
    }

    public HistorialClinico(String juicicoclinico, Date fecha, String pagado, int idmascota, int idveterinario, int idtratamiento) {
        this.juicicoclinico = juicicoclinico;
        this.fecha = fecha;
        this.pagado = pagado;
        this.idmascota = idmascota;
        this.idveterinario = idveterinario;
        this.idtratamiento = idtratamiento;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdhistorialclinico() {
        return idhistorialclinico;
    }

    public String getJuicicoclinico() {
        return juicicoclinico;
    }

    public Date getFecha() {
        return fecha;
    }

    public String isPagado() {
        return pagado;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public int getIdveterinario() {
        return idveterinario;
    }

    public int getIdtratamiento() {
        return idtratamiento;
    }

    public void setJuicicoclinico(String juicicoclinico) {
        this.juicicoclinico = juicicoclinico;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public void setIdveterinario(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    public void setIdtratamiento(int idtratamiento) {
        this.idtratamiento = idtratamiento;
    }
    
    
}
