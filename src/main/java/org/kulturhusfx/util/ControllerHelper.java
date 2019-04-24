package org.kulturhusfx.util;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ControllerHelper {

    public static String changeInformation(TextField textField, Label label){
        String s;
        if (textField.getText() == null || textField.getText().isEmpty()){
            s = label.getText();
        }
        else{
            s = textField.getText();
        }
        return s;
    }

    //Updates the choicebox with the hallnames
    public static void updateRoomList(ChoiceBox box, HallModel hallModel){
        for (Hall hall : hallModel.getHallList()) {
            String hallName = hall.getHallName();
            //makes sure that only unique hallnames are added to the choicebox
            if(!box.getItems().contains(hallName)){
                box.getItems().add(hallName);
            }
        }
    }

    //General method for adding types of events to the choicebox
    public static void addEventType(ChoiceBox box){
        box.getItems().addAll("Konsert", "Teater", "Konferanse", "Forestilling", "Annet");
    }

    // Method to get date now
    public static final LocalDate getLocalDate(){
        String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }
}
