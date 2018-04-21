package com.example.alejandro.webvolley.dto;

import java.util.List;

/**
 * Created by alejandro on 20/04/18.
 */

public class Orden {

    Long id;
    Cliente cliente;
    Sucursal sucursal;
    Repartidor repartidor;
    List<Jugo> jugos;
    Cobro cobro;
    Pago pago;
    Estatus estatus;

    public Orden() {
    }

    public Orden(Long id, Cliente cliente, Sucursal sucursal, Repartidor repartidor, List<Jugo> jugos, Cobro cobro, Pago pago, Estatus estatus) {
        this.id = id;
        this.cliente = cliente;
        this.sucursal = sucursal;
        this.repartidor = repartidor;
        this.jugos = jugos;
        this.cobro = cobro;
        this.pago = pago;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public List<Jugo> getJugos() {
        return jugos;
    }

    public void setJugos(List<Jugo> jugos) {
        this.jugos = jugos;
    }

    public Cobro getCobro() {
        return cobro;
    }

    public void setCobro(Cobro cobro) {
        this.cobro = cobro;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

}
