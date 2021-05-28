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
@Table(name = "Veterinario")
public class Veterinario implements Serializable{
    @Id
    @Column(name="idveterinario")
    private int idveterinario;
    
    @Column(name="dni")
    private String dni;

    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="fechanacimiento")
    private Date fechanacimiento;
    
    @Column(name="especialidad")
    private String especialidad;
    
    @Column(name="clave")
    private String clave;

    public Veterinario() {
    }

    public int getIdveterinario() {
        return idveterinario;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
