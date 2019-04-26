package org.kulturhusfx.model;


import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HallModel implements Serializable {

    private static final HallModel hallModel = new HallModel();

    private HappeningModel happeningModel = HappeningModel.getInstance();
    private List<Happening> happeningList = happeningModel.getHappeningList();

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
    public void deleteHappening(String hallName) {
        happeningList.removeIf(e -> e.getHall().getHallName().equals(hallName));
    }
}
