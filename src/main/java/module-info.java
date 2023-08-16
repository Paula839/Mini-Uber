module com.example.miniuber {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.miniuber to javafx.fxml;
    exports com.example.miniuber;
}