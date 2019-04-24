package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.io.File;

public class CsvHallThread extends Task<Void> {

    private Runnable runme;
    ReadFileCsv readFileCsv = new ReadFileCsv();
    File file = AdminMainPageController.file;

    public CsvHallThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() throws Exception {
        try {
            Thread.sleep(3000);
            readFileCsv.readHallFromFile(file);
        }
        catch (Exception e){
        }
        return null;
    }

    @Override
    protected void succeeded(){
        runme.run();
    }
}