//package com.example.planet_voting;
//
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Text;
//import javafx.scene.text.Font;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//public class VoteConfirmationWindow {
//    private Stage stage;
//    private String votedPlanet;
//
//    public VoteConfirmationWindow(Stage stage, String votedPlanet) {
//        this.stage = stage;
//        this.votedPlanet = votedPlanet;
//        VotingAppGUI.recordVote(votedPlanet); // Record the vote
//    }
//
//    public void show() {
//        Text thankYouMessage = new Text("Thank you for voting for " + votedPlanet + "!");
//        thankYouMessage.setFont(Font.font("Century Gothic", 25));
//        thankYouMessage.setFill(Color.LIGHTPINK);
//
//        Button closeButton = new Button("Close");
//        closeButton.setOnAction(e -> stage.close());
//
//        Button voteAgainButton = new Button("Vote Again");
//        voteAgainButton.setOnAction(e -> new IdEntryWindow(stage).show());
//
//        VBox layout = new VBox(20, thankYouMessage, closeButton, voteAgainButton);
//        layout.setAlignment(Pos.CENTER);
//        layout.setBackground(AppUtils.createAppBackground());
//
//        stage.setScene(new Scene(layout, 500, 400));
//        stage.show();
//    }
//}




package com.example.planet_voting;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VoteConfirmationWindow {
    private Stage stage;
    private String votedPlanet;
    private int voteWeight;

    public VoteConfirmationWindow(Stage stage, String votedPlanet) {
        this.stage = stage;
        this.votedPlanet = votedPlanet;
        this.voteWeight = voteWeight;
        VotingAppGUI.recordVote(votedPlanet, voteWeight); // Record the vote
    }

    public void show() {
        Text thankYouMessage = new Text("Thank you for voting for " + votedPlanet + "!");
        thankYouMessage.setFont(Font.font("Century Gothic", 25));
        thankYouMessage.setFill(Color.BLACK);

        Button closeButton = new Button("Close");
        // Nastavte štýl pre closeButton
        closeButton.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 16px; -fx-text-fill: #0C2D57;");
        closeButton.setOnAction(e -> stage.close());

        Button voteAgainButton = new Button("Vote Again");
        // Nastavte štýl pre voteAgainButton
        voteAgainButton.setStyle("-fx-font-family: 'Century Gothic'; -fx-font-size: 16px; -fx-text-fill: #0C2D57;");
        voteAgainButton.setOnAction(e -> {
            stage.close(); // Správne použite už dostupný stage
            new IdEntryWindow(stage).show(); // Znovu otvorte okno pre zadanie ID použitím toho istého stage
        });

        VBox layout = new VBox(20, thankYouMessage, closeButton, voteAgainButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.show();
    }
}
