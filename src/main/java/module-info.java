module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.kulturhusfx.controllers to javafx.fxml;

    exports org.kulturhusfx;
    exports org.kulturhusfx.controllers;
    exports org.kulturhusfx.base;
    exports org.kulturhusfx.util;
    exports org.kulturhusfx.model;
}