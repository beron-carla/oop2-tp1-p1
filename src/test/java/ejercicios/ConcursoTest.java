package ejercicios;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static ejercicios.Participante.PUNTOS_GANADOS;
import static org.junit.jupiter.api.Assertions.*;
public class ConcursoTest {

    @Test
    public void testInscripciónExitosa() {
        //un participante se inscribe a un concurso
        //setup
        var maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        LocalDateTime fechaInicio = LocalDateTime.of(2025, 2, 11, 12, 10, 5);
        LocalDateTime fechaFin = LocalDateTime.of(2025, 2, 15, 12, 20, 1);
        LocalDateTime fechaInscripcion = LocalDateTime.of(2025, 2, 13, 12, 20, 1);
        Concurso unConcurso = new Concurso(1, "concurso 1", fechaInicio, fechaFin);

        //ejercitación
        unConcurso.nuevaInscripcion(maria, fechaInscripcion);

        //verificación
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void testInscripcionPrimerDia() {
        //un participante se inscribe a un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        LocalDateTime fechaInicio = LocalDateTime.of(2025, 2, 11, 12, 10, 5);
        LocalDateTime fechaFin = LocalDateTime.of(2025, 2, 15, 12, 20, 1);
        Concurso unConcurso = new Concurso(1, "concurso 1", fechaInicio, fechaFin);

        //ejercitación
        unConcurso.nuevaInscripcion(maria, fechaInicio);

        //verificación
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());
        assertEquals(PUNTOS_GANADOS, maria.puntos());

    }

    @Test
    public void testInscripcionFueraDeRango(){
        //un participante se inscribe fuera de rango
        //setup
        var maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        LocalDateTime fechaInicio = LocalDateTime.of(2025, 2, 11, 12, 10, 5);
        LocalDateTime fechaFin = LocalDateTime.of(2025, 2, 15, 12, 20, 1);
        LocalDateTime fechaInscripcion = LocalDateTime.of(2025, 2, 16, 12, 20, 1);
        Concurso unConcurso = new Concurso(1, "concurso 1", fechaInicio, fechaFin);

    //opcion 2 separar exercise de verify
        RuntimeException exception = null;
        try {
            unConcurso.nuevaInscripcion(maria, fechaInscripcion);
        } catch (RuntimeException excepcion) {
            exception = excepcion;
        }
        //Verify
        //verificar que al inscribir a un participante se invoque al m enviarCorreo
        assertEquals(0, unConcurso.cantidadInscriptos());
        assertNotNull(exception);
        assertEquals(Concurso.FECHA_FUERA_DE_RANGO, exception.getMessage());
        assertFalse(unConcurso.participanteInscripto(maria));

    }
    @Test
    public void testFechaNula() {
        //Creación de concurso con fecha null

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {

            new Concurso(1, "concurso 1", null, null);

        });
        assertNotNull(exception);
        assertEquals(Concurso.FECHA_NULA, exception.getMessage());
    }
}
