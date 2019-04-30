package org.kulturhusfx.util;

import javafx.scene.control.ChoiceBox;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.exception.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public static void exceptionAlertWrapper(Runnable runnable) {
        try {
            runnable.run();
        } catch (InvalidInputException e) {
            InvalidInputHandler.generateAlert(e);
        }
    }

    public static void checkValidNumberOfSeats(String numberOfSeats) {
        try {
            int seat = Integer.parseInt(numberOfSeats);
            if (seat < 0) {
                throw new InvalidNumberOfSeatsException("Antall seter kan ikke være et negativt tall");
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberOfSeatsException("Antall seter må være et tall");
        }
    }

    public static void checkValidPhone(String phoneNumber) {
        try {
            long phone = Long.parseLong(phoneNumber);
            if (phoneNumber.length() != 8) {
                throw new InvalidPhoneException("Telefonnummer må være 8 siffer");
            }
            if (phone < 0) {
                throw new InvalidPhoneException("Telefonnummer kan ikke være et negativt tall");
            }
        } catch (NumberFormatException e) {
            throw new InvalidPhoneException("Telefonnummer må være et tall");
        }
    }

    public static void checkValidEmail(String email) {
        String[] splitEmail = email.split("@");
        if (splitEmail.length != 2) {
            throw new InvalidEmailException("Epost må inneholde '@'");
        }
    }

    public static void checkValidDate(String date) {
        String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(date);
        boolean validDate = m.matches();
        if (!validDate) {
            throw new InvalidDateException("Datoformat er feil. Formatet skal være: 'åååå-mm-dd' " +
                    "Arrangement ble ikke oppdatert ");
        }
    }

    public static void checkValidTicketPrice(String price) {
        try {
            double ticketPrice = Double.parseDouble(price);
            if (ticketPrice < 0) {
                throw new InvalidTicketPriceException("Billettpris må være et positivt tall");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTicketPriceException("Billettpris må være et tall");
        }
    }

    public static void checkValidTime(String time) {
        String[] splitStringTime = time.split(":");
        if (splitStringTime.length != 2) {
            throw new InvalidTimeException("Husk å dele time og minutt med : ");
        }

        try {
            int hour = Integer.parseInt(splitStringTime[0]);
            if (hour < 0 || hour > 23) {
                throw new InvalidTimeException("Time må være et tall mellom 0 og 23");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTimeException("Time må være et tall mellom 0 og 23");
        }

        try {
            int minute = Integer.parseInt(splitStringTime[1]);
            if (minute < 0 || minute > 59) {
                throw new InvalidTimeException("Minutter må være et tall mellom 0 og 59");
            }
        } catch (NumberFormatException e) {
            throw new InvalidTimeException("Minutter må være et tall mellom 0 og 59");
        }

    }

    public static void checkIfFieldIsEmpty(String... args) {
        for (String str : args) {
            if (str == null || str.trim().length() == 0) {
                throw new InvalidInputException("Alle felt må fylles ut");
            }
        }
    }

    public static void checkIfChoiceBoxIsEmpty(ChoiceBox box) {
        if (box.getValue() == null) {
            throw new InvalidInputException("Alle felt må fylles ut");
        }
    }

    public static void checkIfHallExists(String hallName, List<Hall> liste) {
        for (Hall hall : liste) {
            if (hall.getHallName().equals(hallName)) {
                throw new InvalidHallException("Salen finnes fra før av");
            }
        }
    }
}
