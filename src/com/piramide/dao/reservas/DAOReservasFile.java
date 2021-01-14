package com.piramide.dao.reservas;

import com.piramide.entities.Reserva;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOReservasFile implements DAOReservas, Serializable {

    List<Reserva> reservas;

    private final String fileReservas = "reservas";

    public DAOReservasFile() {
        reservas = new ArrayList<>();
    }

    @Override
    public void inserta(Reserva reserva) {
        reservas.add(reserva);
        try {
            FileOutputStream fos = new FileOutputStream(fileReservas);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reserva busca(int numero) {
        try {
            FileInputStream fis = new FileInputStream(fileReservas);
            ObjectInputStream ois = new ObjectInputStream(fis);
            DAOReservasFile control = (DAOReservasFile) ois.readObject();
            this.reservas = control.reservas;
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < reservas.size(); i++){
            Reserva reserva = reservas.get(i);
            if(reserva.getNumero() == numero){
                return reserva;
            }
        }
        return null;
    }
}
