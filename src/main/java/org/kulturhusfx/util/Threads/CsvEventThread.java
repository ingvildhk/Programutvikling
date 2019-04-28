package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidInputException;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;

import java.io.IOException;

public class CsvEventThread extends Task<Void> {

    private Runnable runme;
    private ReadFileCsv readFileCsv = new ReadFileCsv();
    private String fileName = AdminMainPageController.fileName;
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    private IOException ioeException;
    private InterruptedException interruptedException;
    private InvalidInputException inputException;
    private Exception exception;

    public CsvEventThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(3000);
            readFileCsv.readHappeningFromFile(fileName);
        } catch (InvalidInputException e) {
            inputException = e;
        } catch (IOException e) {
            ioeException = e;
        } catch (InterruptedException e) {
            interruptedException = e;
        } catch (Exception e) {
            exception = e;
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void succeeded() {
        if (inputException != null) {
            InvalidInputHandler.generateThreadAlert(inputException);
        }
        else if (ioeException != null) {
            FileExceptionHandler.generateExceptionmsg(new IOException("Feil oppstod under lesing fra fil: " + ioeException.getMessage()));
        }
        else if (interruptedException != null) {
            FileExceptionHandler.generateExceptionmsg(new IOException("Feil oppstod under lesing fra fil: " + interruptedException.getMessage()));
        }
        else if (exception != null) {
            FileExceptionHandler.generateExceptionmsg(exception);
        }
        else {
            sceneUtils.generateConfirmationAlert("Bekreftelse på registrering", "Arrangement er opprettet fra fil");
        }
        runme.run();
    }
}