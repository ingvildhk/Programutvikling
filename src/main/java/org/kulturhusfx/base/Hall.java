package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;

public class Hall {
    private String hallName;
    private String hallType;
    private String numberOfSeats;

    public Hall(String hallName, String hallType, String numberOfSeats) {
        //sjekker om seteantall er et tall i konstruktøren, slik at det ikke opprettes objekter med feil
        this.hallName = hallName;
        this.hallType = hallType;
        Checker.checkValidNumberOfSeats(numberOfSeats);
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

    private void setHallName(String hallName) {
        this.hallName = hallName;
    }

    private void setHallType(String hallType) {
        this.hallType = hallType;
    }

    private void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void changeHallInformation(String hallName, String hallType, String numberOfSeats){
        Checker.checkValidNumberOfSeats(numberOfSeats);
        setHallName(hallName);
        setHallType(hallType);
        setNumberOfSeats(numberOfSeats);
    }

    public String toString(){
        return (hallName + " " + hallType + " " + numberOfSeats);
    }
}
