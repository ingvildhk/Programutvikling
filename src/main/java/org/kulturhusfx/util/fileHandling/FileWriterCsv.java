package org.kulturhusfx.util.fileHandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriterCsv extends FileWriter {

    public FileWriterCsv(String fileName) throws IOException {
        super(fileName);
    }

    public static void saveHallToFile(String hallName, String hallType, String numberOfSeats, String filePath){
        try{
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(hallName + "," + hallType +"," + numberOfSeats);
            printWriter.close();

            System.out.println("Lagret til fil");
        }
        catch (Exception e){
            System.out.println("Ikke lagret til fil");
        }
    }

    public static void saveEventToFile(String contactName, String phoneNumber, String email, String webpage,
                                       String firm, String otherInformation, String eventName, String performers,
                                       String schedule, String hallName, String hallType, String numberOfSeats, String eventType,
                                       String date, String time, String ticketPrice, String filePath){

    }

    public static void saveTicketToFile(String phoneNumber, String filePath){
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(phoneNumber);
            printWriter.close();
            System.out.println("Lagret til fil");
        }
        catch (Exception e){
            System.out.println("Ikke lagret til fil");
        }

    }
}
