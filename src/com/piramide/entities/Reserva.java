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

    @Override
    public String toString() {
        return "Reserva{" +
                "numero=" + numero +
                ", tipo=" + tipo +
                ", fechaEntrada=" + fechaEntrada +
                ", noches=" + noches +
                ", cliente=" + cliente +
                '}';
    }
}
