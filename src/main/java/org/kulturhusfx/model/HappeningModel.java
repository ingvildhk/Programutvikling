package org.kulturhusfx.model;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HappeningModel implements Serializable {

    private static final HappeningModel instance = new HappeningModel();

    private HappeningModel(){
    }

    public static HappeningModel getInstance(){
        return instance;
    }

    private List<Happening> happeningList = new ArrayList<Happening>();

    public void createHappening(ContactPerson contactPerson, String name, String performers, String schedule,
                                Hall hall, String type, String date, String time, String ticketPrice) {
        happeningList.add(new Happening(contactPerson, name, performers, schedule, hall, type, date, time, ticketPrice));
    }

    public List<Happening> getHappeningList() {
        return happeningList;
    }

    //TODO brukes ikke
    public void deleteEvent(String id) {
        happeningList.removeIf(e -> e.getId().equals(id));
    }

    public void deleteHallHappenings(String hallName) {
        happeningList.removeIf(e -> e.getHall().getHallName().equals(hallName));
    }
}




    /*public HappeningModel() {
        if (happeningList == null || happeningList.isEmpty()) {
            happeningList = new ArrayList<>();
        }
    }*/