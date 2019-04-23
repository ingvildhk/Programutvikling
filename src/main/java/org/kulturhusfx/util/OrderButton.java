package org.kulturhusfx.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.controllers.MainPageController;

public class OrderButton extends TableCell<Event, Boolean> {
    final Button cellButton = new Button("Bestill");
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public OrderButton(){
        //Hva som skjer når man trykker på bestill
        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                ///Henter ut det eventet som er på raden man trykker på
                MainPageController.currentEvent = OrderButton.this.getTableView().getItems().get(OrderButton.this.getIndex());
                sceneUtils.launchScene(event, MainPageController.class, "purchaseTicket.fxml");
            }
        });
    }

    //Legger til knapp hvis raden ikke er tom
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }
}
