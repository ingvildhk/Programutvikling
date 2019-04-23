package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;

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
    public void readHallFromFile(File file) throws IOException, ClassNotFoundException {
        String fileName = file.getName();
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            Hall newHall = (Hall) objectInputStream.readObject();
            hallList.add(newHall);
        }
    }
}
