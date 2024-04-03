package com.example.planet_voting;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class IdEntryWindow implements WindowDisplayable{
    private Stage stage;
    private TextField[] digitFields = new TextField[7];
    private HBox digitsContainer = new HBox(5);
    private TextField nameField; // Add a TextField for the name

    public IdEntryWindow(Stage stage) {
        this.stage = stage;
    }

    private void applyTextFormatter(TextField textField, int maxLength) {
        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(), null, change -> {
            String newText = change.getControlNewText();
            if (newText.length() > maxLength) {
                return null;
            } else {
                if (!newText.matches("\\d*")) { // Allow only digits
                    return null;
                }
            }
            return change;
        });

        textField.setTextFormatter(formatter);
    }


    @Override
    public void show() {
        Label nameLabel = new Label("Enter your name:");
        nameLabel.setFont(Font.font("Century Gothic", 20));
        nameLabel.setTextFill(Color.BLACK);

        nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setMaxWidth(200);

        Label instructionLabel = new Label("Enter your 7-digit Voter ID:");
        instructionLabel.setFont(Font.font("Century Gothic", 20));
        instructionLabel.setTextFill(Color.BLACK);

        prepareDigitFields();

        Button proceedButton = new Button("Proceed");
        proceedButton.setFont(Font.font("Century Gothic", 16));
        proceedButton.setStyle("-fx-text-fill: #0C2D57;");
        proceedButton.setOnAction(e -> validateAndProceed());

        VBox layout = new VBox(20, nameLabel, nameField, instructionLabel, digitsContainer, proceedButton);
        layout.setAlignment(Pos.CENTER);
        digitsContainer.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.show();
    }

    private void prepareDigitFields() {
        for (int i = 0; i < digitFields.length; i++) {
            final int index = i;
            digitFields[i] = new TextField("_");
            digitFields[i].setFont(Font.font("Century Gothic", 16));
            digitFields[i].setPrefWidth(30);
            applyTextFormatter(digitFields[i], 1); // Apply formatter to restrict input

            // Focus next field on input
            digitFields[i].textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.isEmpty() && !"_".equals(newValue)) {
                    if (index < digitFields.length - 1) {
                        digitFields[index + 1].requestFocus();
                    }
                }
            });
            digitsContainer.getChildren().add(digitFields[i]);
        }
    }

    private void validateAndProceed() {
        String userName = nameField.getText().trim();
        StringBuilder voterIdBuilder = new StringBuilder();
        for (TextField digitField : digitFields) {
            String text = digitField.getText().trim();
            // Check if any of the digit fields are not filled (still "_")
            if ("_".equals(text)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Please enter a complete 7-digit Voter ID.");
                alert.showAndWait();
                return;
            }
            voterIdBuilder.append(text);
        }
        String voterId = voterIdBuilder.toString();

        // Admin check
        if (userName.equalsIgnoreCase("margareta") && voterId.equals("1111111")) {
            new Admin(stage).showOptions();
            return; // Exit method for admin
        }

        // Normal user validation
        if (userName.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Please enter your name.");
            alert.showAndWait();
            return;
        }

        if (voterId.length() == 7) {
            if (VotingAppGUI.hasVoted(voterId)) {
                Alert alreadyVotedAlert = new Alert(AlertType.WARNING);
                alreadyVotedAlert.setTitle("Already Voted");
                alreadyVotedAlert.setContentText("Looks like you have already voted. Sorry, you can vote only once.");
                alreadyVotedAlert.showAndWait();
            } else {
                VotingAppGUI.addVotedId(voterId);
                System.out.println("Name: " + userName + ", Voter ID: " + voterId);
                new ClassSelectionWindow(stage).show(); // Proceed to class selection
            }
        }
    }

}

