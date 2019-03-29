module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.kulturhusfx.controllers to javafx.fxml;

    exports org.kulturhusfx;
    exports org.kulturhusfx.controllers;
}