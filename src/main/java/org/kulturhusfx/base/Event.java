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
    private Hall location;
    private String date;
    private String time;
    private String ticketPrice;
    private TicketModel ticketList;

    //Kjøres etter inlest fil for å kontrollere at id-ene holdes unike
    public static void setMinId(int id) {
        if (id > counter) {
            counter = id;
        }
    }

    public Event(ContactPerson contactPerson, String name, String performers,
                 String schedule, Hall location, String type, String date, String time, String ticketPrice) {
        Checker.checkValidDate(date);
        Checker.checkValidTime(time);
        Checker.checkValidTicketPrice(ticketPrice);
        this.contactPerson = contactPerson;
        this.name = name;
        this.performers = performers;
        this.type = type;
        this.schedule = schedule;
        this.location = location;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.id = "" + counter++;

        //Må opprette ny liste med billetter for hvert arrangement så kanskje noe som
        createNewTicketList();
    }

    private void createNewTicketList() {
        this.ticketList = new TicketModel();
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

    public Hall getLocation() {
        return location;
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

    public TicketModel getTicketList() {
        return ticketList;
    }

    private void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPerformers(String performers) {
        this.performers = performers;
    }

    private void setType(String type) {
        this.type = type;
    }

    private void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    private void setLocation(Hall location) {
        this.location = location;
    }

    private void setDate(String date) {
        this.date = date;
    }

    private void setTime(String time) {
        this.time = time;
    }

    private void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void changeEventInformation(ContactPerson contactPerson, String name,
                                       String performers, String type, String schedule, Hall location,
                                       String date, String time, String ticketPrice) {
        setContactPerson(contactPerson);
        setName(name);
        setPerformers(performers);
        setType(type);
        setSchedule(schedule);
        setLocation(location);
        setDate(date);
        setTime(time);
        setTicketPrice(ticketPrice);
    }

    public String toString() {
        String s = contactPerson.toString() + " " + name + " " + performers + type + " " + schedule + " " +
                location.toString() + " " + date + " " + time + " " + ticketPrice;
        return s;
    }

    public int soldTickets() {
        return 0;
    }
}
