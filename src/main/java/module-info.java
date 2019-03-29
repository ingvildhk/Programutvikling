module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.kulturhusfx to javafx.fxml;
    exports org.kulturhusfx;
}