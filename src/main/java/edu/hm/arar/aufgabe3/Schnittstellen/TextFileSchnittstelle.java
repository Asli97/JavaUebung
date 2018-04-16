package edu.hm.arar.aufgabe3.Schnittstellen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Sümeyye on 16.04.2018.
 */
public class TextFileSchnittstelle implements Schnittstelle {

    private final BufferedReader input;
    private final BufferedWriter output;

    public TextFileSchnittstelle() throws IOException {
         this.input = Files.newBufferedReader(Paths.get("c:\\undercut.in.txt"));
         this.output = Files.newBufferedWriter(Paths.get("c:\\undercut.out.txt"), StandardOpenOption.CREATE);

    }

    @Override
    public int einlesen() throws IOException {
        return Integer.parseInt(input.readLine());
    }

    @Override
    public void zeileAusgeben(String ausgabeString) throws IOException {
        output.write(ausgabeString);
    }

    @Override
    public boolean istEingabeKorrekt(int input) {
        return false;
    }
}
