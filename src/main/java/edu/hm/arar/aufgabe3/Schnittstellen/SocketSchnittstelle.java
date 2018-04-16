package edu.hm.arar.aufgabe3.Schnittstellen;

import java.io.IOException;

/**
 * Created by Sümeyye on 16.04.2018.
 */
public class SocketSchnittstelle implements Schnittstelle {

    @Override
    public int einlesen() throws IOException {
        return 0;
    }

    @Override
    public void zeileAusgeben(String ausgabeString) throws IOException {

    }

    @Override
    public boolean istEingabeKorrekt(int input) {
        return false;
    }
}
