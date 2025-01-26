package me.masterdash5.unoproject.model;

public interface Card {
    // Card for UNO
    CardColor getColor();
    CardType getType();
    int getNumber();

    void setCardColor(CardColor color);
    void setCardType(CardType type);
    void setCardNumber(int number);

    // Set URL for card image
    default String getURL() {
        // "color-number.png", "wild.png", "wild4.png", "color-draw-2.png", "color-reverse.png", "color-skip.png"
        String url = "";
        switch (getType()) {
            case NUMBER:
                url = getColor().toString() + "-" + getNumber() + ".png";
                break;
            case WILD:
                url = "wild.png";
                break;
            case WILD4:
                url = "wild4.png";
                break;
            case DRAW2:
                url = getColor().toString() + "-draw-2.png";
                break;
            case REVERSE:
                url = getColor().toString() + "-reverse.png";
                break;
            case SKIP:
                url = getColor().toString() + "-skip.png";
                break;
        }
        return url;
    }
}
