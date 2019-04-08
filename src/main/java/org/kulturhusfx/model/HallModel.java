package org.kulturhusfx.model;


import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidHallException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallModel {

    private static final HallModel hallModel = new HallModel();

    private HallModel() {
    }

    public static HallModel getInstance() {
        return hallModel;
    }

    private List<Hall> hallList = new ArrayList<Hall>();

    public List getHallList() {
        return hallList;
    }

    public void createHall(String hallName, String hallType, String numberOfSeats) {
        if (hallList.contains(hallName)) {
            InvalidInputHandler.generateAlert(new InvalidHallException("Salen finnes fra før av"));
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

    /* Changing from map to arraylist, and design pattern to singleton

    public HallModel() {
        if (hallMap == null || hallMap.isEmpty()) {
            hallMap = new HashMap<>();
        }
    }

    public static Map<String, Hall> hallMap;

    public void createHall(String hallName, String hallType, String numberOfSeats) {
        if (hallMap.containsKey(hallName)) {
            InvalidInputHandler.generateAlert(new InvalidHallException("Salen finnes fra før av"));
        }
        hallMap.put(hallName, new Hall(hallName, hallType, numberOfSeats));
    }

    public Map getHallMap() {
        return hallMap;
    }*/

    public void deleteEvent(String hallName) {

        hallList.removeIf(e -> e.getHallName().equals(hallName));
    }
}
