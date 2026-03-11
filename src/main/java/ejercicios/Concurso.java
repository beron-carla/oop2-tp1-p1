package ejercicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    final String FECHA_NULA= "La fecha no puede ser null";
    final String FECHA_INICIO_INCORRECTA= "La fecha de inicio es incorrecta";
    final String FECHA_FIN_INCORRECTA= "La fecha de fin es incorrecta";
    final String FECHA_FUERA_DE_RANGO= "La fecha de inscripción está fuera de rango";

    private String nombre;
    private int id;
    private List<Participante> inscriptos;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaInscripcion;

    public Concurso (int id, String nombre, LocalDate fechaInicio, LocalDate fechaFin){
        if (fechaInicio.isAfter(fechaFin)){
            throw new RuntimeException(FECHA_INICIO_INCORRECTA);
        }
        if (fechaFin.isBefore(fechaInicio)){
            throw new RuntimeException(FECHA_FIN_INCORRECTA);
        }
        if (fechaNull(fechaFin) || fechaNull(fechaInicio)){
            throw new RuntimeException(FECHA_NULA);
        }
        this.id = id;
        this.inscriptos = new ArrayList<>();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre= nombre;

    }

    public boolean participanteInscripto(Participante participante){
        return this.inscriptos.contains(participante);
    }

    public void nuevaInscripcion(Participante participante, LocalDate fechaInscripcion){
        if (validarFechas(fechaInscripcion)){
            throw new RuntimeException(FECHA_FUERA_DE_RANGO);
        }
        else{
            gestionarInscripcion(participante, fechaInscripcion);
        }
    }

    private boolean validarFechas(LocalDate fechaInscripcion){
        return fechaInscripcion.isBefore(this.fechaInicio) || fechaInscripcion.isAfter(this.fechaFin);
    }

    private void gestionarInscripcion(Participante participante, LocalDate fechaInscripcion){
        if (!participanteInscripto(participante)){
            this.inscriptos.add(participante);
            this.fechaInscripcion = fechaInscripcion;
            gestionarPuntos(participante, fechaInscripcion);
        }
    }
    private void gestionarPuntos(Participante participante, LocalDate fechaInscripcion){
        if(this.fechaInicio.isEqual(fechaInscripcion)){
            participante.sumarPuntos();
        }
    }

    private boolean fechaNull(LocalDate fecha) {
        if (fecha == null){
            return true;
        }
        return false;
    }
}
