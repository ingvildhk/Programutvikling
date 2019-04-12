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

    public static void saveEventToFile(){

    }

    public static void saveTicketToFile(){

    }
}
