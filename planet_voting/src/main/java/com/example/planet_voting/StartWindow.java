package com.example.planet_voting;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartWindow implements  WindowDisplayable{
    private Stage stage;

    public StartWindow(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void show() {
        Text heading = new Text("Time to Choose Your Future");
        heading.setFont(Font.font("Century Gothic", 25));
        heading.setFill(Color.BLACK);

        Button startButton = new Button("Start Voting!");
        startButton.setOnAction(e -> new IdEntryWindow(stage).show());
        // Add Century Gothic font to the button style
        startButton.setStyle("-fx-background-color: LIGHTPINK; -fx-text-fill: #0C2D57; -fx-font-family: 'Century Gothic'; -fx-font-size: 14px;");

        VBox layout = new VBox(20, heading, startButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.show();
    }
}
