package me.masterdash5.unoproject.model;

public enum CardType {

    DRAW2,
    SKIP,
    REVERSE,
    WILD,
    WILD4,
    NUMBER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
