package org.kulturhusfx.model;

import org.kulturhusfx.base.Ticket;
import java.util.ArrayList;
import java.util.List;

public class TicketModel{

    private List<Ticket> ticketList = new ArrayList<>();

    public void createTicket(String phoneNumber) {
        ticketList.add(new Ticket(phoneNumber));
    }

    public List<Ticket> getTicketList(){
        return ticketList;
    }


    //TODO vi har ikke satt opp at man skal få setenummer når man bestiller
    //Må enten implementere det, eller fjerne denne metoden

    public int getSeatNumber(){
        return ticketList.size();
    }
}
