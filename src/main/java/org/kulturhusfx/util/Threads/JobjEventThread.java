package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.fileHandling.ReadFile;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.io.File;

public class JobjEventThread extends Task<Void> {

    private Runnable runme;
    ReadFileJobj readFileJobj = new ReadFileJobj();
    File file = AdminMainPageController.file;

    public JobjEventThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() throws Exception {
        try {
            Thread.sleep(3000);
            readFileJobj.readEventFromFile(file);
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
