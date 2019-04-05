package org.kulturhusfx.model;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketModel {
    private List<Ticket> ticketList;

    public TicketModel() {
        ticketList = new ArrayList<>();
    }

    public void createTicket(String phoneNumber, Event event) {
        ticketList.add(new Ticket(phoneNumber, event));
    }
}
