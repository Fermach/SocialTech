package com.example.fermach.socialtech;

import java.io.Serializable;


public class Company implements Serializable {
    String id;
    String nombre;
    String direccion;
    String localidad;
    String provincia;
    String telefono;
    String email;
    String observaciones;
    String contacto_asociado;


    public Company() {

    }

    public Company(String id, String nombre, String direccion, String localidad, String provincia, String telefono, String email, String observaciones, String contacto_asociado) {
        this.id=id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
        this.observaciones = observaciones;
        this.contacto_asociado = contacto_asociado;
    }

    public Company(String nombre, String direccion, String localidad, String provincia, String telefono, String email, String observaciones, String contacto_asociado) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
        this.observaciones = observaciones;
        this.contacto_asociado = contacto_asociado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getContacto_asociado() {
        return contacto_asociado;
    }

    public void setContacto_asociado(String contacto_asociado) {
        this.contacto_asociado = contacto_asociado;
    }

}
