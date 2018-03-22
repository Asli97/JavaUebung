package edu.hm.arar.s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Sümeyye on 21.03.2018.
 */
public class OurReader implements CSVReader {

    public String[][] read(Reader reader) throws IOException, IllegalArgumentException {
        String[][] result = new String[0][];
        // hier lese ich den reader raus
        int fixValue = 10000;
        char[] buffer = new char[fixValue];
        int lettersRead = reader.read(buffer);
        // Ueberpruefe, ob das letzte Element ein \n ist
        if(buffer[lettersRead] != '\n'){
            throw new IllegalArgumentException();
        }
        if( buffer[0] == '\n'){
            result = new String[0][];
        }





        return result;
    }

}
