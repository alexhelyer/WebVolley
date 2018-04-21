package com.example.alejandro.webvolley.dto;

/**
 * Created by alejandro on 20/04/18.
 */

public class Pago {

    Long id;
    String token_paypal;
    Boolean completado;

    public Pago(Long id, String token_paypal, Boolean completado) {
        this.id = id;
        this.token_paypal = token_paypal;
        this.completado = completado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken_paypal() {
        return token_paypal;
    }

    public void setToken_paypal(String token_paypal) {
        this.token_paypal = token_paypal;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

}
