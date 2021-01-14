package com.piramide.dao.reservas;

import com.piramide.entities.Cliente;
import com.piramide.entities.Reserva;
import com.piramide.entities.TipoHabitacion;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOReservasXML implements DAOReservas{

    private final String fileReservas = "reservas.xml";

    private List<Reserva> leeDatos(){
        List<Reserva> reservas = new ArrayList<>();
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(fileReservas);
            Element elementoRaiz = dom.getDocumentElement();
            NodeList elementosReserva = elementoRaiz.getElementsByTagName("reserva");
            for(int i = 0; i < elementosReserva.getLength(); i++){
                Node elementoReserva = elementosReserva.item(i);
                Node elementoCliente = elementoReserva.getFirstChild().getNextSibling();
                NamedNodeMap atributosReserva = elementoReserva.getAttributes();
                NamedNodeMap atributosCliente = elementoCliente.getAttributes();
                TipoHabitacion tipo;
                if(atributosReserva.getNamedItem("tipo").getNodeValue().equals("INDIVIDUAL")){
                    tipo = TipoHabitacion.INDIVIDUAL;
                }
                else{
                    tipo = TipoHabitacion.DOBLE;
                }
                Cliente cliente = new Cliente(null,atributosCliente.getNamedItem("nombre").getNodeValue(),null);
                Reserva reserva = new Reserva(
                        Integer.parseInt(atributosReserva.getNamedItem("numero").getNodeValue()),
                        tipo,
                        Integer.parseInt(atributosReserva.getNamedItem("noches").getNodeValue()),
                        cliente
                );
                reservas.add(reserva);
            }
        } catch (ParserConfigurationException | SAXException pce) {
            System.out.println(pce.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return reservas;
    }

    @Override
    public void inserta(Reserva reserva) {
        List<Reserva> reservas = leeDatos();
        reservas.add(reserva);
        Document dom;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element elementoRaiz = dom.createElement("reservas");
            for(Reserva reservaActual: reservas){
                //creo el elemento reserva
                Element elementoReserva = dom.createElement("reserva");
                elementoReserva.setAttribute("numero",reservaActual.getNumero().toString());
                elementoReserva.setAttribute("tipo",reservaActual.getTipo().name());
                elementoReserva.setAttribute("fecha",reservaActual.getFechaEntrada().toString());
                elementoReserva.setAttribute("noches",reservaActual.getNoches().toString());
                //creo el elemento cliente
                Element elementoCliente = dom.createElement("cliente");
                elementoCliente.setAttribute("nombre",reservaActual.getCliente().getNombre());
                //introduzco el cliente dentro de la reserva
                elementoReserva.appendChild(elementoCliente);
                //introduzco la reserva dentro del elemento raíz
                elementoRaiz.appendChild(elementoReserva);
            }
            dom.appendChild(elementoRaiz);
            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "reservas.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                tr.transform(new DOMSource(dom),new StreamResult(new FileOutputStream(fileReservas)));
            } catch (TransformerException | IOException te) {
                System.out.println(te.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    @Override
    public Reserva busca(int numero) {
        List<Reserva> reservas = leeDatos();
        Optional<Reserva> optional = reservas.stream().filter(reserva -> reserva.getNumero() == numero).findFirst();
        return optional.orElse(null);
    }
}
