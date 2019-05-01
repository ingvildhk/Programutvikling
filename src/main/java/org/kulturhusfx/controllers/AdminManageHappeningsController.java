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
    private TableColumn<Happening, String> happeningNameCol, happeningTypeCol, happeningPerformersCol,
            happeningTimeCol, happeningDateCol, happeningScheduleCol, happeningPriceCol, contactNameCol,
            contactFirmCol, contactWebsiteCol, contactPhoneCol, contactEmailCol, contactOtherCol;

    @FXML
    private  TableColumn<Happening, Hall> hallCol;

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
        happeningNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        happeningTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        happeningPerformersCol.setCellValueFactory(new PropertyValueFactory<>("performers"));
        hallCol.setCellValueFactory(new PropertyValueFactory<>("hall"));
        happeningTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        happeningDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        happeningPriceCol.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        happeningScheduleCol.setCellValueFactory(new PropertyValueFactory<>("schedule"));

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

    /* Method to create ObservableList of happenings ArrayList */
    private ObservableList<Happening> getHappenings(){
        ObservableList<Happening> happenings = FXCollections.observableArrayList();
        for (Happening happening : happeningList){
            happenings.addAll(happening);
        }
        return happenings;
    }

    private void setEditableColumns() {
        // To edit text fields, editable must be true
        tableViewHappenings.setEditable(true);

        // To make columns editable when double-clicked
        happeningNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        happeningTypeCol.setCellFactory(ComboBoxTableCell.forTableColumn("Konsert", "Teater", "Konferanse",
                "Forestilling", "Annet"));
        happeningPerformersCol.setCellFactory(TextFieldTableCell.forTableColumn());
        happeningTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        happeningDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        happeningScheduleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        happeningPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        hallCol.setCellFactory(ComboBoxTableCell.forTableColumn(getHalls()));

        contactNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactPhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactWebsiteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactFirmCol.setCellFactory(TextFieldTableCell.forTableColumn());
        contactOtherCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    /* Method to create ObservableList of halls ArrayList */
    private ObservableList<Hall> getHalls(){
        ObservableList<Hall> halls = FXCollections.observableArrayList();
        for (Hall hall : hallList){
            halls.addAll(hall);
        }
        return halls;
    }

    public void deleteHappeningBtn(){
        deleteHappeningViaTableView();
    }

    private void deleteHappeningViaTableView(){
        ObservableList<Happening> selectedRows;

        // contains the selected rows
        selectedRows = tableViewHappenings.getSelectionModel().getSelectedItems();
        for (Happening happening : selectedRows){
            happeningList.remove(happening);
        }
        for(Happening happening : happeningModel.getHappeningList()) {
            tableViewHappenings.getItems().remove(happening);
        }
        tableViewHappenings.setItems(getHappenings());
    }

    public void seeOrdersToHappeningBtn(ActionEvent event) throws IOException {
        seeOrdersToSelectedHappening(event);
    }

    // Method to access the SeeOrdersToHappeningController and send the selected object to the
    // viewOrdersToSelectedEvent-method
    private void seeOrdersToSelectedHappening(ActionEvent event) throws IOException{
        ObservableList<Happening> selectedRow;
        selectedRow = tableViewHappenings.getSelectionModel().getSelectedItems();

        if (!selectedRow.isEmpty()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("seeOrdersToSelectedHappening.fxml"));
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

            SeeOrdersToHappeningController controller = loader.getController();

            // Access the controller and call the viewOrdersToSelectedEvent-method
            controller.viewOrdersToSelectedEvent(tableViewHappenings.getSelectionModel().getSelectedItem());
        } else {
            InvalidInputHandler.generateAlert(new InvalidInputException("Arrangement er ikke valgt. For å kunne se " +
                    "billetter til et arrangement må du markere arrangementet du ønsker å se billetter til. "));
        }
    }

    public void backToAdminMainPageBtn(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, AdminManageHappeningsController.class, "adminMainPage.fxml");
    }

    /* Methods to change and set happening data to new values in tableView */

    public void setHappeningNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setName(edittedCell.getNewValue().toString());
    }

    public void setHappeningPerformersCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setPerformers(edittedCell.getNewValue().toString());
    }

    public void setHappeningTimeCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidTime(edittedCell.getNewValue().toString());
        happeningSelected.setTime(edittedCell.getNewValue().toString());
    }

    public void setHappeningDateCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidDate(edittedCell.getNewValue().toString());
        happeningSelected.setDate(edittedCell.getNewValue().toString());
    }

    public void setHappeningScheduleCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setSchedule(edittedCell.getNewValue().toString());
    }

    public void setHappeningPriceCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidTicketPrice(edittedCell.getNewValue().toString());
        happeningSelected.setTicketPrice(edittedCell.getNewValue().toString());
    }

    public void setHappeningTypeCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setType(edittedCell.getNewValue().toString());
    }

    public void setHappeningHallCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setHall((Hall)edittedCell.getNewValue());
    }

    public void setHappeningContactNameCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonName(edittedCell.getNewValue().toString());
    }

    public void setHappeningContactPhoneCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidPhone(edittedCell.getNewValue().toString());
        happeningSelected.setContactPersonPhone(edittedCell.getNewValue().toString());
    }

    public void setHappeningContactEmailCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        Checker.checkValidEmail(edittedCell.getNewValue().toString());
        happeningSelected.setContactPersonEmail(edittedCell.getNewValue().toString());
    }

    public void setHappeningContactWebpageCellEvent(TableColumn.CellEditEvent edittedCell) {
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonWebpage(edittedCell.getNewValue().toString());
    }

    public void setHappeningContactFirmCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonFirm(edittedCell.getNewValue().toString());
    }

    public void setHappeningContactOtherCellEvent(TableColumn.CellEditEvent edittedCell){
        Happening happeningSelected = tableViewHappenings.getSelectionModel().getSelectedItem();
        happeningSelected.setContactPersonOther(edittedCell.getNewValue().toString());
    }

}
