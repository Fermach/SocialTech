package com.example.fermach.socialtech;

import java.io.Serializable;

/**
 * Created by matinal on 03/10/2017.
 */

public class Contact implements Serializable {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String formacion;
    private String provincia;
    private int edad;
    private String sexo;

    public Contact(String nombre, String apellido, String telefono, String email, String formacion, String provincia, int edad,String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.formacion = formacion;
        this.provincia = provincia;
        this.edad = edad;
        this.sexo= sexo;
    }




    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                ", email=" + email +
                ", formacion= " + formacion +
                ", provincia= " + provincia +
                ", edad= " + edad +
                ", sexo= " + sexo +

                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(char sexo) {
        this.email = email;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}