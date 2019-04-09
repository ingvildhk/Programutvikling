package org.kulturhusfx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;

public class AdminManageEventsController {

    // Skal den ta inn eventOrder ehr
    @FXML
    private TableView<Event> tableViewEvents;

    @FXML
    private TableColumn<Event, String> nameColumn, typeColumn, performersColumn,
        hallColumn, timeColumn, dateColumn, programColumn, priceColumn, contactPersonColumn;

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageEventsController.class, "adminMainPage.fxml");
    }
    
    public void deleteEventBtn(ActionEvent event){

    }


}
