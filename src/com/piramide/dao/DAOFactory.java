package com.piramide.dao;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private ControlReservas controlReservas;
    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public ControlReservas getControlReservas(){
        if(controlReservas == null){
            controlReservas = new ControlReservasFile();
        }
        return controlReservas;
    }

}
