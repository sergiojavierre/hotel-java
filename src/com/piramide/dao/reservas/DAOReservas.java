package com.piramide.dao.reservas;

import com.piramide.entities.Reserva;

public interface DAOReservas {
    public void inserta(Reserva reserva);
    public Reserva busca(int numero);
}
