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
@Table(name = "Cliente")
public class Cliente implements Serializable {
    static final long serialVersionUID=34L;

    @Id
    @Column(name = "idcliente")
    private int idcliente;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "poblacion")
    private String poblacion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "clave")
    private String clave;
    
    @Column(name = "baja")
    private Date baja;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String apellidos, String direccion, String poblacion, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.telefono = telefono;
        this.email = email;
        this.clave = "1234";
        this.baja=null;
    }

    public Cliente(String dni, String nombre, String apellidos, String direccion, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.clave = "1234";
        this.baja=null;
    }

    public int getIdcliente() {
        return idcliente;
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

    public String getDireccion() {
        return direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

}
