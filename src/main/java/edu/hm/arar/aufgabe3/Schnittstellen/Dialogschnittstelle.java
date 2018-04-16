package edu.hm.arar.aufgabe3.Schnittstellen;

import java.io.IOException;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class Dialogschnittstelle implements Schnittstelle {

    @Override
    public int einlesen() throws IOException {
        return System.in.read();
    }

    @Override
    public boolean istEingabeKorrekt(int input) {
        return (input < 0);
    }

    @Override
    public void zeileAusgeben(String ausgabeString) {
        System.out.println(ausgabeString);
    }
}
