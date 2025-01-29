package me.masterdash5.unoproject;

import static org.junit.jupiter.api.Assertions.*;
import me.masterdash5.unoproject.controller.UnoController;
import me.masterdash5.unoproject.model.cards.Card_Number;
import me.masterdash5.unoproject.model.CardColor;
import me.masterdash5.unoproject.model.Card;
import javafx.application.Platform;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class tests extends ApplicationTest {

    private UnoController controller;

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        controller = new UnoController();
        controller.startGame();
        controller.getDeck().addToDiscardPile(new Card_Number(CardColor.RED, 5));
    }

    @Test
    void testIsValidPlay_SameColor() {
        controller.startGame();
        controller.getDeck().addToDiscardPile(new Card_Number(CardColor.RED, 5));
        Card redCard = new Card_Number(CardColor.RED, 7);
        assertTrue(controller.isValidPlay(redCard));
    }

    @Test
    void testIsValidPlay_DifferentColorSameNumber() {
        controller.startGame();
        controller.getDeck().addToDiscardPile(new Card_Number(CardColor.RED, 5));
        Card blueCard = new Card_Number(CardColor.BLUE, 5);
        assertTrue(controller.isValidPlay(blueCard));
    }

    @Test
    void testIsValidPlay_InvalidCard() {
        controller.startGame();
        controller.getDeck().addToDiscardPile(new Card_Number(CardColor.RED, 5));
        Card blueCard = new Card_Number(CardColor.BLUE, 8);
        assertFalse(controller.isValidPlay(blueCard));
    }
}
