module com.example.planet_voting {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.planet_voting to javafx.fxml;
    exports com.example.planet_voting;
}