module com.example.miniuber {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.miniuber to javafx.fxml;
    exports com.example.miniuber;
}