package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.SceneUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFileCsv extends SaveFile {

    @Override
    public void saveEventToFile(Event event, String path) throws IOException {
        try (
                FileWriter fileWriter = new FileWriter(path, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter);
                ) {

            printWriter.println(event.getContactPerson().getContactName() + ";" + event.getContactPerson().getPhoneNumber()+ ";" +
                    event.getContactPerson().getEmail() + ";" + event.getContactPerson().getWebpage() + ";" + event.getContactPerson().getFirm()+ ";" +
                    event.getContactPerson().getOtherInformation() + ";" + event.getName() + ";" + event.getPerformers() + ";" +
                    event.getSchedule() + ";" + event.getHall().getHallName() + ";" + event.getHall().getHallType() + ";" +
                    event.getHall().getNumberOfSeats() + ";" + event.getType() + ";" + event.getDate() + ";" +
                    event.getTime() + ";" + event.getTicketPrice());
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
