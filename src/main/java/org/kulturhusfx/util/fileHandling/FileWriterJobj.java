package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Ticket;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.spec.ECField;

public class FileWriterJobj extends FileWriter {

    public FileWriterJobj(String fileName) throws IOException {
        super(fileName);
    }

    public static void saveHallToFile(Hall hall, String filePath) throws IOException {

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(hall);
            System.out.println("Skrevet til fil");

        }
    }

    public static void saveEventToFile(Event event, String filePath) throws IOException{
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(event);
            System.out.println("Skrevet til fil");

        }
    }

    public static void saveTicketToFile(Ticket ticket, String filePath) throws IOException{
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(ticket);
            System.out.println("Skrevet til fil");

        }
    }
}
