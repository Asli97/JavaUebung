package edu.hm.arar.aufgabe3;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class Spielparameters implements Parameters {

    private final int scoreToWin = 40;
    private final int maxChoice = 5;


    @Override
    public int getScoreToWin() {
        return scoreToWin;
    }

    @Override
    public int getMaxChoice() {
        return maxChoice;
    }
}
