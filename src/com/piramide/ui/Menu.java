package com.piramide.ui;

import com.piramide.dao.ControlReservas;
import com.piramide.dao.ControlReservasFile;
import com.piramide.dao.DAOFactory;
import com.piramide.entities.Cliente;
import com.piramide.entities.Reserva;
import com.piramide.entities.TipoHabitacion;

import java.util.Scanner;

public class Menu {

    //NO HACER :(
    //static ControlReservas controlReservas = new ControlReservasFile();

    public static void muestraPrincipal(){
        while(true) {
            System.out.print("1) Crear reserva\n2) Buscar reserva\n0) Salir\nOpción: ");
            Scanner scanner = new Scanner(System.in);
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion == 1) {
                introduceReserva();
            } else if (opcion == 2) {
                muestraBusquedaReserva();
            }
            else if(opcion == 0){
                break;
            }
        }
    }

    private static void introduceReserva(){
        Scanner scanner = new Scanner(System.in);
        //datos de la reserva
        System.out.print("Nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        Cliente cliente = new Cliente(null,nombreCliente,null);
        System.out.print("Número de reserva: ");
        Integer numeroReserva = Integer.parseInt(scanner.nextLine());
        //creo la reserva
        Reserva reserva = new Reserva(numeroReserva, TipoHabitacion.INDIVIDUAL,1,cliente);
        //guardo la reserva
        //controlReservas.inserta(reserva);
        DAOFactory.getInstance().getControlReservas().inserta(reserva);
    }

    private static void muestraBusquedaReserva(){
        System.out.print("Introduce nº de reserva: ");
        Scanner scanner = new Scanner(System.in);
        //leo el nº
        int numReserva = Integer.parseInt(scanner.nextLine());
        //creo un objeto que busca
        //busco en la persistencia
        //Reserva reserva = controlReservas.busca(numReserva);
        Reserva reserva = DAOFactory.getInstance().getControlReservas().busca(numReserva);
        System.out.println(reserva);
    }
}
