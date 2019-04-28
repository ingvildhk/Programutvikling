package org.kulturhusfx.util.Threads;

import javafx.concurrent.Task;
import org.kulturhusfx.controllers.AdminMainPageController;
import org.kulturhusfx.util.FileExceptionHandler;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.*;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;

import java.io.File;
import java.io.IOException;

public class CsvEventThread extends Task<Void> {

    private Runnable runme;
    private ReadFileCsv readFileCsv = new ReadFileCsv();
    private String fileName = AdminMainPageController.fileName;
    private SceneUtils sceneUtils = SceneUtils.getInstance();
    //for some reason succeeded runs twice? This counter makes sure that only one alert pops up
    private int counter = 0;

    private Boolean input = false;
    private Boolean phone = false;
    private Boolean email = false;
    private Boolean seats = false;
    private Boolean date = false;
    private Boolean time = false;
    private Boolean ticket = false;
    private Boolean ioeException = false;
    private Boolean interruptedException = false;

    public void setInput(Boolean input) {
        this.input = input;
    }

    public void setPhone(Boolean phone) {
        this.phone = phone;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public void setSeats(Boolean seats) {
        this.seats = seats;
    }

    public void setDate(Boolean date) {
        this.date = date;
    }

    public void setTime(Boolean time) {
        this.time = time;
    }

    public void setTicket(Boolean ticket) {
        this.ticket = ticket;
    }

    public void setIoeException(Boolean ioeException) {
        this.ioeException = ioeException;
    }

    public void setInterruptedException(Boolean interruptedException) {
        this.interruptedException = interruptedException;
    }

    public CsvEventThread(Runnable runme) {
        this.runme = runme;
    }

    @Override
    protected Void call() {
        try {
            Thread.sleep(3000);
            readFileCsv.readEventFromFile(fileName);
        }
        catch (InvalidTicketPriceException e){
            setTicket(true);
        }
        catch (InvalidTimeException e){
            setTime(true);
        }
        catch (InvalidDateException e){
            setDate(true);
        }
        catch (InvalidNumberOfSeatsException e){
            setSeats(true);
        }
        catch (InvalidPhoneException ie){
            setPhone(true);
        }
        catch (InvalidEmailException e){
            setEmail(true);
        }
        catch (InvalidInputException e){
            setInput(true);
        }
        catch (IOException e){
            setIoeException(true);
        }
        catch (InterruptedException e) {
            setInterruptedException(true);
        }
        return null;
    }

    @Override
    protected void succeeded(){
        if (input){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidInputException("Alle data må fylles ut. Husk å dele variabler med ';'"));
        }
        if (phone){
            counter ++;
            InvalidInputHandler.generateThreadAlert(new InvalidPhoneException("Telefonnummer må være et positivt heltall med 8 siffer"));
        }
        if (email){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidEmailException("Epost må inneholde '@"));
        }
        if (date){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidDateException("Datoformat feil. Må oppgis i dette formatet: 'yyyy-mm-dd'"));
        }
        if (time){
            counter ++;
            InvalidInputHandler.generateThreadAlert(new InvalidTimeException("Time og minutt må deles med ':'. Time må være" +
                    "mellom 0 og 23. Minutt må være mellom 0 og 59."));
        }
        if (ticket){
            counter++;
            InvalidInputHandler.generateThreadAlert(new InvalidTimeException("Billettpris må være et positivt heltall"));
        }
        if (ioeException | interruptedException){
            counter++;
            FileExceptionHandler.generateExceptionmsg(new IOException("Feil oppstod under lesing fra fil"));
        }
        else if (counter == 0){
            counter++;
            sceneUtils.generateConfirmationAlert("Bekreftelse på registrering", "Arrangement er opprettet fra fil");
        }
        runme.run();
    }
}