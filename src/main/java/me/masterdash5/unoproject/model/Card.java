package me.masterdash5.unoproject.model;

import javafx.scene.image.Image;

import java.io.InputStream;

public interface Card {
    // Card for UNO
    CardColor getColor();
    CardType getType();
    int getNumber();

    void setCardColor(CardColor color);
    void setCardType(CardType type);
    void setCardNumber(int number);
    // Set Image for the card
    default Image getImage() {
        String fileName = "";
        switch (getType()) {
            case NUMBER:
                fileName = getColor().toString() + "-" + getNumber() + ".png";
                break;
            case WILD:
                fileName = "wild.png";
                break;
            case WILD4:
                fileName = "wild-draw-4.png";
                break;
            case DRAW2:
                fileName = getColor().toString() + "-draw-2.png";
                break;
            case REVERSE:
                fileName = getColor().toString() + "-reverse.png";
                break;
            case SKIP:
                fileName = getColor().toString() + "-skip.png";
                break;
        }
        // Load the image from the file name
        String resourcePath = "/assets/" + fileName; // Ensure the path is relative to 'resources'
        InputStream stream = getClass().getResourceAsStream(resourcePath);
        if (stream == null) {
            throw new IllegalArgumentException("Image not found: " + resourcePath);
        }
        return new Image(stream);    }

}
