package com.example.alejandro.webvolley.dto;

import java.io.Serializable;

/**
 * Created by alejandro on 19/04/18.
 */

public class Cliente implements Serializable{

    Long id;
    String nombre;
    String direccion;



    public Cliente(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
