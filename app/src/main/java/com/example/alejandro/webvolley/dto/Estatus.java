package com.example.alejandro.webvolley.dto;

/**
 * Created by alejandro on 20/04/18.
 */

public class Estatus {

    Long id;
    String estado;

    public Estatus(Long id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
