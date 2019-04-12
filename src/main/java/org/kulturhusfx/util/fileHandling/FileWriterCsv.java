package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Ticket;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriterCsv extends FileWriter {

    public FileWriterCsv(String fileName) throws IOException {
        super(fileName);
    }

    public static void saveHallToFile(Hall hall, String filePath){
        try{
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(hall.getHallName() + "," + hall.getHallType() +"," + hall.getNumberOfSeats());
            printWriter.close();

            System.out.println("Lagret til fil");
        }
        catch (Exception e){
            System.out.println("Ikke lagret til fil");
        }
    }

    public static void saveEventToFile(Event event, String filePath){

        try{
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(event.getContactPerson().getName() + "," + event.getContactPerson().getPhoneNumber()+ "," +
                    event.getContactPerson().getEmail() + "," + event.getContactPerson().getWebpage() + "," + event.getContactPerson().getFirm()+ "," +
                    event.getContactPerson().getOtherInformation() + "," + event.getName() + "," + event.getPerformers() + "," +
                    event.getSchedule() + "," + event.getHall().getHallName() + "," + event.getHall().getHallType() + "," +
                    event.getHall().getNumberOfSeats() + "," + event.getType() + "," + event.getDate() + "," +
                    event.getTime() + "," + event.getTicketPrice());
            printWriter.close();

            System.out.println("Lagret til fil");
        }
        catch (Exception e){
            System.out.println("Ikke lagret til fil");
        }

    }

    public static void saveTicketToFile(Ticket ticket, String filePath){
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(ticket.getPhoneNumber());
            printWriter.close();
            System.out.println("Lagret til fil");
        }
        catch (Exception e){
            System.out.println("Ikke lagret til fil");
        }

    }
}

