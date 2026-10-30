package entidades;

import java.time.LocalTime;

public class Clases {
    
    private int idClase;
    private String disciplina;
    private Entrenadores entrenador;
    private LocalTime horario;
    private int capacidad;
    private boolean estado;

    public Clases() {
    }

    public Clases(int idClase, String disciplina, Entrenadores entrenador, LocalTime horario, int capacidad, boolean estado) {
        this.idClase = idClase;
        this.disciplina = disciplina;
        this.entrenador = entrenador;
        this.horario = horario;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Clases(String disciplina, Entrenadores entrenador, LocalTime horario, int capacidad, boolean estado) {
        this.disciplina = disciplina;
        this.entrenador = entrenador;
        this.horario = horario;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    
    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Entrenadores getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenadores entrenador) {
        this.entrenador = entrenador;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

   @Override
public String toString() {
    String nombreEntrenador = (entrenador != null)
            ? entrenador.getNombre() + " " + entrenador.getApellido()
            : "Sin asignar";

    return "Clase: " + disciplina + ", " + horario + "hs"
            + ", capacidad para " + capacidad + " personas."
            + " Profesor: " + nombreEntrenador;
}
    
    
    
}
