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

    //to transfer the information about the current happening to be able to order tickets
    public static Happening currentHappening;

    @FXML
    public TableView<Happening> tableViewHappenings;
    @FXML
    private TableColumn<Happening, String> HappeningColumn, TypeColumn, DateColumn, TimeColumn, AvailableColumn;

    @FXML
    private TableColumn<Happening, Boolean> OrderColumn;

    @FXML
    private TextField srcTxtField;

    private HappeningModel happeningModel = HappeningModel.getInstance();
    private List<Happening> happeningList = happeningModel.getHappeningList();
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public void initialize() {
        setColumnValues();
        addButtons();
        filteringTxtField();
    }


    // Method that allows user to search for value in table cells
    public void filteringTxtField() {
        srcTxtField.textProperty().addListener(observable -> {
            if (srcTxtField.textProperty().get().isEmpty()) {
                tableViewHappenings.setItems(getHappenings());
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
        });
    }

    public void handleAdminLoginBtnAction(ActionEvent event) throws IOException {
        sceneUtils.launchScene(event, MainPageController.class, "adminMainPage.fxml");
    }

    //adds buttons to not-empty cells
    private void addButtons() {
        OrderColumn.setCellValueFactory(
                property -> new SimpleBooleanProperty(property.getValue() != null));

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
            happenings.addAll(happening);
        }
        return happenings;
    }
}

