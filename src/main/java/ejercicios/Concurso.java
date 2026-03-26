package ejercicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    public static final String FECHA_NULA= "La fecha no puede ser null";
    public static final String FECHA_INICIO_INCORRECTA= "La fecha de inicio es incorrecta";
    public static final String FECHA_FIN_INCORRECTA= "La fecha de fin es incorrecta";
    public static final String FECHA_FUERA_DE_RANGO= "La fecha de inscripción está fuera de rango";

    private String nombre;
    private int id;
    private List<Participante> inscriptos;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;


    public Concurso (int id, String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if (fechaNull(fechaFin) || fechaNull(fechaInicio)){
            throw new RuntimeException(FECHA_NULA);
        }
        if (fechaInicio.isAfter(fechaFin)){
            throw new RuntimeException(FECHA_INICIO_INCORRECTA);
        }
        if (fechaFin.isBefore(fechaInicio)){
            throw new RuntimeException(FECHA_FIN_INCORRECTA);
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

    public void nuevaInscripcion(Participante participante, LocalDateTime fechaInscripcion){
        if (validarFechas(fechaInscripcion)){
            throw new RuntimeException(FECHA_FUERA_DE_RANGO);
        }
        else{
            gestionarInscripcion(participante, fechaInscripcion);
        }
    }

    private boolean validarFechas(LocalDateTime fechaInscripcion){
        return fechaInscripcion.isBefore(this.fechaInicio) || fechaInscripcion.isAfter(this.fechaFin);
    }

    private void gestionarInscripcion(Participante participante, LocalDateTime fechaInscripcion){
        if (!participanteInscripto(participante)){
            this.inscriptos.add(participante);
            participante.fechaDeInscripcion(fechaInscripcion);
            gestionarPuntos(participante, fechaInscripcion);
        }
    }
    private void gestionarPuntos(Participante participante, LocalDateTime fechaInscripcion){
        if(this.fechaInicio.isEqual(participante.fechaInscripcion)){
            participante.sumarPuntos();
        }
    }

    private boolean fechaNull(LocalDateTime fecha) {
        return fecha == null;
    }
    public int cantidadInscriptos(){
        return this.inscriptos.size();
    }
}
