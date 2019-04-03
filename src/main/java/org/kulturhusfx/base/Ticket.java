package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;

import java.util.Date;

public class Ticket {

    private static int counter = 0;

    private int seatNumber;
    private String phoneNumber;
    private Date timeOfPurchase;

    public Ticket(String phoneNumber) {
        //sjekker om telefonnummer er et gyldig nummer før objektet opprettes
        Checker.checkValidPhone(phoneNumber);
        this.phoneNumber = phoneNumber;
        /*counter blir billettnummer og må sammenlignes med Event sin NumberOfSeats og gi feilmelding til
        kjøper om at det er utsolgt, noe som
        if(Event.Hall.getNumberofSeats >= counter){
            Alertbox: InvalidNumberOfSeatsException: Arrangementet er utsolgt*/
        this.seatNumber = counter++;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSeatNumber(){
        return seatNumber;
    }

}

