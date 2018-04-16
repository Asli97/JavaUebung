package edu.hm.arar.aufgabe3;

/**
 * Created by Sümeyye on 11.04.2018.
 */

/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
import edu.hm.arar.aufgabe3.Formatieren.Formatierer;
import edu.hm.arar.aufgabe3.Formatieren.StringFormatierer;
import edu.hm.arar.aufgabe3.Parameter.InstabilSpielparameters;
import edu.hm.arar.aufgabe3.Parameter.KurzSpielparameters;
import edu.hm.arar.aufgabe3.Parameter.Parameter;
import edu.hm.arar.aufgabe3.Parameter.Spielparameters;
import edu.hm.arar.aufgabe3.Regeln.PotSpielregeln;
import edu.hm.arar.aufgabe3.Regeln.Regeln;
import edu.hm.arar.aufgabe3.Regeln.Spielregeln;
import edu.hm.arar.aufgabe3.Schnittstellen.Dialogschnittstelle;
import edu.hm.arar.aufgabe3.Schnittstellen.Schnittstelle;

import java.io.IOException;
import java.util.List;

/**
 * Monolithic version of Undercut. Violates lots of design principles.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-30
 * @see <a href="http://www.ams.org/publicoutreach/msamhome/96-undercut-index.html">Undercut</a>
 */
public class OurGame {
    /**
     * Entry point.
     * @param args Commandline args: none.
     * @exception IOException on incomplete input.
     */
    public static void main(String... args) throws IOException {
        final Parameter param = new Spielparameters();
        final Schnittstelle schnittstelle = new Dialogschnittstelle();
        final Formatierer formatierer = new StringFormatierer();
        final Regeln regeln = new PotSpielregeln();

        new OurGame().play(param,schnittstelle,formatierer,regeln);
    }

    /**
     * Runs an Undercut game.
     * Gets input from System.in, prints output to System.out.
     * @exception IOException on incomplete input.
     */
    public void play(Parameter param, Schnittstelle schnittstelle, Formatierer formatierer, Regeln regeln) throws IOException {
        final int scoreToWin = param.getScoreToWin();

        int playerAScore = 0;
        int playerBScore = 0;
        int roundsPlayed = 0;

        schnittstelle.zeileAusgeben("Undercut start");
        // loop until a player wins ...
        while(regeln.nochZuegeUebrig(playerAScore, playerBScore, scoreToWin)) {
            int playerAChoice;
            // read players' choices; if invalid, discard and retry
            schnittstelle.zeileAusgeben(formatierer.formatieren("Player A, your choice (%s)?",param.getChoices(1, roundsPlayed)));
            do {
                final int input = schnittstelle.einlesen();
                if(schnittstelle.istEingabeKorrekt(input))
                    throw new IOException(); // bomb out on end of input
                playerAChoice = input - '0';
            }
            while(!param.getChoices(1, roundsPlayed).contains(playerAChoice));

            int playerBChoice;
            schnittstelle.zeileAusgeben(formatierer.formatieren("Player B, your choice (%s)?",param.getChoices(2, roundsPlayed)));
            do {
                final int input = schnittstelle.einlesen();
                if(schnittstelle.istEingabeKorrekt(input))
                    throw new IOException();
                playerBChoice = input - '0';
            }
            while(!param.getChoices(2, roundsPlayed).contains(playerBChoice));

            // update scores

            final List<Integer> rundenErgebnis = regeln.berechneRundenSieger(playerAChoice, playerBChoice);
            playerAScore += rundenErgebnis.get(0);
            playerBScore += rundenErgebnis.get(1);
            roundsPlayed++;

            // publish scores to both players
            schnittstelle.zeileAusgeben(formatierer.formatieren("Round %d, Player A: %d, Player B: %d", roundsPlayed,playerAScore,playerBScore));
        }

        // announce final results to both players
        switch (regeln.berechneGewinner(playerAScore, playerBScore)) {

            case TIE:
                schnittstelle.zeileAusgeben("Tie");
                break;
            case PLAYER_A_HAT_GEWONNEN:
                schnittstelle.zeileAusgeben("Player A wins");
                break;
            case PLAYER_B_HAT_GEWONNEN:
                schnittstelle.zeileAusgeben("Player B wins");
                break;
        }


    }

}