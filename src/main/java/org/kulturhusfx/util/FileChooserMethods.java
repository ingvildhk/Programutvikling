package org.kulturhusfx.util;

import javafx.stage.FileChooser;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.util.fileHandling.SaveFileCsv;
import org.kulturhusfx.util.fileHandling.SaveFileJobj;

import java.io.*;

public class FileChooserMethods {

    private static final FileChooserMethods instance = new FileChooserMethods();

    private FileChooserMethods(){
    }

    public static FileChooserMethods getInstance(){
        return instance;
    }

    private FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
    FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");

    public void saveEventToFile(Event event) throws IOException {
        if(!fileChooser.getExtensionFilters().contains(jobjFilter)){
            fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        }
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                SaveFileJobj jobj = new SaveFileJobj();
                jobj.saveEventToFile(event, fileName);
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                SaveFileCsv csv = new SaveFileCsv();
                csv.saveEventToFile(event, fileName);
            }
            else{
                FileExceptionHandler.generateExceptionmsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }

    public void saveHallToFile(Hall hall) throws IOException {
        if(!fileChooser.getExtensionFilters().contains(jobjFilter)){
            fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        }
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                SaveFileJobj jobj = new SaveFileJobj();
                jobj.saveHallToFile(hall, fileName);
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                SaveFileCsv csv = new SaveFileCsv();
                csv.saveHallToFile(hall, fileName);
            }
            else{
                FileExceptionHandler.generateExceptionmsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }
}
