package org.kulturhusfx.util;

import javafx.stage.FileChooser;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.util.fileHandling.SaveFileCsv;
import org.kulturhusfx.util.fileHandling.SaveFileJobj;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;

public class FileChooserMethods {

    private static final FileChooserMethods instance = new FileChooserMethods();

    private FileChooserMethods() {
    }

    public static FileChooserMethods getInstance() {
        return instance;
    }

    private FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
    FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");

    public void saveHappeningToFile(Happening happening) throws IOException {
        if (!fileChooser.getExtensionFilters().contains(jobjFilter)) {
            fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        }
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if (fileChooser.getSelectedExtensionFilter() == jobjFilter) {
                SaveFileJobj jobj = new SaveFileJobj();
                jobj.saveHappeningToFile(happening, fileName);
            } else if (fileChooser.getSelectedExtensionFilter() == csvFilter) {
                SaveFileCsv csv = new SaveFileCsv();
                csv.saveHappeningToFile(happening, fileName);
            } else {
                FileExceptionHandler.generateExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }

    public void saveHallToFile(Hall hall) throws IOException {
        if (!fileChooser.getExtensionFilters().contains(jobjFilter)) {
            fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        }
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if (fileChooser.getSelectedExtensionFilter() == jobjFilter) {
                SaveFileJobj jobj = new SaveFileJobj();
                jobj.saveHallToFile(hall, fileName);
            } else if (fileChooser.getSelectedExtensionFilter() == csvFilter) {
                SaveFileCsv csv = new SaveFileCsv();
                csv.saveHallToFile(hall, fileName);
            } else {
                FileExceptionHandler.generateExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }
}
