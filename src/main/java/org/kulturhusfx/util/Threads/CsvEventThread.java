package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;

import java.io.File;

public class CsvEventThread extends Task<Void> {

    private Runnable runme;
    ReadFileCsv readFileCsv = new ReadFileCsv();
    File file = AdminMainPageController.file;

    public CsvEventThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() throws Exception {
        try {
            Thread.sleep(3000);
            readFileCsv.readEventFromFile(file);
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