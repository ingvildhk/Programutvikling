package org.kulturhusfx.model;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketModel {

    private List<Ticket> ticketList = new ArrayList<>();;

    public TicketModel() {
    }

    public void createTicket(String phoneNumber) {
        ticketList.add(new Ticket(phoneNumber));
    }

    public List<Ticket> getTicketList(){
        return ticketList;
    }

    public int getSeatNumber(){
        return ticketList.size();
    }
}
