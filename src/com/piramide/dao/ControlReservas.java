package com.piramide.dao;

import com.piramide.entities.Reserva;

public interface ControlReservas {
    public void inserta(Reserva reserva);
    public Reserva busca(int numero);
}
