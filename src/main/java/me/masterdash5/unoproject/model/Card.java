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

    /**
     * Retrieves the image representation of a card based on its type, color, and number.
     * The method determines the appropriate image file name using the card's attributes,
     * and then loads the image resource from the corresponding file path.
     * Throws an exception if the image resource cannot be found.
     *
     * @return the {@code Image} object associated with the card.
     * @throws IllegalArgumentException if the image resource is not found.
     */
    default Image getImage() {
        String fileName = switch (getType()) {
            case NUMBER -> getColor().toString() + "-" + getNumber() + ".png";
            case WILD -> "wild.png";
            case WILD4 -> "wild-draw-4.png";
            case DRAW2 -> getColor().toString() + "-draw-2.png";
            case REVERSE -> getColor().toString() + "-reverse.png";
            case SKIP -> getColor().toString() + "-skip.png";
        };

        // Load the image from the file name
        String resourcePath = "/assets/" + fileName; // Ensure the path is relative to 'resources'
        InputStream stream = getClass().getResourceAsStream(resourcePath);

        if (stream == null)
            throw new IllegalArgumentException("Image not found: " + resourcePath);

        return new Image(stream);
    }

}
