package org.kulturhusfx.util;

import javafx.stage.FileChooser;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.util.fileHandling.FileWriterCsv;
import org.kulturhusfx.util.fileHandling.FileWriterJobj;

import java.io.*;
import java.util.List;

public class FileChooserMethods {

    EventModel eventModel = EventModel.getInstance();
    List<Event> eventList = eventModel.getEventList();

    //general method for saving and reading from file possibly

    public static void saveEventToFile(Event event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                try (
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
                    objectOutputStream.writeObject(event);
                }
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveEventToFile(event, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }

    public static void saveHallToFile(Hall hall) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                try (
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
                    objectOutputStream.writeObject(hall);
                }
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveHallToFile(hall, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }

    public static void saveTicketToFile(Ticket ticket) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                try (
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
                    objectOutputStream.writeObject(ticket);
                }
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveTicketToFile(ticket, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }
}
