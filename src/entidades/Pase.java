/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author Pablo Carnota
 */
public class Pase {
    private int idPase;
    private Socios socio;
    private int cantidadEntradas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costo;
    private boolean estado;

    public Pase(int idPase, Socios socio, int cantidadEntradas, LocalDate fechaInicio, LocalDate fechaFin, double costo, boolean estado) {
        this.idPase = idPase;
        this.socio = socio;
        this.cantidadEntradas = cantidadEntradas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.estado = estado;
    }

    public Pase() {
    }

    public Pase(Socios socio, int cantidadEntradas, LocalDate fechaInicio, LocalDate fechaFin, double costo, boolean estado) {
        this.socio = socio;
        this.cantidadEntradas = cantidadEntradas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costo = costo;
        this.estado = estado;
    }

    public int getIdPase() {
        return idPase;
    }

    public void setIdPase(int idPase) {
        this.idPase = idPase;
    }

    public Socios getSocio() {
        return socio;
    }

    public void setSocio(Socios socio) {
        this.socio = socio;
    }

    public int getCantidadEntradas() {
        return cantidadEntradas;
    }

    public void setCantidadEntradas(int cantidadEntradas) {
        this.cantidadEntradas = cantidadEntradas;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Pase{" + "idPase=" + idPase 
                + ", socio=" + socio 
                + ", cantidadEntradas=" + cantidadEntradas
                + ", fechaInicio=" + fechaInicio 
                + ", fechaFin=" + fechaFin 
                + ", costo=" + costo 
                + ", estado=" + estado + '}';
    }
    
    
}
