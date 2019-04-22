package org.kulturhusfx.model;


import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidHallException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HallModel implements Serializable {

    private static final HallModel hallModel = new HallModel();

    private EventModel eventModel = EventModel.getInstance();
    private List<Event> eventList = eventModel.getEventList();


    private HallModel() {
    }

    public static HallModel getInstance() {
        return hallModel;
    }

    private List<Hall> hallList = new ArrayList<>();

    public List<Hall> getHallList() {
        return hallList;
    }

    public void createHall(String hallName, String hallType, String numberOfSeats) {
        //endret på metoden slik at den faktisk sjekker om hallname finnes fra før av
        for (Hall hall : hallList){
            if (hall.getHallName().equals(hallName)){
                InvalidInputHandler.generateAlert(new InvalidHallException("Salen finnes fra før av"));
            }
        }
        hallList.add((new Hall(hallName, hallType, numberOfSeats)));
    }

    public int getHallIndex(String roomName){
        for (Hall hall : hallList){
            if(hall.getHallName().equals(roomName)){
                return hallList.indexOf(hall);
            }
        }
        return 0;
    }

    // Sletter arrangementene som hører til salen som blir slettet
    public void deleteEvent(String hallName) {
        eventList.removeIf(e -> e.getHall().getHallName().equals(hallName));
    }
}
