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
public class Asistencia {
    private int idAsistencia;
    private Socios socio;
    private Clases clase;
    private LocalDate fechaAsistencia;

    public Asistencia(int idAsistencia, Socios socio, Clases clase, LocalDate fechaAsistencia) {
        this.idAsistencia = idAsistencia;
        this.socio = socio;
        this.clase = clase;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Asistencia() {
    }

    public Asistencia(Socios socio, Clases clase, LocalDate fechaAsistencia) {
        this.socio = socio;
        this.clase = clase;
        this.fechaAsistencia = fechaAsistencia;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Socios getSocio() {
        return socio;
    }

    public void setSocio(Socios socio) {
        this.socio = socio;
    }

    public Clases getClase() {
        return clase;
    }

    public void setClase(Clases clase) {
        this.clase = clase;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    @Override
    public String toString() {
        return "Asistencia{" + "idAsistencia=" + idAsistencia + ", socio=" + socio + ", clase=" + clase + ", fechaAsistencia=" + fechaAsistencia + '}';
    }
    
    
    
    
    
}
