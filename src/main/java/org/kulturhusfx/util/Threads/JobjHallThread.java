package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.io.File;
import java.io.IOException;

public class JobjHallThread extends Task<Void> {

    private Runnable runme;
    ReadFileJobj readFileJobj = new ReadFileJobj();
    File file = AdminMainPageController.file;

    public JobjHallThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() throws Exception {
        try {
            Thread.sleep(3000);
            readFileJobj.readHallFromFile(file);
        }
        catch(Exception e){

        }
        return null;
    }

    @Override
    protected void succeeded(){
        runme.run();
    }
}