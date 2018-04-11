package edu.hm.arar.aufgabe3;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class Spielregeln implements Regeln {
    @Override
    public PunkteVerteilung berechneRundenSieger(int playerAChoice, int playerBChoice) {
        PunkteVerteilung result;
        if(playerAChoice == playerBChoice - 1)
            result = PunkteVerteilung.PLAYER_A_BEKOMMT_PUNKTE;
        else if(playerBChoice == playerAChoice - 1)
            result = PunkteVerteilung.PLAYER_B_BEKOMMT_PUNKTE;
        else {
            result = PunkteVerteilung.BEIDE_BEKOMMEN_PUNKTE;
        }
        return result;
    }

    @Override
    public boolean istEingabeKorrekt(int input) {
        return (input < 0);
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
}
