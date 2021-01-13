package com.piramide.entities;

import java.io.Serializable;

public class Cliente implements Serializable {
    private Integer num;
    private String nombre, email;

    public Cliente(Integer num, String nombre, String email) {
        this.num = num;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "num=" + num +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
