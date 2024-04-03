package com.example.planet_voting;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Map;
import javafx.scene.paint.Color;

public class Admin {
    private final Stage stage;

    public Admin(Stage stage) {
        this.stage = stage;
    }

    public void showOptions() {
        Button viewResultsButton = new Button("View Current Results");
        viewResultsButton.setOnAction(e -> showCurrentResults());
        viewResultsButton.setFont(Font.font("Century Gothic",18));
        viewResultsButton.setTextFill(Color.DARKBLUE);

        Button endVotingButton = new Button("End Voting and Show Final Results");
        endVotingButton.setOnAction(e -> showFinalResults());
        endVotingButton.setFont(Font.font("Century Gothic",18));
        endVotingButton.setTextFill(Color.DARKBLUE);

        VBox layout = new VBox(10, viewResultsButton, endVotingButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.show();
    }


    private void showCurrentResults() {
        String leadingPlanetSummary = VotingAppGUI.getLeadingPlanet();
        String resultsSummary = VotingAppGUI.getVotesSummary();

        Label leadingLabel = new Label("Current Winner: " + leadingPlanetSummary);
        leadingLabel.setFont(Font.font("Century Gothic", 16));
        leadingLabel.setTextFill(Color.WHITE);

        Label resultsLabel = new Label(resultsSummary);
        resultsLabel.setFont(Font.font("Century Gothic", 16));
        resultsLabel.setTextFill(Color.WHITE);

        Button backButton = new Button("Back to Admin Options");
        backButton.setFont(Font.font("Century Gothic", 16));
        backButton.setOnAction(e -> Platform.runLater(this::showOptions));
        backButton.setTextFill(Color.DARKBLUE);

        VBox layout = new VBox(20, resultsLabel, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.setTitle("Current Voting Results");
    }

    private void showFinalResults() {
        var finalResults = VotingAppGUI.getFinalResults(); // Fetch final results as percentages
        var chart = new PieChart();

        final String chartLabelStyle = "-fx-font-family: 'Century Gothic'; -fx-font-size: 14;";


        // Add slices to the pie chart
        finalResults.forEach((planet, percentage) -> {
            var slice = new PieChart.Data(planet + ": " + String.format("%.2f%%", percentage), percentage);
            chart.getData().add(slice);
            slice.getNode().setStyle(chartLabelStyle);
        });

        chart.setStyle(chartLabelStyle);

        Button backButton = new Button("End Viewing Results");
        backButton.setFont(Font.font("Century Gothic", 14));
        backButton.setTextFill(Color.DARKBLUE);
        backButton.setOnAction(e -> showWinnerAnnouncementInSameWindow(
                finalResults.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("No winner")));

        VBox layout = new VBox(20, chart, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
//        stage.setScene(scene);
        stage.setTitle("Final Voting Results");
    }

    private void showWinnerAnnouncementInSameWindow(String winningPlanet) {
        Label winnerAnnouncement = new Label("Congratulations! The winner planet is " + winningPlanet + "\" \n your new home to live");
        winnerAnnouncement.setFont(new Font("Century Gothic", 18));
        winnerAnnouncement.setTextFill(Color.BLACK);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        closeButton.setFont(Font.font("Century Gothic",16));
        closeButton.setTextFill(Color.DARKBLUE);

        VBox layout = new VBox(20, winnerAnnouncement, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        layout.setBackground(AppUtils.createAppBackground());

        stage.setScene(new Scene(layout, 600, 700));
        stage.setTitle("Winner Announcement");
    }
}
