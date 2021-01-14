package com.piramide.dao;

import com.piramide.dao.reservas.DAOReservas;
import com.piramide.dao.reservas.DAOReservasFile;
import com.piramide.dao.reservas.DAOReservasXML;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOReservas DAOReservas;
    private DAOFactory(){}

    public static DAOFactory getInstance() {
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public DAOReservas getDAOReservas(){
        if(DAOReservas == null){
            DAOReservas = new DAOReservasXML();
        }
        return DAOReservas;
    }

}
