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
        boolean isEscape = false;
        String[][] result = new String[0][]; // Ergebnis
        String[] currentLine = new String[0]; // Die aktuelle zeile, in dem wir die woerter speichern bis \n
        StringBuilder word = new StringBuilder(); // zwischenspeicherung des aktuell gelesenen wordes

        int symbol = reader.read();
        while(symbol != -1) {
           final char currentChar = (char) symbol;
            // Der Fall, wenn ein , kommt
            if( currentChar == '\\' && !isEscape){
                isEscape = true;
            } else if (currentChar == ',' && !isEscape) {
                currentLine = finishWord(currentLine, word.toString()); // aktuelle zeile neu setzen
                word = new StringBuilder(); // tmp word wieder leeren fuer das neue Word
            } else if (currentChar == '\n' && !isEscape) {
                currentLine = finishWord(currentLine, word.toString()); // aktuelle zeile neu setzen
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
            symbol = reader.read();
        }

        if(currentLine.length > 0){
            throw new IllegalArgumentException();
        }
        return result;
    }

    /**
     * Baut das neue Word zusammen.
     * @param currentLine aktuelle Zeile.
     * @param currentWord aktuelles Word.
     * @return ein Array mit @spaceLine dem neuen Word.
     */
    private String[] finishWord(String[] currentLine, String currentWord) {
        final String[] spaceLine = new String[currentLine.length + 1]; // hier machen wir Platz fuer das neue Word
        System.arraycopy(currentLine, 0, spaceLine, 0, currentLine.length); // das vorhandene Array ubernehmen
        spaceLine[spaceLine.length - 1] = currentWord; // neues word hinzufugen
        return spaceLine;
    }

}
