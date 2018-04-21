package com.example.alejandro.webvolley.dto;

/**
 * Created by alejandro on 20/04/18.
 */

public class Jugo {

    Long id;
    String sabor;
    String tam;
    int precio;

    public Jugo(Long id, String sabor, String tam, int precio) {
        this.id = id;
        this.sabor = sabor;
        this.tam = tam;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
