package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.exception.InvalidHallException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFileJobj extends ReadFile {
    @Override
    public void readHappeningFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Happening newHappening = (Happening) objectInputStream.readObject();
            //creates a new list of tickets when the happening has been initialized
            newHappening.createTicketModel();
            happeningList.add(newHappening);

            //checks if the hall in the happening already exists. If it doesn't adds it to the hallList
            boolean hallExists = false;
            for (Hall hall : hallList) {
                if (hall.getHallName().equals(newHappening.getHall().getHallName())) {
                    hallExists = true;
                    break;
                }
            }
            if (hallExists) {
                hallList.add(newHappening.getHall());

            }
        }
    }

    @Override
    public void readHallFromFile(String fileName) throws InvalidHallException, IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Hall newHall = (Hall) objectInputStream.readObject();
            if (!hallList.isEmpty()) {
                Checker.checkIfHallExists(newHall.getHallName(), hallList);
            }
            hallList.add(newHall);
        }
    }
}
