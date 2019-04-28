package org.kulturhusfx.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.util.OrderButton;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public class MainPageController {

    //TODO copy how it's done in managehappeningscontroller?
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

    public void initialize() {

        addButtons();
        setColumnValues();
        //onInputMethodTextChanged();
        filteringTxtField();
    }

    // Skal fikses
    public void onInputMethodTextChanged() {
        filteringTxtField();
    }

    // Method that allows user to filter value in cells
    public void filteringTxtField() {
        srcTxtField.textProperty().addListener(observable -> {
            if (srcTxtField.textProperty().get().isEmpty()) {
                tableViewHappenings.setItems(getHappenings());
                return;
            }

            ObservableList<Happening> happeningList = FXCollections.observableArrayList();
            ObservableList<TableColumn<Happening, ?>> columns = tableViewHappenings.getColumns();

            for (int i = 0; i < getHappenings().size(); i++)
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
            addButtons();
        });
    }

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    private void addButtons() {
        OrderColumn.setCellValueFactory(
                property -> new SimpleBooleanProperty(property.getValue() != null));

        //adds buttons to not-empty cells
        OrderColumn.setCellFactory(
                property -> new OrderButton());
    }

    private void setColumnValues() {
        HappeningColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("type"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("date"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("time"));
        AvailableColumn.setCellValueFactory(new PropertyValueFactory<Happening, String>("availableTickets"));
        tableViewHappenings.setItems(getHappenings());
    }

    private ObservableList<Happening> getHappenings() {
        ObservableList<Happening> happenings = FXCollections.observableArrayList();

        for (Happening happening : happeningList) {
            happenings.add(happening);
        }
        return happenings;
    }
}

