package org.kulturhusfx.model;

import org.kulturhusfx.base.Event;
import java.util.ArrayList;
import java.util.List;

public class EventModel {
    private List<Event> eventList;

    public EventModel(){
        eventList = new ArrayList<>();
    }

    public void createEvent(String webpage){

    }

    public void deleteEvent(String id) {
        eventList.removeIf(e -> e.getId().equals(id));
    }

    public void deleteHallEvents(String hallName){
        eventList.removeIf(e -> e.getLocation().getHallName().equals(hallName));
    }



}
