package org.kulturhusfx.base;

import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.ControllerHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Ticket {

    //TODO Date må implementeres
    private String phoneNumber;
    //private String timeOfPurchase;
    private int antallBilletter;

    public Ticket(String phoneNumber) {
        Checker.checkValidPhone(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAntallBilletter(int antallBilletter){
        this.antallBilletter = antallBilletter;

    }

    public int getAntallBilletter(){
        return antallBilletter;
    }

    /*
    public void setTimeOfPurchase(){
        String dato = ControllerHelper.getLocalDate().toString();
        this.timeOfPurchase = dato;
    }

    public String getTimeOfPurchase(){
        return timeOfPurchase;
    }
    */

}

