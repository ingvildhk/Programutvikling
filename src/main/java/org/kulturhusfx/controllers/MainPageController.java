package org.kulturhusfx.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kulturhusfx.base.ContactPerson;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.OrderButton;
import org.kulturhusfx.util.SceneUtils;
import org.kulturhusfx.util.fileHandling.ReadFileCsv;
import org.kulturhusfx.util.fileHandling.ReadFileJobj;

import java.io.IOException;
import java.util.List;

public class MainPageController {

    //Dette er ok :)
    public static Happening currentHappening;

   @FXML
    private TableView<Happening> tableViewHappenings;
    @FXML
    private TableColumn<Happening, String> HappeningColumn, TypeColumn, DateColumn, TimeColumn, AvailableColumn;

    @FXML
    private TableColumn<Happening, Boolean> OrderColumn;

    @FXML
    private TextField srcTxtField;

    private HallModel hallModel = HallModel.getInstance();
    private HappeningModel happeningModel = HappeningModel.getInstance();
    private List<Happening> happeningList = happeningModel.getHappeningList();
    private List<Hall> hallList = hallModel.getHallList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public void initialize(){

        addButtons();
        setColumnValues();
        //onInputMethodTextChanged();
        filteringTxtField();
    }

    // Skal fikses
    public void onInputMethodTextChanged(){
        filteringTxtField();
    }

    // Endre til lambda
    // Method that allows user to search for value in cells
    public void filteringTxtField(){
        srcTxtField.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(srcTxtField.textProperty().get().isEmpty()){
                    tableViewHappenings.setItems(getHappenings());
                    return;
                }

                ObservableList<Happening> happeningList = FXCollections.observableArrayList();
                ObservableList<TableColumn<Happening, ?>> columns = tableViewHappenings.getColumns();

                for(int i = 0; i < getHappenings().size(); i++)
                    for (int j = 0; j < columns.size(); j++) {
                        TableColumn col = columns.get(j);

                        String cellValue = col.getCellData(getHappenings().get(i)).toString();

                        cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(srcTxtField.textProperty().get().toLowerCase())) {
                            happeningList.add(getHappenings().get(i));
                            break;
                        }
                    }

                tableViewHappenings.setItems(happeningList);
            }
        });
    }

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    private void addButtons(){
        OrderColumn.setCellValueFactory(
                property -> new SimpleBooleanProperty(property.getValue() != null));

        //Trenger ikke denne koden da den er erstattet med lambda. Ligger her i tilfelle noe går galt
                /*new Callback<TableColumn.CellDataFeatures<Happening, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Happening, Boolean> property) {
                        return new SimpleBooleanProperty(property.getValue() != null);
                    }
                });*/

        //Legger til knapp i cell
        OrderColumn.setCellFactory(
                property -> new OrderButton());

        /*Trenger ikke denne koden da den er erstattet med lambda. Ligger her i tilfelle noe går galt
                new Callback<TableColumn<Happening, Boolean>, TableCell<Happening, Boolean>>() {

                    @Override
                    public TableCell<Happening, Boolean> call(TableColumn<Happening, Boolean> property) {
                        return new OrderButton();
                    }
                });*/
    }

    private void setColumnValues(){
        HappeningColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("time"));
        AvailableColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("availableTickets"));
        tableViewHappenings.setItems(getHappenings());
    }

    private ObservableList<Happening> getHappenings(){
        ObservableList<Happening> happenings = FXCollections.observableArrayList();

        for (Happening happening : happeningList){
            happenings.add(happening);
        }
        return happenings;
    }
}

