package edu.hm.arar.aufgabe3;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public interface Regeln {

    PunkteVerteilung berechneRundenSieger(int playerAChoice,int playerBChoice);

    Ergebnis berechneGewinner(int playerAScore, int playerBScore);

    boolean istEingabeKorrekt (int input);
}
