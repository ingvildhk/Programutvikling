package org.kulturhusfx.util;

import org.kulturhusfx.util.exception.InvalidDateException;
import org.kulturhusfx.util.exception.InvalidEmailException;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;
import org.kulturhusfx.util.exception.InvalidPhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//egen klasse med alle check-metodene, kan brukes flere steder om nødvendig
public class Checker {

    //checkValidTime(date) - metode må opprettes
    //checkValidTicketPrice(ticketPrice) - metode må opprettes
    public static void checkValidNumberOfSeats(String numberOfSeats) {
        try {
            int seat = Integer.parseInt(numberOfSeats);
            if(seat < 0) {
                InvalidInputHandler.generateAlert(
                        new InvalidNumberOfSeatsException("Antall seter kan ikke være et negativt tall"));
            }
        }
        catch(NumberFormatException e){
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
        String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        boolean validDate = m.matches();
        if (!validDate) {
            InvalidInputHandler.generateAlert(new InvalidDateException
                    ("Datoformat feil. Event ble ikke oppdatert "));
        }
    }
}
