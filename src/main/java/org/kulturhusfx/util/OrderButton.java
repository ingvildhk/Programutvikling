package org.kulturhusfx.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.controllers.MainPageController;
import org.kulturhusfx.controllers.PurchaseTicketController;

//class for adding and handling buttons on main page, when clicking to order tickets
public class OrderButton extends TableCell<Happening, Boolean> {
    final Button cellButton = new Button("Bestill");
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public OrderButton() {
        cellButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainPageController.currentHappening = OrderButton.this.getTableView().getItems().get(OrderButton.this.getIndex());
                sceneUtils.launchScene(event, PurchaseTicketController.class, "purchaseTicket.fxml");
            }
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            setGraphic(cellButton);
        } else {
            setGraphic(null);
        }
    }
}
