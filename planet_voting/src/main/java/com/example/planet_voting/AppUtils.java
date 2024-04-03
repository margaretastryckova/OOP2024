package com.example.planet_voting;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;

public class AppUtils {

    public static Background createAppBackground() {
        Image backgroundImage = new Image(AppUtils.class.getResourceAsStream("/pozadie2.jpg"));
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        return new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize));
    }
}

