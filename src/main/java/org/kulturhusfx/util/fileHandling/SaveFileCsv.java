package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFileCsv extends SaveFile {

    @Override
    public void saveHappeningToFile(Happening happening, String path) throws IOException {
        try (
                FileWriter fileWriter = new FileWriter(path, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                ) {
            printWriter.println(happening.getContactPerson().getContactName() + ";" + happening.getContactPerson().getPhoneNumber()+ ";" +
                    happening.getContactPerson().getEmail() + ";" + happening.getContactPerson().getWebpage() + ";" + happening.getContactPerson().getFirm()+ ";" +
                    happening.getContactPerson().getOtherInformation() + ";" + happening.getName() + ";" + happening.getPerformers() + ";" +
                    happening.getSchedule() + ";" + happening.getHall().getHallName() + ";" + happening.getHall().getHallType() + ";" +
                    happening.getHall().getNumberOfSeats() + ";" + happening.getType() + ";" + happening.getDate() + ";" +
                    happening.getTime() + ";" + happening.getTicketPrice());
        }
    }

    @Override
    public void saveHallToFile(Hall hall, String path) throws IOException{
        try (
                FileWriter fileWriter = new FileWriter(path, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                ) {

            printWriter.println(hall.getHallName() + ";" + hall.getHallType() + ";" + hall.getNumberOfSeats());
        }
    }
}
