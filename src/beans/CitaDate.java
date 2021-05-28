/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Alex
 */
public class CitaDate {
    private int idcita;
    private String motivo;
    private long fecha;
    private int idcliente;
    private int idmascota;

    public CitaDate() {
    }

    public CitaDate(int idcita, String motivo, long fecha, int idcliente, int idmascota) {
        this.idcita = idcita;
        this.motivo = motivo;
        this.fecha = fecha;
        this.idcliente = idcliente;
        this.idmascota = idmascota;
    }

    public CitaDate(String motivo, long fecha, int idCliente, int idMascota) {

        this.motivo = motivo;
        this.fecha = fecha;
        this.idcliente = idCliente;
        this.idmascota = idMascota;
    }

    public int getIdcita() {
        return idcita;
    }

    public String getMotivo() {
        return motivo;
    }

    public long getFecha() {
        return fecha;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }
    
    @Override
    public String toString() {
        return "CitaDate{" + "idcita=" + idcita + ", motivo=" + motivo + ", fecha=" + fecha + ", idcliente=" + idcliente + ", idmascota=" + idmascota + '}';
    }
}
