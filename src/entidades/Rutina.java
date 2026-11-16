/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author matia
 */
public class Rutina {
    private int idRutina;
    private String nombreEjercicio;
    private String grupoMuscular;
    private String descripcion;
    private String rutaAnimacion; //ubicacion de las animaciones
    private boolean estado;
    
    public Rutina(){}

    public Rutina(String nombreEjercicio, String grupoMuscular, String descripcion, String rutaAnimacion, boolean estado) {
 
        this.nombreEjercicio = nombreEjercicio;
        this.grupoMuscular = grupoMuscular;
        this.descripcion = descripcion;
        this.rutaAnimacion = rutaAnimacion;
        this.estado = estado;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaAnimacion() {
        return rutaAnimacion;
    }

    public void setRutaAnimacion(String rutaAnimacion) {
        this.rutaAnimacion = rutaAnimacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
    return nombreEjercicio + "("+ grupoMuscular +")";}
    
    
}
