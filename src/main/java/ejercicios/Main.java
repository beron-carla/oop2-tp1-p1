package ejercicios;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Participante participante = new Participante(1, "Carla", "32456789", "alrac@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 2, 11);
        LocalDate fechaFin = LocalDate.of(2025, 2, 15);
        RegistroDeInscriptos registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\2026-2028\\archivoInscriptos.txt");
        Concurso concurso = new Concurso(1, "ConcursoGanador",fechaInicio, fechaFin, registro);
        concurso.nuevaInscripcion(participante, fechaInicio);
    }
}
