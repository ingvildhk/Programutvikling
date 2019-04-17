package org.kulturhusfx.base;

import org.kulturhusfx.model.TicketModel;
import org.kulturhusfx.util.Checker;

public class Event {

    private static int counter = 0;

    private ContactPerson contactPerson;
    private String id;
    private String name;
    private String type;
    private String performers;
    private String schedule;
    private Hall hall;
    private String date;
    private String time;
    private String ticketPrice;
    private TicketModel ticketModel;
    private int availableTickets;

    // TODO ??
    //Kjøres etter innlest fil for å kontrollere at id-ene holdes unike
    public static void setMinId(int id) {
        if (id > counter) {
            counter = id;
        }
    }

    public Event(ContactPerson contactPerson, String name, String performers,
                 String schedule, Hall hall, String type, String date, String time, String ticketPrice) {
        Checker.checkValidDate(date);
        Checker.checkValidTime(time);
        Checker.checkValidTicketPrice(ticketPrice);
        this.contactPerson = contactPerson;
        this.name = name;
        this.performers = performers;
        this.type = type;
        this.schedule = schedule;
        this.hall = hall;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.id = "" + counter++;
        this.availableTickets = Integer.parseInt(hall.getNumberOfSeats());


        //Må opprette ny liste med billetter for hvert arrangement så kanskje noe som
        ticketModel = new TicketModel();
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public String getName() {
        return name;
    }

    public String getPerformers() {
        return performers;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getType() {
        return type;
    }

    public Hall getHall() {
        return hall;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public String getId() {
        return id;
    }

    public int getAvailableTickets() { return availableTickets; }

    public TicketModel getTicketModel() {
        return ticketModel;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAvailableTickets(int availableTickets){
        this.availableTickets = availableTickets;
    }

    public void setName(String name) {
        Checker.checkIfFieldIsEmpty(name);
        this.name = name;
    }

    public void setPerformers(String performers) {
        Checker.checkIfFieldIsEmpty(name);
        this.performers = performers;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSchedule(String schedule) {
        Checker.checkIfFieldIsEmpty(name);
        this.schedule = schedule;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setDate(String date) {
        Checker.checkValidDate(date);
        this.date = date;
    }

    public void setTime(String time) {
        Checker.checkValidTime(time);
        this.time = time;
    }

    public void setTicketPrice(String ticketPrice) {
        Checker.checkValidTicketPrice(ticketPrice);
        this.ticketPrice = ticketPrice;
    }

    public void changeEventInformation(ContactPerson contactPerson, String name,
                                       String performers, String type, String schedule, Hall hall,
                                       String date, String time, String ticketPrice) {
        setContactPerson(contactPerson);
        setName(name);
        setPerformers(performers);
        setType(type);
        setSchedule(schedule);
        setHall(hall);
        setDate(date);
        setTime(time);
        setTicketPrice(ticketPrice);
    }

    public String toString() {
        String s = contactPerson.toString() + " " + name + " " + performers + type + " " + schedule + " " +
                hall.toString() + " " + date + " " + time + " " + ticketPrice;
        return s;
    }

    // TODO brukes denne?
    public int soldTickets() {
        return 0;
    }
}
