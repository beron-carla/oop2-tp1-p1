package ejercicios;

import java.util.Objects;

public class Participante {
    public static final int PUNTOS_GANADOS = 10;
    public int id;
    public String nombre;
    public String dni;
    public String email;
    public int puntos;

    public Participante(int id, String nombre, String dni, String email){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public void sumarPuntos(){
        this.puntos += PUNTOS_GANADOS;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(dni, that.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public int id(){
        return this.id;
    }
    public String email(){
        return this.email;
    }
}
