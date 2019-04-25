package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidHallException;
import org.kulturhusfx.util.exception.InvalidInputException;
import org.kulturhusfx.util.exception.InvalidNumberOfSeatsException;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;

import java.io.File;
import java.io.IOException;

public class CsvHallThread extends Task<Void> {

    private Runnable runme;
    ReadFileCsv readFileCsv = new ReadFileCsv();
    File file = AdminMainPageController.file;
    SceneUtils sceneUtils = SceneUtils.getInstance();

    private Boolean hallExists = false;
    private Boolean numberOfSeats = false;
    private Boolean input = false;
    private Boolean readException = false;
    private Boolean interruptedException = false;
    //for some reason succeeded runs twice? This counter makes sure that only one alert pops up
    private int counter = 0;

    private void setHallExists(Boolean b){
        hallExists = b;
    }
    private void setNumberOfSeats(Boolean b){
        numberOfSeats = b;
    }
    private void setInput(Boolean b){
        input = b;
    }
    private void setReadException(Boolean b){
        readException = b;
    }
    private void setInterruptedException(Boolean b){
        interruptedException = b;
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
        catch (InvalidNumberOfSeatsException ie){
            setNumberOfSeats(true);
        }
        catch (InvalidInputException iie){
            setInput(true);
        }
        catch (InterruptedException e) {
            setInterruptedException(true);
        }
        catch (IOException e) {
            setReadException(true);
        }
        return null;
    }

    @Override
    protected void succeeded(){
        if (hallExists){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidHallException("En av salene du forsøker å registrere finnes fra før"));
        }
        if (numberOfSeats){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidNumberOfSeatsException("Antall seter må være et positivt heltall"));
        }
        if (input){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidInputException("All informasjon må fylles ut"));
        }
        if (readException | interruptedException){
            counter++;
            FileExceptionHandler.generateExceptionmsg(new IOException("Feil oppstod under lesing fra fil"));
        }
        else if (counter == 0){
            counter++;
            sceneUtils.generateConfirmationAlert("Bekreftelse på registrering", "Sal er opprettet fra fil");
        }
        runme.run();
    }
}