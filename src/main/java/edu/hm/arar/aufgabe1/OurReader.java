/* Organisation: Hochschule Muenchen, Fakultaet 07
 * Project: Praktikum Softwarearchitektur, SS 2018
 * Author: Suemeyye Arar, arar@hm.edu; Thomas Willeit, twilleit@hm.edu
 * Date: 2018-03-22
 * Purpose: Loesung von Aufgabe 1.0 "CSVReader"
 * Software: Oracle Java SE JDK 1.8.0,
 * Hardware: intel(R) Core(TM) i5-6200U CPU
 */
package edu.hm.arar.aufgabe1;

import edu.hm.cs.rs.arch18.a01_kiss.CSVReader;

import java.io.IOException;
import java.io.Reader;

/**
 * Liest CSV Datein.
 * @author Suemeyye Arar, arar@hm.edu
 * @author Thomas Willeit, twilleit@hm.edu
 * @version 2018-03-22
 */
public class OurReader implements CSVReader {


    @Override
    public String[][] read(Reader reader) throws IOException, IllegalArgumentException {
        // wir lesen alles in text ein.
        final StringBuilder text = new StringBuilder();
        int symbol = reader.read();
        while(symbol != -1) {
            text.append((char)symbol);
            symbol = reader.read();
        }

        // Checken ob letztes Zeiehchen ein \n
        if(text.charAt(text.length() - 1) != '\n' ) {

        }

        return parseText(text.toString());
    }

    /**
     * Psreses the text.
     * @param text ist der ganze text.
     * @return das result.
     */

    private String[][] parseText(String text) {
        String[][] result = new String[0][];
        boolean isEscape = false;
        String[] currentLine = new String[0]; // Die aktuelle zeile, in dem wir die woerter speichern bis \n
        StringBuilder word = new StringBuilder(); // zwischenspeicherung des aktuell gelesenen wordes
        for (char currentChar : text.toCharArray()) { // durch jeden Buchstaben laufen
            // Der Fall, wenn ein , kommt
            if (currentChar == '\\' && !isEscape) {
                isEscape = true; // esqape Variable setzen
            } else if (currentChar == ',' && !isEscape) {
                currentLine = finishWord(currentLine, word.toString(), false); // aktuelle zeile neu setzen
                word = new StringBuilder(); // tmp word wieder leeren fuer das neue Word
            } else if (currentChar == '\n' && !isEscape) {
                currentLine = finishWord(currentLine, word.toString(), true); // aktuelle zeile neu setzen
                word = new StringBuilder(); // tmp word wieder leeren fuer das neue Word
                final String[][] newResult = new String[result.length + 1][];
                System.arraycopy(result, 0, newResult, 0, result.length);
                newResult[newResult.length - 1] = currentLine;
                result = newResult;
                currentLine = new String[0];
            } else {
                word.append(currentChar);
                isEscape = false;
            }

        }
        checkConditions(result, isEscape, currentLine, word);
        return result;
    }

    /***
     * checkt alles halt a mal.
     * @param result das ergebnis.
     * @param isEscape ob gerade esqaped wird.
     * @param currentLine die aktuelle zeile.
     * @param word das aktuell wort.
     */
    private void checkConditions(String[][] result, boolean isEscape, String[] currentLine, StringBuilder word) {
        if(result.length == 0 || currentLine.length > 0 || word.length() > 0 || isEscape){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Baut das neue Word zusammen.
     * @param currentLine aktuelle Zeile.
     * @param currentWord aktuelles Word.
     * @param newline neue zeile.
     * @return ein Array mit @spaceLine dem neuen Word.
     */
    private String[] finishWord(String[] currentLine, String currentWord, boolean newline) {
        String[] spaceLine = currentLine;
        if (!newline || currentLine.length > 0 || currentWord.length() > 0) {
            spaceLine = new String[currentLine.length + 1]; // hier machen wir Platz fuer das neue Word
            System.arraycopy(currentLine, 0, spaceLine, 0, currentLine.length); // das vorhandene Array ubernehmen
            spaceLine[spaceLine.length - 1] = currentWord; // neues word hinzufugen
        }

        return spaceLine;
    }

}
