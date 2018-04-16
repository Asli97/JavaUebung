package edu.hm.arar.aufgabe3.Parameter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class Spielparameters implements Parameter {

    private final int scoreToWin = 40;
    private final List<Integer> choices = Arrays.asList(1,2,3,4,5);


    @Override
    public List<Integer> getChoices(int spieler, int runde) {
        return choices;
    }

    @Override
    public int getScoreToWin() {
        return scoreToWin;
    }


}
