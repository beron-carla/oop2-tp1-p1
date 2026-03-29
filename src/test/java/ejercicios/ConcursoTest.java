package ejercicios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    RegistroInscriptosFake registro;
    ServiceMailFake serviceMailFake;
    String esperado;

    @BeforeEach
    void setUp(){
        this.maria = new Participante(1, "maria", "23456789", "mperez@gmail.com");
        this.fechaInicio = LocalDate.of(2026, 2, 11);
        this.fechaFin = LocalDate.of(2026, 2, 15);
        this.serviceMailFake = new ServiceMailFake();
        this.registro = new RegistroInscriptosFake();
        this.concurso = new Concurso(1, "concurso 1", fechaInicio, fechaFin,registro, serviceMailFake);
    }
    @Test
    @DisplayName("Un participante se inscribe a un concurso")
    public void testInscripciónExitosa() {
        //setup
        this.fechaInscripcion = LocalDate.of(2026, 2, 13);
        //ejercitación
        concurso.nuevaInscripcion(maria, fechaInscripcion);
        String esperado = "13/02/2026, 1, 1\n";
        //verificación
        assertTrue(concurso.participanteInscripto(maria));
        assertEquals(1, concurso.cantidadInscriptos());
        assertTrue(registro.startWith("13/02/2026"));
        assertEquals(esperado.replace("\n", System.lineSeparator()),
                registro.data());
        assertEquals("mperez@gmail.com - Inscripcion: Su inscripción al concurso fue exitosa.", serviceMailFake.mail());
    }

    @Test
    @DisplayName("un participante se inscribe a un concurso el primer dia de abierta la inscripción")
    public void testInscripcionPrimerDia() {
        //setup

        //ejercitación
        concurso.nuevaInscripcion(maria, fechaInicio);
        String esperado = "11/02/2026, 1, 1\n";

        //verificación
        assertTrue(concurso.participanteInscripto(maria));
        assertEquals(1, concurso.cantidadInscriptos());
        assertEquals(PUNTOS_GANADOS, maria.puntos());
        assertTrue(registro.startWith("11/02/2026"));
        assertEquals(esperado.replace("\n", System.lineSeparator()),
                registro.data());
        assertEquals("mperez@gmail.com - Inscripcion: Su inscripción al concurso fue exitosa.", serviceMailFake.mail());
    }

    @Test
    @DisplayName("Un participante se inscribe fuera de rango")
    public void testInscripcionFueraDeRango(){

        //setup
        LocalDate fechaInscripcion = LocalDate.of(2026, 2, 16);

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
    @DisplayName("Creación de concurso con fecha null")
    public void testFechaNula() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {

            new Concurso(1, "concurso 1", null, null, registro,serviceMailFake );

        });
        assertNotNull(exception);
        assertEquals(Concurso.FECHA_NULA, exception.getMessage());
    }
}
