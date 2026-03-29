package ejercicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static ejercicios.Participante.PUNTOS_GANADOS;
import static org.junit.jupiter.api.Assertions.*;
public class ConcursoTest {
    Participante maria;
    Concurso concurso;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    LocalDate fechaInscripcion;
    RegistroDeInscriptos registro;

    @BeforeEach
    void setUp(){
        this.maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        this.fechaInicio = LocalDate.of(2025, 2, 11);
        this.fechaFin = LocalDate.of(2025, 2, 15);
        this.registro = new ArchivoDeInscriptos("F:\\proyectos\\sistemas\\2026-2028\\archivoInscriptos.txt");
        this.concurso = new Concurso(1, "concurso 1", fechaInicio, fechaFin,registro);
    }
    @Test
    public void testInscripciónExitosa() {
        //un participante se inscribe a un concurso
        //setup
        this.fechaInscripcion = LocalDate.of(2025, 2, 13);

        //ejercitación
        concurso.nuevaInscripcion(maria, fechaInscripcion);

        //verificación
        assertTrue(concurso.participanteInscripto(maria));
        assertEquals(1, concurso.cantidadInscriptos());
    }

    @Test
    public void testInscripcionPrimerDia() {
        //un participante se inscribe a un concurso el primer dia de abierta la inscripción
        //setup

        //ejercitación
        concurso.nuevaInscripcion(maria, fechaInicio);

        //verificación
        assertTrue(concurso.participanteInscripto(maria));
        assertEquals(1, concurso.cantidadInscriptos());
        assertEquals(PUNTOS_GANADOS, maria.puntos());
    }

    @Test
    public void testInscripcionFueraDeRango(){
        //un participante se inscribe fuera de rango
        //setup
        LocalDate fechaInscripcion = LocalDate.of(2025, 2, 16);

        //ejercitación y verificación
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
                    concurso.nuevaInscripcion(maria, fechaInscripcion);
                });

        //Verify
        assertEquals(0, concurso.cantidadInscriptos());
        assertNotNull(exception);
        assertEquals(Concurso.FECHA_FUERA_DE_RANGO, exception.getMessage());
        assertFalse(concurso.participanteInscripto(maria));
    }
    @Test
    public void testFechaNula() {
        //Creación de concurso con fecha null

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {

            new Concurso(1, "concurso 1", null, null, registro);

        });
        assertNotNull(exception);
        assertEquals(Concurso.FECHA_NULA, exception.getMessage());
    }
}
