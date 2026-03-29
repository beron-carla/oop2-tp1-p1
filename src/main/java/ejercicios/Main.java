package ejercicios;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Participante participante = new Participante(1, "Carla", "32456789", "alracnoreb@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 2, 11);
        LocalDate fechaFin = LocalDate.of(2025, 2, 15);
        RegistroDeInscriptos registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\2026-2028\\archivoInscriptos.txt");
//        RegistroDeInscriptos registroBD = new PersistenciaInscriptos();
        var servicioEmail = new ServiceMail("6c8de69c97c85a", "bce2c11456ac07", "true", "true", "sandbox.smtp.mailtrap.io", "2525");

        Concurso concurso = new Concurso(1, "ConcursoGanador",fechaInicio, fechaFin, registro,servicioEmail );
        concurso.nuevaInscripcion(participante, fechaInicio);
    }
}
