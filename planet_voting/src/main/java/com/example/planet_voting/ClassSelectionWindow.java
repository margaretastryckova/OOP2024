package com.example.planet_voting;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class ClassSelectionWindow implements WindowDisplayable {
    private Stage stage;
    private int voteWeight;

    public ClassSelectionWindow(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void show() {
        Label chooseClassLabel = new Label("Choose your class:");
        chooseClassLabel.setFont(Font.font("Century Gothic", 20));
        chooseClassLabel.setTextFill(Color.BLACK);

        ComboBox<String> classComboBox = new ComboBox<>();
        classComboBox.getItems().addAll("Lower", "Middle", "High");
        classComboBox.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 14;");

        Button proceedButton = new Button("Proceed");
        proceedButton.setFont(Font.font("Century Gothic", 16));
        proceedButton.setStyle("-fx-text-fill: #0C2D57;");
        proceedButton.setOnAction(e -> {
            String selectedClass = classComboBox.getValue();
            if (classComboBox.getValue() == null) {
                // Show an alert if no class is selected
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Class Selection Required");
                alert.setHeaderText(null); // No header
                alert.setContentText("Please select your class before proceeding.");
                alert.showAndWait();
            } else {
                int weight = 1; // Default weight
                if (selectedClass.equals("Middle")) {
                    weight = 2; // Middle class weight
                } else if (selectedClass.equals("High")) {
                    weight = 3; // High class weight
                }
                new PlanetVotingWindow(stage,weight).show();
            }
        });

        VBox layout = new VBox(10, chooseClassLabel, classComboBox, proceedButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.show();
    }
}
