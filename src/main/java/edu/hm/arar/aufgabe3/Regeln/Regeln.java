package edu.hm.arar.aufgabe3.Regeln;

import edu.hm.arar.aufgabe3.Ergebnis;
import edu.hm.arar.aufgabe3.PunkteVerteilung;

import java.util.List;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public interface Regeln {

    List<Integer> berechneRundenSieger(int playerAChoice, int playerBChoice);

    Ergebnis berechneGewinner(int playerAScore, int playerBScore);

    boolean nochZuegeUebrig(int playerAScore, int playerBScore, int maxScore);
}
