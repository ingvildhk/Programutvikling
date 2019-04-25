package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.exception.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFileCsv extends ReadFile {

    @Override
    public void readEventFromFile(File file) throws IOException, InvalidPhoneException, InvalidEmailException ,
            InvalidNumberOfSeatsException, InvalidDateException, InvalidTimeException, InvalidTicketPriceException, InvalidInputException{
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                String [] eventDetails = line.split(",");
                if (eventDetails.length > 0){
                    //sjekker at alle felt er fylt ut, fyller de tre valgfrie stringene midlertidig med tekst hvis de er tomme
                    //slik at programmet fortsetter å kjøre
                    ArrayList<String> stringList = new ArrayList<>();
                    for (int i = 0; i < eventDetails.length; i++) {
                        stringList.add(eventDetails[i]);
                    }
                    if (stringList.get(3).isEmpty() | stringList.get(3) == null) {
                        stringList.set(3, "not avaiable");
                    }
                    if (stringList.get(4).isEmpty() | stringList.get(4) == null) {
                        stringList.set(4, "not avaiable");
                    }
                    if (stringList.get(5).isEmpty() | stringList.get(5) == null) {
                        stringList.set(5, "not avaiable");
                    }
                    for (int i = 0; i < stringList.size(); i++){
                        if (stringList.get(i) == null | stringList.get(i).trim().isEmpty()){
                            throw new InvalidInputException("");
                        }
                    }

                    //sjekker telefonnummer
                    for(int i = 1; i < eventDetails.length; i = i+16){
                        try {
                            int phone = Integer.parseInt(eventDetails[i]);
                            if (phone < 0) {
                                throw new InvalidPhoneException("");
                            }
                            if (eventDetails[i].length() != 8){
                                throw new InvalidPhoneException("");
                            }
                        }
                        catch (NumberFormatException e) {
                            throw new InvalidPhoneException("");
                        }
                    }

                    //sjekker epost
                    for(int i = 2; i < eventDetails.length; i = i+16){
                        String[] splitEmail = eventDetails[i].split("@");
                        if (splitEmail.length != 2) {
                            throw new InvalidEmailException("");
                        }
                    }

                    //sjekker antall plasser
                    for (int i = 11; i < eventDetails.length; i = i+16){
                        try {
                            int seat = Integer.parseInt(eventDetails[i]);
                            if (seat < 0) {
                                throw new InvalidNumberOfSeatsException("");
                               }
                        } catch (NumberFormatException e) {
                            throw new InvalidNumberOfSeatsException("");
                        }
                    }

                    //sjekker datoformat
                    for (int i = 13; i < eventDetails.length; i = i+16) {
                        String regex = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(eventDetails[i]);
                        boolean validDate = m.matches();
                        if (!validDate) {
                            throw new InvalidDateException("");
                        }
                    }

                    //sjekker tidformat
                    for (int i = 14; i < eventDetails.length; i = i+16) {
                        String[] splitStringTime = eventDetails[i].split(":");
                        if (splitStringTime.length != 2) {
                            throw new InvalidTimeException("");
                        }
                        try {
                            int hour = Integer.parseInt(splitStringTime[0]);
                            if (hour < 0 || hour > 23) {
                                throw new InvalidTimeException("");
                            }
                            int minute = Integer.parseInt(splitStringTime[1]);
                            if (minute < 0 || minute > 59) {
                                throw new InvalidTimeException("");
                            }
                        }
                        catch (NumberFormatException e){
                            throw new InvalidTimeException("");
                        }
                    }

                    //sjekker billettpris
                    for (int i = 15; i < eventDetails.length; i = i+16) {
                        try {
                            double ticketPrice = Double.parseDouble(eventDetails[i]);
                            if (ticketPrice < 0) {
                               throw new InvalidTicketPriceException("");
                            }
                        }
                        catch (NumberFormatException e) {
                            throw new InvalidTicketPriceException("");
                        }
                    }

                    if (hallList.isEmpty()){
                        eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                        eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                new Hall(eventDetails[9], eventDetails[10], eventDetails[11]), eventDetails[12], eventDetails[13],
                                eventDetails[14], eventDetails[15]);
                    }

                    else {
                        for (Hall hall : hallList) {
                            //Hvis salen som legges inn finnes fra før av så opprettes ikke salen på nytt
                            if (hall.getHallName().equals(eventDetails[9])){
                                int hallIndex = hallModel.getHallIndex(eventDetails[9]);
                                Hall aHall = hallList.get(hallIndex);
                                eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                                eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                        aHall, eventDetails[9], eventDetails[13], eventDetails[14], eventDetails[15]);
                            }
                            else {
                                eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                                eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                        new Hall(eventDetails[9], eventDetails[10], eventDetails[11]), eventDetails[12], eventDetails[13],
                                        eventDetails[14], eventDetails[15]);
                            }
                        }
                    }
                }
            }
        }
        finally {
            try{
                bufferedReader.close();
            }
            catch (IOException ie){
                throw new IOException();
            }
        }
    }

    @Override
    public void readHallFromFile(File file) throws InvalidInputException, InvalidHallException, IOException{
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] hallDetails = line.split(",");
                for (int i = 0; i < hallDetails.length; i++){
                    if(hallDetails[i] == null | hallDetails[i].trim().isEmpty()){
                        throw new InvalidInputException("");
                    }
                }
                //Only checks the name of halls
                for (int i = 0; i < hallDetails.length; i=i+3){
                    //Makes sure there is a object in the hallList available for comparison
                    if (!hallList.isEmpty()){
                        for (Hall hall : hallList) {
                            if (hallDetails[i].equals(hall.getHallName())) {
                                throw new InvalidHallException("");
                            }
                        }
                    }
                }
                //Only checks the number of seats in halls
                for (int i = 2; i < hallDetails.length; i=i+3){
                    try {
                        int seat = Integer.parseInt(hallDetails[i]);
                        if (seat < 0) {
                            throw new InvalidNumberOfSeatsException("");                   }
                    }
                    catch (NumberFormatException e) {
                        throw new InvalidNumberOfSeatsException("");
                    }
                }
                hallModel.createHall(hallDetails[0], hallDetails[1], hallDetails[2]);
            }
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException ie) {
                throw new IOException();
            }
        }
    }
}

