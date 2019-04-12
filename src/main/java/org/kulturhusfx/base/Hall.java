package org.kulturhusfx.base;

import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;


import java.util.List;

public class Hall {
    private String hallName;
    private String hallType;
    private String numberOfSeats;

    public Hall(String hallName, String hallType, String numberOfSeats) {
        Checker.checkValidNumberOfSeats(numberOfSeats);
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
        Checker.checkIfFieldIsEmpty(hallName);
        this.hallName = hallName;
    }

    public void setHallType(String hallType) {
        Checker.checkIfFieldIsEmpty(hallType);
        this.hallType = hallType;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        Checker.checkValidNumberOfSeats(numberOfSeats);
        this.numberOfSeats = numberOfSeats;
    }

    public void changeHallInformation(String hallName, String hallType, String numberOfSeats) {
        Checker.checkValidNumberOfSeats(numberOfSeats);
        setHallName(hallName);
        setHallType(hallType);
        setNumberOfSeats(numberOfSeats);
    }


    //TODO Trenger ikke toString i levering
    public String toString() {
        return (hallName + " " + hallType + " " + numberOfSeats);
    }
}
