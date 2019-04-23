package org.kulturhusfx.util;

import javafx.stage.FileChooser;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Ticket;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.exception.InvalidHallException;
import org.kulturhusfx.util.fileHandling.FileWriterCsv;
import org.kulturhusfx.util.fileHandling.FileWriterJobj;

import java.io.*;
import java.util.List;

public class FileChooserMethods {

    private static final FileChooserMethods instance = new FileChooserMethods();

    private FileChooserMethods(){
    }

    public static FileChooserMethods getInstance(){
        return instance;
    }

    EventModel eventModel = EventModel.getInstance();
    List<Event> eventList = eventModel.getEventList();
    HallModel hallModel = HallModel.getInstance();
    List<Hall> hallList = hallModel.getHallList();

    private FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
    FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");

    public void registerHallFromFile() throws  IOException, ClassNotFoundException{
        fileChooser.setTitle("Velg salfil");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (fileChooser.getSelectedExtensionFilter() == csvFilter){
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(selectedFile));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    String[] hallDetails = line.split(",");
                    if (hallDetails.length > 0) {
                        for (Hall hall : hallList) {
                            if (hall.getHallName() == hallDetails[0]) {
                                InvalidInputHandler.generateAlert(new InvalidHallException("En av salene du forsøker å" +
                                        " registrere finnes fra før av: " + hall.getHallName()));
                            }
                        }
                        hallModel.createHall(hallDetails[0], hallDetails[1], hallDetails[2]);
                    }
                }

            }
            catch(Exception e) {
                InvalidInputHandler.generateAlert(new RuntimeException("Feil i lesing fra fil"));
                e.printStackTrace();
            }
            finally {
                try{
                    bufferedReader.close();
                }
                catch (IOException ie){
                    InvalidInputHandler.generateAlert(new RuntimeException("Feil oppsto ved lukking av fil"));
                    ie.printStackTrace();
                }
            }

        }
        else if (fileChooser.getSelectedExtensionFilter() == jobjFilter){
            String fileName = selectedFile.getName();
            try (FileInputStream fileInputStream = new FileInputStream(fileName);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
            {
                Hall newHall = (Hall) objectInputStream.readObject();
                hallList.add(newHall);
            }
        }
    }

    public void registerEventFromFile() throws IOException, ClassNotFoundException{
        fileChooser.setTitle("Velg arrangementsfil");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (fileChooser.getSelectedExtensionFilter() == csvFilter){
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(selectedFile));
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    String [] eventDetails = line.split(",");
                    if (eventDetails.length > 0){
                        for (Hall hall : hallList) {
                            //Hvis salen som legges inn finnes fra før av så opprettes ikke salen på nytt
                            if (hall.getHallName() == eventDetails[9]){
                                int hallIndex = hallModel.getHallIndex(eventDetails[9]);
                                Hall aHall = hallList.get(hallIndex);
                                eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                                eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                        aHall, eventDetails[9], eventDetails[13], eventDetails[14], eventDetails[15]);
                            }
                            else {
                                eventModel.createEvent(new ContactPerson(eventDetails[0], eventDetails[1], eventDetails[2], eventDetails[3],
                                                eventDetails[4], eventDetails[5]), eventDetails[6], eventDetails[7], eventDetails[8],
                                        new Hall(eventDetails[9], eventDetails[10], eventDetails[11]), eventDetails[12], eventDetails[13],
                                        eventDetails[14], eventDetails[15]);
                            }
                        }
                    }
                }
            }
            catch(Exception e)
            {
                InvalidInputHandler.generateAlert(new RuntimeException("Feil i lesing fra fil"));
                e.printStackTrace();
            }
            finally {
                try{
                    bufferedReader.close();
                }
                catch (IOException ie){
                    InvalidInputHandler.generateAlert(new RuntimeException("Feil oppsto ved lukking av fil"));
                }
            }
        }
        else if (fileChooser.getSelectedExtensionFilter() == jobjFilter){
            String fileName = selectedFile.getName();
            try (FileInputStream fileInputStream = new FileInputStream(fileName);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
            {
                Event newEvent = (Event) objectInputStream.readObject();
                newEvent.createTicketModel();
                eventList.add(newEvent);
            }
        }
    }

    public void saveEventToFile(Event event) throws IOException {
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                try (
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
                    objectOutputStream.writeObject(event);
                }
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveEventToFile(event, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }

    public void saveHallToFile(Hall hall) throws IOException {
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                try (
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
                    objectOutputStream.writeObject(hall);
                }
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveHallToFile(hall, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }

    public void saveTicketToFile(Ticket ticket) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jobjFilter = new FileChooser.ExtensionFilter("jobj", "*.jobj");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
        fileChooser.getExtensionFilters().addAll(jobjFilter, csvFilter);
        File file = fileChooser.showSaveDialog(null);
        String fileName = file.getName();

        if (file != null) {
            if(fileChooser.getSelectedExtensionFilter() == jobjFilter){
                try (
                        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                ) {
                    objectOutputStream.writeObject(ticket);
                }
            }
            else if(fileChooser.getSelectedExtensionFilter() == csvFilter){
                FileWriterCsv csv = new FileWriterCsv(fileName);
                csv.saveTicketToFile(ticket, fileName);
            }
            else{
                FileExceptionHandler.generateIOExceptionMsg(new InvalidObjectException("Filtype må være jobj eller csv"));
            }
        }
    }
}
