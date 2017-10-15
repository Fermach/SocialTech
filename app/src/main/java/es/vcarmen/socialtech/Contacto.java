package es.vcarmen.socialtech;

/**
 * Created by matinal on 03/10/2017.
 */

public class Contacto {
    private String nombre;
    private String apellido;
    private int telefono;
    private char sexo;

    public Contacto(String nombre, String apellido, int telefono, char sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                ", sexo=" + sexo +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
