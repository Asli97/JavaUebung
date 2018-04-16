package edu.hm.arar.aufgabe3.Formatieren;

import edu.hm.arar.aufgabe3.Formatieren.Formatierer;

/**
 * Created by Sümeyye on 11.04.2018.
 */
public class StringFormatierer implements Formatierer {
    @Override
    public String formatieren(String string, Object... args) {

        return String.format(string,args);
    }

}
