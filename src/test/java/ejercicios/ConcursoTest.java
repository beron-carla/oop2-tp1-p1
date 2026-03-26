package ejercicios;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static ejercicios.Participante.PUNTOS_GANADOS;
import static org.junit.jupiter.api.Assertions.*;
public class ConcursoTest {

    @Test
    public void testInscripciónExitosa() {
        //un participante se inscribe a un concurso
        //setup
        var maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2025, 3, 15);
        Concurso unConcurso = new Concurso(1, "concurso 1", fechaInicio, fechaFin);

        //ejercitación
        unConcurso.nuevaInscripcion(maria, fechaInscripcion);

        //verificación
        assertTrue(unConcurso.participanteInscripto(maria));
        assertEquals(1, unConcurso.cantidadInscriptos());
    }
    @Test
    public void testInscripcionPrimerDia(){
        //un participante se inscribe a un concurso el primer dia de abierta la inscripción
        //setup
        var maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
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
        LocalDate fechaInicio = LocalDate.of(2025, 3, 1);
        LocalDate fechaFin = LocalDate.of(2025, 3, 31);
        LocalDate fechaInscripcion = LocalDate.of(2026, 3, 12);
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
        assertEquals("La fecha de inscripción está fuera de rango", exception.getMessage());
        assertFalse(unConcurso.participanteInscripto(maria));

    }
    @Test
    public void testFechaNula(){
        //Creación de concurso con fecha null
        RuntimeException execption = null;
        try {
            Concurso unConcurso = new Concurso(1, "concurso 1", null, null);
        }catch (RuntimeException excepcion){
            execption = excepcion;
        }
        assertNotNull(execption);
        assertEquals("La fecha no puede ser null", execption.getMessage());
    }
}
