package org.kulturhusfx.util;

import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//egen klasse med alle check-metodene, kan brukes flere steder om nødvendig
public class Checker {
    
    public static void checkValidNumberOfSeats(String numberOfSeats) {
        try {
            int seat = Integer.parseInt(numberOfSeats);
            if (seat < 0) {
                InvalidInputHandler.generateAlert(
                        new InvalidNumberOfSeatsException("Antall seter kan ikke være et negativt tall"));
            }
        } catch (NumberFormatException e) {
            InvalidInputHandler.generateAlert(
                    new InvalidNumberOfSeatsException("Antall seter må være et tall"));
        }
    }

    public static void checkValidPhone(String phoneNumber) {
        try {
            int phone = Integer.parseInt(phoneNumber);
            if (phone < 0) {
                InvalidInputHandler.generateAlert(new InvalidPhoneException("Telefonnummer kan ikke være et negativt tall"));
            }
        } catch (NumberFormatException e) {
            InvalidInputHandler.generateAlert(new InvalidPhoneException("Telefonnummer må være et tall"));
        }

        if (phoneNumber.length() != 8) {
            InvalidInputHandler.generateAlert(new InvalidPhoneException("Telefonnummer må være 8 siffer"));
        }
    }

    public static void checkValidEmail(String email) {
        String[] splitEmail = email.split("@");
        if (splitEmail.length != 2) {
            InvalidInputHandler.generateAlert(new InvalidEmailException("Epost må inneholde '@'"));
        }
    }

    // Metode for å sjekke om Dato-input er i riktig format
    public static void checkValidDate(String date) {
        // Sjekk at regexen er riktig, fjern kommentar når gjort
        //String regex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
        String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        boolean validDate = m.matches();
        if (!validDate) {
            InvalidInputHandler.generateAlert(new InvalidDateException
                    ("Datoformat feil. Skal være i dette formatet: 'yyyy-mm-dd' Event ble ikke oppdatert "));
        }
    }

    public static void checkValidTicketPrice(String price) {
        try {
            double ticketPrice = Double.parseDouble(price);
            if (ticketPrice < 0) {
                InvalidInputHandler.generateAlert(new InvalidTicketPriceException("Billettpris må være et positivt tall"));
            }
        } catch (NumberFormatException e) {
            InvalidInputHandler.generateAlert(new InvalidTicketPriceException("Billettpris må være et tall"));
        }
    }

    public static void checkValidTime(String time) {
        String[] splitStringTime = time.split(":");
        if (splitStringTime.length != 2) {
            InvalidInputHandler.generateAlert(new InvalidTimeException("Husk å dele time og minutt med : "));
        }
        int hour = Integer.parseInt(splitStringTime[0]);
        if (hour < 0 || hour > 23) {
            InvalidInputHandler.generateAlert(new InvalidTimeException("Time må være et tall mellom 0 og 23"));
        }

        int minute = Integer.parseInt(splitStringTime[1]);
        if (minute < 0 || minute > 59) {
            InvalidInputHandler.generateAlert(new InvalidTimeException("Minutter må være et tall mellom 0 og 59"));
        }
    }

    public static void checkIfFieldIsEmpty(String... args) {
        for (String str : args) {
            if (str == null || str.trim().length() == 0) {
                InvalidInputHandler.generateAlert(new InvalidInputException("Alle felt må fylles ut"));
            }
        }
    }

    public static void checkIfHallExcists(String hallName, List<Hall> liste){
        for (Hall hall : liste){
            if (hall.getHallName().equals(hallName)){
                InvalidInputHandler.generateAlert(new InvalidHallException("Salen finnes fra før av"));
            }
        }
    }
}
