package com.example.planet_voting;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class PlanetVotingWindow implements WindowDisplayable{
    private Stage stage;
    private int voteWeight;

    public PlanetVotingWindow(Stage stage) {
        this.stage = stage;
        this.voteWeight = voteWeight;
    }

    @Override
    public void show() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(AppUtils.createAppBackground());

        // Example planets with placeholder paths for images
        PlanetInfo[] planets = {
                new PlanetInfo("Minerva", "Entirely Oceanic,Home to vibrant marine life and submerged mountains rich in precious minerals. A world of endless ocean exploration.", "C:\\Users\\megis\\java_letnySem_2024\\minerva.jpeg"),
                new PlanetInfo("Orestes", "Endless Lush Forests,Covered in ancient forests, this planet boasts rich biodiversity and symbiotic relationships between its flora and fauna.", "C:\\\\Users\\\\megis\\\\java_letnySem_2024\\\\orestes.jpeg"),
                new PlanetInfo("Somnus", "Desert of Sparkling Crystals,A mesmerizing desert landscape where the sands and formations glitter with countless crystals, offering both beauty and valuable resources.", "C:\\Users\\megis\\java_letnySem_2024\\somnus.jpeg")
        };

        for (PlanetInfo planet : planets) {
            VBox planetBox = createPlanetBox(planet);
            layout.getChildren().add(planetBox);
        }

        stage.setScene(new Scene(layout, 600, 700)); // Adjusted for potentially larger content
        stage.show();
    }

    private VBox createPlanetBox(PlanetInfo planet) {
        ImageView planetImage = new ImageView(new Image(planet.imagePath));
        planetImage.setFitHeight(100); // Set the image size as needed
        planetImage.setFitWidth(100);

        Label planetName = new Label(planet.name);
        planetName.setFont(Font.font("Century Gothic", 16));
        planetName.setTextFill(Color.DARKBLUE);

        Text description = new Text(planet.description);
        description.setFont(Font.font("Century Gothic", 14));
        description.setFill(Color.BLACK);
        description.setWrappingWidth(350);

        Button voteButton = new Button("Vote for " + planet.name);
        voteButton.setFont(Font.font("Century Gothic", 14));
        voteButton.setOnAction(e -> {
            VotingAppGUI.recordVote(planet.name, this.voteWeight);
            new VoteConfirmationWindow(stage, planet.name).show();
        });
        voteButton.setStyle("-fx-background-color: #0C2D57; -fx-text-fill: white;");

        VBox planetBox = new VBox(5, planetImage, planetName, description, voteButton);
        planetBox.setAlignment(Pos.CENTER);

        return planetBox;
    }

    private static class PlanetInfo {
        String name;
        String description;
        String imagePath;

        PlanetInfo(String name, String description, String imagePath) {
            this.name = name;
            this.description = description;
            this.imagePath = imagePath;
        }
    }


    public PlanetVotingWindow(Stage stage, int voteWeight) {
        this.stage = stage;
        this.voteWeight = voteWeight; // Save the vote weight
    }


}
