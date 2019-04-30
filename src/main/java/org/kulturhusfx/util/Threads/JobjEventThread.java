package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.io.IOException;

public class JobjEventThread extends Task<Void> {

    private Runnable runme;
    ReadFileJobj readFileJobj = new ReadFileJobj();
    String fileName = AdminMainPageController.fileName;
    SceneUtils sceneUtils = SceneUtils.getInstance();

    private IOException ioException;
    private InterruptedException interruptedException;
    private ClassNotFoundException classException;
    private Exception exception;

    public JobjEventThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(3000);
            readFileJobj.readHappeningFromFile(fileName);
        }
        catch (InterruptedException e) {
            interruptedException = e;
        }
        catch (IOException e) {
            ioException = e;
        }
        catch (ClassNotFoundException e){
            classException = e;
        }
        catch (Exception e){
            exception = e;
        }
        return null;
    }

    @Override
    protected void succeeded(){
        if (ioException != null){
            FileExceptionHandler.generateExceptionMsg(new IOException("Feil oppstod under lesing fra fil: " + ioException.getMessage()));
        }
        else if (interruptedException != null){
            FileExceptionHandler.generateExceptionMsg(new InterruptedException("Feil oppstod under lesing fra fil: " + interruptedException.getMessage()));
        }
        else if (classException != null) {
            FileExceptionHandler.generateExceptionMsg(new ClassNotFoundException("Feil oppstod under lesing fra fil: " + classException.getMessage()));
        }
        else if (exception != null){
            FileExceptionHandler.generateExceptionMsg(new Exception(exception.getMessage()));
        }
        else {
            sceneUtils.generateConfirmationAlert("Bekreftelse på registrering", "Arrangement er opprettet fra fil");
        }
        runme.run();
    }
}
