package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.exception.InvalidHallException;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

public class CsvHallThread extends Task<Void> {

    private Runnable runme;
    ReadFileCsv readFileCsv = new ReadFileCsv();
    File file = AdminMainPageController.file;

    private Boolean hallExists = false;

    private void setHallExists(Boolean b){
        hallExists = b;
    }

    public CsvHallThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call(){
        try {
            Thread.sleep(3000);
            readFileCsv.readHallFromFile(file);
        }
        catch (InvalidHallException ihe){
            setHallExists(true);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void succeeded(){
        if (hallExists == true){
            FileExceptionHandler.generateExceptionmsg(new RuntimeException("ooh lookie sal finnes"));
        }
        runme.run();
    }
}