module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfuzzylite;

    opens org.example to javafx.fxml;
    exports org.example;
}