package edu.hm.arar.aufgabe3.Regeln;

import edu.hm.arar.aufgabe3.Ergebnis;
import edu.hm.arar.aufgabe3.PunkteVerteilung;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sümeyye on 16.04.2018.
 */
public class PotSpielregeln implements Regeln {

    private int pot = 0;
    private int counter = 0;
    private final int limit = 3;

    @Override
    public List<Integer> berechneRundenSieger(int playerAChoice, int playerBChoice) {
        final List<Integer> result = new ArrayList<>();
        if (playerAChoice == playerBChoice) {
            pot += playerAChoice + playerBChoice;
            result.add(0);
            result.add(0);
            counter++;
        } else if (playerAChoice == playerBChoice - 1) {
            result.add(playerAChoice + playerBChoice + pot);
            result.add(0);
            pot = 0;
            counter =0;
        } else if (playerBChoice == playerAChoice - 1) {
            result.add(0);
            result.add(playerAChoice + playerBChoice + pot);
            pot = 0;
            counter = 0;
        } else {
            result.add(playerAChoice);
            result.add(playerBChoice);
            counter = 0;
        }
        return result;
    }

    @Override
    public Ergebnis berechneGewinner(int playerAScore, int playerBScore) {
        Ergebnis ergebnis;
        if (playerAScore == playerBScore)
            ergebnis = Ergebnis.TIE;
        else if (playerAScore > playerBScore)
            ergebnis = Ergebnis.PLAYER_A_HAT_GEWONNEN;
        else
            ergebnis = Ergebnis.PLAYER_B_HAT_GEWONNEN;
        return ergebnis;
    }

    @Override
    public boolean nochZuegeUebrig(int playerAScore, int playerBScore, int scoreToWin) {
        return playerAScore < scoreToWin && playerBScore < scoreToWin && counter < limit;
    }


}
