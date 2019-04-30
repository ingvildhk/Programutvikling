package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidHallException;
import org.kulturhusfx.util.exception.InvalidInputException;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;

import java.io.IOException;

public class CsvHallThread extends Task<Void> {

    private Runnable runme;
    ReadFileCsv readFileCsv = new ReadFileCsv();
    String fileName = AdminMainPageController.fileName;
    SceneUtils sceneUtils = SceneUtils.getInstance();

    private boolean hallExist;
    private IOException ioException;
    private InvalidInputException inputException;
    private Exception exception;

    public CsvHallThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(3000);
            readFileCsv.readHallFromFile(fileName);
        }
        catch (InvalidInputException e) {
            inputException = e;
        }
        catch (InvalidHallException e){
            hallExist = true;
        }
        catch (IOException e) {
            ioException = e;
        }
        catch (Exception e){
            exception = e;
        }
        return null;
    }

    @Override
    protected void succeeded() {
        if (hallExist){
            InvalidInputHandler.generateThreadAlert(new InvalidHallException("En av salene du forsøker å registrere finnes fra før"));
        }
        if (inputException != null) {
            InvalidInputHandler.generateThreadAlert(inputException);
        }
        else if (ioException != null) {
            FileExceptionHandler.generateExceptionMsg(new IOException("Feil oppsto under lesing fra fil: " + ioException.getMessage()));
        }
        else if (exception != null){
            FileExceptionHandler.generateExceptionMsg(new Exception(exception.getMessage()));
        }
        else {
            sceneUtils.generateConfirmationAlert("Bekreftelse på registrering", "Sal er opprettet fra fil");
        }
        runme.run();
    }
}