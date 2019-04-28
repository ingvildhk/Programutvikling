package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.exception.InvalidHallException;

import java.io.*;

public class ReadFileJobj extends ReadFile {
    @Override
    public void readEventFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            Happening newHappening = (Happening) objectInputStream.readObject();
            newHappening.createTicketModel();
            happeningList.add(newHappening);
            for (Hall hall : hallList){
                if (!newHappening.getHall().getHallName().equals(hall.getHallName())){
                    hallList.add(newHappening.getHall());
                }
            }
        }
    }

    @Override
    public void readHallFromFile(String fileName) throws InvalidHallException, IOException, ClassNotFoundException {
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
