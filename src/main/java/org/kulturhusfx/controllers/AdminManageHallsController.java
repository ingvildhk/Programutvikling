package org.kulturhusfx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class AdminManageHallsController {

    @FXML
    private TableView <Hall> tableViewHalls;

    @FXML
    private TableColumn <Hall, String> nameColumn, typeOfHallColumn, numberOfSeatsColumn;

    private HallModel hallModel = HallModel.getInstance();
    private List<Hall> hallList = hallModel.getHallList();

    // Method to list the registered halls in tableView
    private ObservableList<Hall> getHalls(){
        ObservableList<Hall> halls = FXCollections.observableArrayList();
        for (Hall hall : hallList){
            halls.add(hall);
        }
        return halls;
    }

    public void deleteHallFromTableView(){
        ObservableList<Hall> selectedRows;

        // contains the selected rows
        selectedRows = tableViewHalls.getSelectionModel().getSelectedItems();
        for (Hall hall : selectedRows){
            hallList.remove(hall);
            hallModel.deleteEvent(hall.getHallName());
        }
        for(Hall hall : hallModel.getHallList()) {
            tableViewHalls.getItems().remove(hall);
        }
        tableViewHalls.setItems(getHalls());


    }

    // Methods to change and set eventdata i tableView with double-click
    public void editHallNameCellEvent (TableColumn.CellEditEvent edittedCell){
        Hall hallSelected = tableViewHalls.getSelectionModel().getSelectedItem();
        hallSelected.setHallName(edittedCell.getNewValue().toString());
    }

    public void editHallTypeCellEvent (TableColumn.CellEditEvent edittedCell){
        Hall hallSelected = tableViewHalls.getSelectionModel().getSelectedItem();
        hallSelected.setHallType(edittedCell.getNewValue().toString());
    }

    public void editNumberOfSeatsCellEvent (TableColumn.CellEditEvent edittedCell){
        Hall hallSelected = tableViewHalls.getSelectionModel().getSelectedItem();
        hallSelected.setNumberOfSeats(edittedCell.getNewValue().toString());
    }


    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        SceneUtils.launchScene(event, AdminManageHallsController.class, "adminMainPage.fxml");
    }

    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Hall, String>("hallName"));
        typeOfHallColumn.setCellValueFactory(new PropertyValueFactory<Hall, String>("hallType"));
        numberOfSeatsColumn.setCellValueFactory(new PropertyValueFactory<Hall, String>("numberOfSeats"));

        tableViewHalls.setItems(getHalls());

        // To change text fields, editable must be true
        tableViewHalls.setEditable(true);

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeOfHallColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numberOfSeatsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
