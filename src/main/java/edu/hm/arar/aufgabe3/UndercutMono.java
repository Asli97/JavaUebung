package edu.hm.arar.aufgabe3;

/**
 * Created by Sümeyye on 11.04.2018.
 */

/* (C) 2018, R. Schiedermeier, rs@cs.hm.edu
 * Java 1.8.0_121, Linux x86_64 4.15.4
 * bluna (Intel Core i7-5600U CPU/2.60GHz, 4 cores, 2003 MHz, 16000 MByte RAM)
 **/
import java.io.IOException;

/**
 * Monolithic version of Undercut. Violates lots of design principles.
 * @author R. Schiedermeier, rs@cs.hm.edu
 * @version 2018-03-30
 * @see <a href="http://www.ams.org/publicoutreach/msamhome/96-undercut-index.html">Undercut</a>
 */
public class UndercutMono {
    /**
     * Entry point.
     * @param args Commandline args: none.
     * @exception IOException on incomplete input.
     */
    public static void main(String... args) throws IOException {
        final Parameters param = new Spielparameters();
        final Schnittstelle schnittstelle = new Dialogschnittstelle();
        final Formatierer formatierer = new StringFormatierer();
        final Regeln regeln = new Spielregeln();

        new UndercutMono().play(param,schnittstelle,formatierer,regeln);
    }

    /**
     * Runs an Undercut game.
     * Gets input from System.in, prints output to System.out.
     * @exception IOException on incomplete input.
     */
    public void play(Parameters param, Schnittstelle schnittstelle, Formatierer formatierer, Regeln regeln) throws IOException {
        final int scoreToWin = param.getScoreToWin();
        final int maxChoice = param.getMaxChoice();

        int playerAScore = 0;
        int playerBScore = 0;
        int roundsPlayed = 0;

        schnittstelle.zeileAusgeben("Undercut start");
        // loop until a player wins ...
        while(playerAScore < scoreToWin && playerBScore < scoreToWin) {
            int playerAChoice;
            // read players' choices; if invalid, discard and retry
            schnittstelle.zeileAusgeben(formatierer.formatieren("Player A, your choice (1-%d)?", maxChoice));
            do {
                final int input = schnittstelle.einlesen();
                if(regeln.istEingabeKorrekt(input))
                    throw new IOException(); // bomb out on end of input
                playerAChoice = input - '0';
            }
            while(playerAChoice < 1 || playerAChoice > maxChoice);

            int playerBChoice;
            schnittstelle.zeileAusgeben(formatierer.formatieren("Player B, your choice (1-%d)?", maxChoice));
            do {
                final int input = schnittstelle.einlesen();
                if(regeln.istEingabeKorrekt(input))
                    throw new IOException();
                playerBChoice = input - '0';
            }
            while(playerBChoice < 1 || playerBChoice > maxChoice);

            // update scores

            switch (regeln.berechneRundenSieger(playerAChoice,playerBChoice)){

                case PLAYER_A_BEKOMMT_PUNKTE:
                    playerAScore += playerAChoice + playerBChoice;
                    break;
                case PLAYER_B_BEKOMMT_PUNKTE:
                    playerBScore += playerAChoice + playerBChoice;
                    break;
                case BEIDE_BEKOMMEN_PUNKTE:
                    playerAScore += playerAChoice;
                    playerBScore += playerBChoice;
                    break;
            }
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