package me.masterdash5.unoproject.model;

public enum CardColor {

    RED,
    GREEN,
    BLUE,
    YELLOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
