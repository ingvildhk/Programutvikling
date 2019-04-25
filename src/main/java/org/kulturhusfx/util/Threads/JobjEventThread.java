package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.fileHandling.ReadFile;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.io.File;
import java.io.IOException;

public class JobjEventThread extends Task<Void> {

    private Runnable runme;
    ReadFileJobj readFileJobj = new ReadFileJobj();
    File file = AdminMainPageController.file;
    SceneUtils sceneUtils = SceneUtils.getInstance();
    private Boolean readException = false;
    private Boolean interruptedException = false;
    private Boolean classException = false;
    //for some reason succeeded runs twice? This counter makes sure that only one alert pops up
    private int counter = 0;

    private void setReadException(Boolean b){
        readException = b;
    }
    private void setInterruptedException(Boolean b){
        interruptedException = b;
    }
    private void setClassException(Boolean b){
        classException = b;
    }

    public JobjEventThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(3000);
            readFileJobj.readEventFromFile(file);
        }

        catch (InterruptedException e) {
        setInterruptedException(true);
    }
        catch (
    IOException e) {
        setReadException(true);
    }
        catch (ClassNotFoundException e){
        setClassException(true);
    }
        return null;
    }

    @Override
    protected void succeeded(){
        if (readException | interruptedException | classException){
            FileExceptionHandler.generateExceptionmsg(new IOException("Feil oppstod under lesing fra fil"));
        }
        else if (counter == 0){
            counter++;
            sceneUtils.generateConfirmationAlert("Bekreftelse på registrering", "Arrangement er opprettet fra fil");
        }
        runme.run();
    }
}
