package org.kulturhusfx.util;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.controllers.MainPageController;
import org.kulturhusfx.controllers.PurchaseTicketController;

//class for adding and handling buttons on main page, when clicking to order tickets
public class OrderButton extends TableCell<Happening, Boolean> {

    private final Button cellButton = new Button("Bestill");
    private SceneUtils sceneUtils = SceneUtils.getInstance();

    public OrderButton() {
        cellButton.setOnAction(event -> {
            MainPageController.currentHappening = OrderButton.this.getTableView().getItems().get(OrderButton.this.getIndex());
            sceneUtils.launchScene(event, PurchaseTicketController.class, "purchaseTicket.fxml");
        });
    }

    @Override
    //adds a button to row, if the row is not empty
    protected void updateItem(Boolean b, boolean empty) {
        super.updateItem(b, empty);
        if (!empty) {
            setGraphic(cellButton);
        } else {
            setGraphic(null);
        }
    }
}
