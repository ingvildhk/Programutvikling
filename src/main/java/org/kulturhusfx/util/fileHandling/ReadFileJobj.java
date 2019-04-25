package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.exception.InvalidHallException;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;

import java.io.*;

public class ReadFileJobj extends ReadFile {
    @Override
    public void readEventFromFile(File file) throws IOException, ClassNotFoundException {
        String fileName = file.getName();
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            Event newEvent = (Event) objectInputStream.readObject();
            newEvent.createTicketModel();
            eventList.add(newEvent);
        }
    }

    @Override
    public void readHallFromFile(File file) throws InvalidHallException, IOException, ClassNotFoundException {
        String fileName = file.getName();
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            Hall newHall = (Hall) objectInputStream.readObject();
            if (!hallList.isEmpty()){
                for(Hall hall : hallList){
                    if (hall.getHallName().equals(newHall.getHallName())){
                        throw new InvalidHallException("");
                    }
                }
            }
            hallList.add(newHall);
        }
    }
}
