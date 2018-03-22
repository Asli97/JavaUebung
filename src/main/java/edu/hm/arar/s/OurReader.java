package edu.hm.arar.s;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Sümeyye on 21.03.2018.
 */
public class OurReader implements CSVReader {

    public String[][] read(Reader reader) throws IOException, IllegalArgumentException {
        boolean isEscape = false;
        String[][] result = new String[0][]; // Ergebnis
        String[] currentLine = new String[0]; // Die aktuelle zeile, in dem wir die woerter speichern bis \n
        StringBuilder temporaryWord = new StringBuilder(); // zwischenspeicherung des aktuell gelesenen wordes

        int tempChar;
        while((tempChar = reader.read()) != -1) {
            char currentChar = (char) tempChar;
            // Der Fall, wenn ein , kommt
            if( currentChar == '\\' && !isEscape){
                isEscape = true;
            } else if (currentChar == ',' && !isEscape) {
                currentLine = finishWord(currentLine, temporaryWord.toString()); // aktuelle zeile neu setzen
                temporaryWord = new StringBuilder(); // tmp word wieder leeren für das neue Word
            } else if (currentChar == '\n' && !isEscape) {
                currentLine = finishWord(currentLine, temporaryWord.toString()); // aktuelle zeile neu setzen
                temporaryWord = new StringBuilder(); // tmp word wieder leeren für das neue Word
                String[][] tempResult = new String[result.length + 1][];
                System.arraycopy(result, 0, tempResult, 0, result.length);
                tempResult[tempResult.length - 1] = currentLine;
                result = tempResult;
                currentLine = new String[0];
            } else {
                temporaryWord.append(currentChar);
                isEscape = false;
            }
        }

        if(currentLine.length > 0){
            throw new IllegalArgumentException();
        }
        return result;
    }

    private String[] finishWord(String[] currentLine, String currentWord) {
        String[] tmpLine = new String[currentLine.length + 1]; // hier machen wir Platz fuer das neue Word
        System.arraycopy(currentLine, 0, tmpLine, 0, currentLine.length); // das vorhandene Array ubernehmen
        tmpLine[tmpLine.length - 1] = currentWord; // neues word hinzufugen
        return tmpLine;
    }

}
