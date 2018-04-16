package edu.hm.arar.aufgabe3.Parameter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sümeyye on 16.04.2018.
 */
public class KurzSpielparameters implements Parameter {

    private final int scoreToWin = 12;

    private final List<Integer> choices = Arrays.asList(1,2,3);

    @Override
    public List<Integer> getChoices(int spieler, int runde) {
        return choices;
    }

    @Override
    public int getScoreToWin() {
        return scoreToWin;
    }
}
