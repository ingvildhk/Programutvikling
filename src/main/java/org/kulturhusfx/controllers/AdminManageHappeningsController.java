package org.kulturhusfx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.Checker;
import org.kulturhusfx.util.InvalidInputHandler;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.exception.InvalidInputException;

import java.io.IOException;
import java.util.List;

public class AdminManageHappeningsController {

    @FXML
    private TableView<Happening> tableViewHappenings;

    @FXML
    private TableColumn<Happening, String> nameColumn, typeColumn, performersColumn,
            timeColumn, dateColumn, programColumn, priceColumn, contactNameCol,
            contactFirmCol, contactWebsiteCol, contactPhoneCol, contactEmailCol, contactOtherCol;

    @FXML
    private  TableColumn<Happening, Hall> hallColumn;

    private HallModel hallModel = HallModel.getInstance();
    private HappeningModel happeningModel = HappeningModel.getInstance();
    private List<Happening> happeningList = happeningModel.getHappeningList();
    private List<Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public void initialize() {
        setColumnValues();
        setEditableColumns();
    }

    private void setColumnValues(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("type"));
        performersColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("performers"));
        hallColumn.setCellValueFactory(new PropertyValueFactory<>("hall"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("time"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("date"));
        programColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("schedule"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("ticketPrice"));

        contactNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().
                getContactName()));
        contactPhoneCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().
                getPhoneNumber()));
        contactEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().
                getEmail()));
        contactFirmCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().
                getFirm()));
        contactWebsiteCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().
                getContactPerson().getWebpage()));
        contactOtherCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactPerson().
                getOtherInformation()));

        tableViewHappenings.setItems(getHappenings());
    }

    // Method to create ObservableList of happenings ArrayList
    private ObservableList<Happening> getHappenings(){
        ObservableList<Happening> happenings = FXCollections.observableArrayList();
        for (Happening happening : happeningList){
            happenings.addAll(happening);
        }
        return happenings;
    }

    private void setEditableColumns() {
        // To change text fields, editable must be true
        tableViewHappenings.setEditable(true);

        // To make columns editable
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Konsert", "Teater", "Konferanse",
                "Forestilling", "Annet"));
        performersColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        programColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hallColumn.setCellFactory(ComboBoxTableCell.forTableColumn(getHalls()));

        contactNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactPhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactWebsiteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactFirmCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactOtherCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tableViewHappenings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private ObservableList<Hall> getHalls(){
        ObservableList<Hall> halls = FXCollections.observableArrayList();
        for (Hall hall : hallList){
            halls.add(hall);
        }
        return halls;
    }

    public void deleteHappeningBtn(){
        deleteHappeningFromTableView();
    }

    private void deleteHappeningFromTableView(){
        ObservableList<Happening> selectedRows;

        // contains the selected rows
        selectedRows = tableViewHappenings.getSelectionModel().getSelectedItems();
        for (Happening happening : selectedRows){
            happeningList.remove(happening);
        }
        for(Happening happenings : happeningModel.getHappeningList()) {
            tableViewHappenings.getItems().remove(happenings);
        }
        tableViewHappenings.setItems(getHappenings());
    }

    // Method to access the SeeOrdersToHappeningController to send the selected object to the initData-method
    public void seeOrdersToHappeningBtn(ActionEvent event) throws IOException {
        ObservableList<Happening> selectedRows;
        selectedRows = tableViewHappenings.getSelectionModel().getSelectedItems();

        if (!selectedRows.isEmpty()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("seeOrdersToHappening.fxml"));
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            SeeOrdersToHappeningController controller = loader.getController();

            // Access the controller and call the initData-method
            controller.initData(tableViewHappenings.getSelectionModel().getSelectedItem());
        } else {
            InvalidInputHandler.generateAlert(new InvalidInputException("Arrangement er ikke valgt. For å kunne se " +
                    "billetter til et arrangement må du markere arrangementet du ønsker å se billetter til. "));
        }
    }


    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, AdminManageHappeningsController.class, "adminMainPage.fxml");
    }

    /*
    Methods to change and set happening data to new values in tableView with double-click
     */

    public void editHappeningNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setName(edittedCell.getNewValue().toString());
    }

    public void editHappeningPerformersCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setPerformers(edittedCell.getNewValue().toString());
    }

    public void editHappeningTimeCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidTime(edittedCell.getNewValue().toString());
        happeningSelected.setTime(edittedCell.getNewValue().toString());
    }

    public void editHappeningDateCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidDate(edittedCell.getNewValue().toString());
        happeningSelected.setDate(edittedCell.getNewValue().toString());
    }

    public void editHappeningScheduleCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setSchedule(edittedCell.getNewValue().toString());
    }

    public void editHappeningPriceCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidTicketPrice(edittedCell.getNewValue().toString());
        happeningSelected.setTicketPrice(edittedCell.getNewValue().toString());
    }

    public void editHappeningTypeCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setType(edittedCell.getNewValue().toString());
    }

    public void editHappeningHallCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setHall((Hall)edittedCell.getNewValue());
    }

    public void editHappeningContactNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonName(edittedCell.getNewValue().toString());
    }

    public void editHappeningContactPhoneCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidPhone(edittedCell.getNewValue().toString());
        happeningSelected.setContactPersonPhone(edittedCell.getNewValue().toString());
    }

    public void editHappeningContactEmailCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidEmail(edittedCell.getNewValue().toString());
        happeningSelected.setContactPersonEmail(edittedCell.getNewValue().toString());
    }

    public void editHappeningContactWebpageCellEvent(TableColumn.CellEditEvent edittedCell) {
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonWebpage(edittedCell.getNewValue().toString());
    }

    public void editHappeningContactFirmCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonFirm(edittedCell.getNewValue().toString());
    }

    public void editHappeningContactOtherCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonOther(edittedCell.getNewValue().toString());
    }

}
