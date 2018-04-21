package com.example.alejandro.webvolley.dto;

/**
 * Created by alejandro on 20/04/18.
 */

public class Cobro {

    Long id;
    Integer total;
    String token_paypal;
    Boolean completado;

    public Cobro(Long id, Integer total, String token_paypal, Boolean completado) {
        this.id = id;
        this.total = total;
        this.token_paypal = token_paypal;
        this.completado = completado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
