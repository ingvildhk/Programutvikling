package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.exception.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileCsv extends ReadFile {

    @Override
    public void readHappeningFromFile(String fileName) throws IOException, InvalidInputException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(fileName));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] happeningDetails = line.split(";");
                if (happeningDetails.length > 0) {
                    ArrayList<String> stringList = new ArrayList<>();
                    for (int i = 0; i < happeningDetails.length; i++) {
                        stringList.add(happeningDetails[i]);
                    }
                    //fills three variables with temporary text if they are empty - as they are not required to fill out
                    if (stringList.get(3).isEmpty() | stringList.get(3) == null) {
                        stringList.set(3, "not avaiable");
                    }
                    if (stringList.get(4).isEmpty() | stringList.get(4) == null) {
                        stringList.set(4, "not avaiable");
                    }
                    if (stringList.get(5).isEmpty() | stringList.get(5) == null) {
                        stringList.set(5, "not avaiable");
                    }
                    for (int i = 0; i < stringList.size(); i++) {
                        if (stringList.get(i) == null | stringList.get(i).trim().isEmpty()) {
                            throw new InvalidInputException("All informasjon må fylles ut");
                        }
                    }

                    Checker.checkValidPhone(happeningDetails[1]);

                    Checker.checkValidEmail(happeningDetails[2]);

                    Checker.checkValidNumberOfSeats(happeningDetails[11]);

                    Checker.checkValidDate(happeningDetails[13]);

                    Checker.checkValidTime(happeningDetails[14]);

                    Checker.checkValidTicketPrice(happeningDetails[15]);


                    //checks if the hall in the happening already exists. If it doesn't, creates it as a
                    //hall object and adds it to the hallList
                    boolean hallExists = false;
                    for (Hall hall : hallList) {
                        if (hall.getHallName().equals(happeningDetails[9])) {
                            hallExists = true;
                            break;
                        }
                    }

                    if (hallExists) {
                        int hallIndex = hallModel.getHallIndex(happeningDetails[9]);
                        Hall aHall = hallList.get(hallIndex);
                        happeningModel.createHappening(new ContactPerson(happeningDetails[0], happeningDetails[1], happeningDetails[2], happeningDetails[3],
                                        happeningDetails[4], happeningDetails[5]), happeningDetails[6], happeningDetails[7], happeningDetails[8],
                                aHall, happeningDetails[9], happeningDetails[13], happeningDetails[14], happeningDetails[15]);
                    } else {
                        Hall aHall = new Hall(happeningDetails[9], happeningDetails[10], happeningDetails[11]);
                        happeningModel.createHappening(new ContactPerson(happeningDetails[0], happeningDetails[1], happeningDetails[2], happeningDetails[3],
                                        happeningDetails[4], happeningDetails[5]), happeningDetails[6], happeningDetails[7], happeningDetails[8],
                                aHall, happeningDetails[12], happeningDetails[13],
                                happeningDetails[14], happeningDetails[15]);
                        hallList.add(aHall);
                    }
                }
            }

        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ie) {
                throw new IOException();
            }
        }
    }

    @Override
    public void readHallFromFile(String fileName) throws InvalidInputException, InvalidHallException, IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(fileName));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] hallDetails = line.split(";");
                for (int i = 0; i < hallDetails.length; i++) {
                    if (hallDetails[i] == null | hallDetails[i].trim().isEmpty()) {
                        throw new InvalidInputException("All informasjon må fylles ut");
                    }
                }
                //Only checks the name of halls
                if (!hallList.isEmpty()) {
                    Checker.checkIfHallExists(hallDetails[0], hallList);
                }
                Checker.checkValidNumberOfSeats(hallDetails[2]);
                hallModel.createHall(hallDetails[0], hallDetails[1], hallDetails[2]);
            }
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ie) {
                throw new IOException();
            }
        }
    }
}

