package edu.hm.arar.aufgabe3.Regeln;

import edu.hm.arar.aufgabe3.Ergebnis;
import edu.hm.arar.aufgabe3.PunkteVerteilung;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sümeyye on 16.04.2018.
 */
public class NonPotSpielregeln implements Regeln{


    @Override
    public List<Integer> berechneRundenSieger(int playerAChoice, int playerBChoice) {

        final List<Integer> result = new ArrayList<>();

        if(playerAChoice == playerBChoice - 1) {
            result.add(playerAChoice + playerBChoice);
            result.add(0);

        } else if(playerBChoice == playerAChoice - 1) {
            result.add(0);
            result.add(playerAChoice + playerBChoice);
        } else {
            int ergebnis = playerAChoice - playerBChoice;

            if (ergebnis >= 2 || ergebnis < -2) {
                if (ergebnis > 0) {
                    result.add(playerAChoice + playerBChoice);
                    result.add(0);
                } else {
                    result.add(0);
                    result.add(playerAChoice + playerBChoice);
                }
            } else {
                result.add(playerAChoice);
                result.add(playerBChoice);
            }
        }
        return result;
    }



    @Override
    public Ergebnis berechneGewinner(int playerAScore, int playerBScore) {
        Ergebnis ergebnis;
        if(playerAScore == playerBScore)
            ergebnis = Ergebnis.TIE;
        else if(playerAScore > playerBScore)
            ergebnis = Ergebnis.PLAYER_A_HAT_GEWONNEN;
        else
            ergebnis = Ergebnis.PLAYER_B_HAT_GEWONNEN;
        return ergebnis;
    }

    @Override
    public boolean nochZuegeUebrig(int playerAScore, int playerBScore, int scoreToWin) {
        return playerAScore < scoreToWin && playerBScore < scoreToWin;
    }
}
