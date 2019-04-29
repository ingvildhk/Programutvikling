package org.kulturhusfx.base;

import java.io.Serializable;

public class Hall implements Serializable {
    private String hallName;
    private String hallType;
    private String numberOfSeats;

    public Hall(String hallName, String hallType, String numberOfSeats) {
        this.hallName = hallName;
        this.hallType = hallType;
        this.numberOfSeats = numberOfSeats;
    }

    public String getHallName() {
        return hallName;
    }

    public String getHallType() {
        return hallType;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void changeHallInformation(String hallName, String hallType, String numberOfSeats) {
        setHallName(hallName);
        setHallType(hallType);
        setNumberOfSeats(numberOfSeats);
    }

    public String toString(){
        return (hallName + " " + hallType + " " + numberOfSeats);
    }
}
