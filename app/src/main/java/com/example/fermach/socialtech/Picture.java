package com.example.fermach.socialtech;

import java.io.Serializable;

public class Picture implements Serializable {
    int imagen;
    String nombre;

    public Picture() {
    }

    public Picture(int imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }
    public Picture(int imagen) {
        this.imagen = imagen;
        this.nombre = "Sin nombre";
    }


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
