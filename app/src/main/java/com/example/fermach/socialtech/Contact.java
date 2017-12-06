package com.example.fermach.socialtech;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.io.Serializable;


public class Contact implements Serializable {
    private String id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String formacion;
    private String provincia;
    private int edad;
    private String sexo;
    private int imagen;

    public Contact() {
    }

    public Contact(String id, int imagen, String nombre, String apellido, String telefono, String email, String formacion, String provincia, int edad, String sexo) {
        this.id=id;
        this.imagen=imagen;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.formacion = formacion;
        this.provincia = provincia;
        this.edad = edad;
        this.sexo= sexo;
    }
    public Contact(int imagen, String nombre, String apellido, String telefono, String email, String formacion, String provincia, int edad, String sexo) {
        this.imagen=imagen;
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
                "  id= "+ id +
                ", nombre= " + nombre  +
                ", apellido= " + apellido +
                ", telefono= " + telefono +
                ", email= " + email +
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Exclude
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}