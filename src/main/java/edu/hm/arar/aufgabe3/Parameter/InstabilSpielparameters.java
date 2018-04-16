package edu.hm.arar.aufgabe3.Parameter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sümeyye on 16.04.2018.
 */
public class InstabilSpielparameters implements Parameter {

    private final int scoreToWin = 42;
    private final List<List<Integer>> choices =
            Arrays.asList(Arrays.asList(1,2,3,4), Arrays.asList(2,3,4,5), Arrays.asList(1,3,5));



    @Override
    public List<Integer> getChoices(int spieler, int runde) {
        int runde2 = runde % 3;
        int choice_spieler2 = (runde2 > 1) ? 0 : runde2 + 1;
        return (spieler == 1) ? choices.get(runde2) : choices.get(choice_spieler2);
    }

    @Override
    public int getScoreToWin() {
        return scoreToWin;
    }

}