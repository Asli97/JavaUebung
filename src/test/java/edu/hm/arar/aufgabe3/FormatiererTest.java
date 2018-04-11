package edu.hm.arar.aufgabe3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class FormatiererTest {

    @Test
    public void testFormatieren(){
        String have = "Wert: %s - %s";
        int wert = 5;
        int wert2  = 6;

        String result = new StringFormatierer().formatieren(have, wert, wert2);
        assertEquals("Wert: 5 - 6", result);
    }
}
