package com.example.alejandro.webvolley.dto;

/**
 * Created by alejandro on 19/04/18.
 */

public class Repartidor {

    Long id;
    String nombre;
    String placas;
    String direccion;

    public Repartidor(Long id, String nombre, String placas, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.placas = placas;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
