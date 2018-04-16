package edu.hm.arar.aufgabe3.Schnittstellen;

import java.io.IOException;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public interface Schnittstelle {

    int einlesen() throws IOException;

    void zeileAusgeben(String ausgabeString) throws IOException;

    boolean istEingabeKorrekt (int input);
}
