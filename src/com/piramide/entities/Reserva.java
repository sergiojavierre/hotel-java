package com.piramide.entities;

import java.io.Serializable;
import java.util.Date;

public class Reserva implements Serializable {
    private Integer numero;
    private TipoHabitacion tipo;
    private Date fechaEntrada;
    private Integer noches;
    private Cliente cliente;

    public Reserva(Integer numero, TipoHabitacion tipo, Integer noches, Cliente cliente) {
        this.numero = numero;
        this.tipo = tipo;
        this.fechaEntrada = new Date();
        this.noches = noches;
        this.cliente = cliente;
    }

    public Integer getNumero(){
        return this.numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public Integer getNoches() {
        return noches;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "numero=" + numero +
                ", tipo=" + tipo +
                ", noches=" + noches +
                ", cliente=" + cliente +
                '}';
    }
}
