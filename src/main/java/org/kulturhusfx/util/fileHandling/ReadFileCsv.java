package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidHallException;
import java.io.*;

public class ReadFileCsv extends ReadFile {

    @Override
    public void readEventFromFile(File file) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                String [] eventDetails = line.split(",");
                if (eventDetails.length > 0){
                    for (Hall hall : hallList) {
                        //Hvis salen som legges inn finnes fra før av så opprettes ikke salen på nytt
                        if (hall.getHallName() == eventDetails[9]){
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
        catch(IOException e) {
            throw new IOException();
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
    public void readHallFromFile(File file) throws InvalidHallException, IOException{
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] hallDetails = line.split(",");
                for (Hall hall : hallList) {
                    for (int i = 0; i < hallDetails.length; i++){
                        if (hallDetails[i] == hall.getHallName()) {
                            System.out.println("Heiheihei");
                            throw new InvalidHallException("");
                        }
                    }
                }
                hallModel.createHall(hallDetails[0], hallDetails[1], hallDetails[2]);
            }
        }
        catch (IOException e) {
            throw new IOException();
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

