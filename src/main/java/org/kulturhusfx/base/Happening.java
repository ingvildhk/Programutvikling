package org.kulturhusfx.base;

import org.kulturhusfx.model.TicketModel;
import java.io.Serializable;

public class Happening implements Serializable {

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
    private int availableTickets;
    //TicketModel is transient so that Happening can be serialized
    private transient TicketModel ticketModel;

    //SVUID set to a random number
    private static final long serialVersionUID = -3210158538721287756L;

    public Happening(ContactPerson contactPerson, String name, String performers,
                     String schedule, Hall hall, String type, String date, String time, String ticketPrice) {
        this.contactPerson = contactPerson;
        this.name = name;
        this.performers = performers;
        this.type = type;
        this.schedule = schedule;
        this.hall = hall;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
        this.availableTickets = Integer.parseInt(hall.getNumberOfSeats());

        //Creates a new list of Tickets for every Happening that is created
        createTicketModel();
    }

    public void createTicketModel(){
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

    public void setContactPersonName(String name) {
        this.contactPerson.setContactName(name);
    }

    public void setContactPersonPhone(String phone) {
        this.contactPerson.setPhoneNumber(phone);
    }

    public void setContactPersonEmail(String email) {
        this.contactPerson.setEmail(email);
    }

    public void setContactPersonWebpage(String webpage){
        this.contactPerson.setWebpage(webpage);
    }

    public void setContactPersonFirm(String firm){
        this.contactPerson.setFirm(firm);
    }

    public void setContactPersonOther(String otherInformation){
        this.contactPerson.setOtherInformation(otherInformation);
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAvailableTickets(int availableTickets){
        this.availableTickets = availableTickets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerformers(String performers) {
        this.performers = performers;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void changeHappeningInformation(ContactPerson contactPerson, String name,
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
}
