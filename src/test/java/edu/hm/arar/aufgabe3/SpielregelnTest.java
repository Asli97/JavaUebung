package edu.hm.arar.aufgabe3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class SpielregelnTest {

    private Regeln sut;

    @Before
    public void setup(){
        sut = new Spielregeln();
    }

    @Test
    public void testTie(){
        Ergebnis have = sut.berechneGewinner(5,5);
        assertEquals(Ergebnis.TIE,have);
    }

    @Test
    public void testPlayerAWin(){
        Ergebnis have = sut.berechneGewinner(5,3);
        assertEquals(Ergebnis.PLAYER_A_HAT_GEWONNEN,have);
    }

    @Test
    public void testPlayerBWin(){
        Ergebnis have = sut.berechneGewinner(5,6);
        assertEquals(Ergebnis.PLAYER_B_HAT_GEWONNEN,have);
    }


}
