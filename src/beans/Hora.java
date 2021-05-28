/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "Hora")
public class Hora implements Serializable {

    @Id
    @Column(name = "idhora")
    private int idhora;

    @Column(name = "hora")
    private String hora;

    public Hora() {
    }

    public Hora(int idhora, String hora) {
        this.idhora = idhora;
        this.hora = hora;
    }

    public int getIdhora() {
        return idhora;
    }

    public String getHora() {
        return hora;
    }
    
    
}