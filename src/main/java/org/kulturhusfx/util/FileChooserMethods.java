package org.kulturhusfx.util;

import javafx.stage.FileChooser;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.util.fileHandling.FileWriterCsv;
import org.kulturhusfx.util.fileHandling.FileWriterJobj;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;

public class FileChooserMethods {

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
                FileWriterJobj jobj = new FileWriterJobj(fileName);
                jobj.saveEventToFile(event, fileName);
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
                FileWriterJobj jobj = new FileWriterJobj(fileName);
                jobj.saveHallToFile(hall, fileName);
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
                FileWriterJobj jobj = new FileWriterJobj(fileName);
                jobj.saveTicketToFile(ticket, fileName);
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
