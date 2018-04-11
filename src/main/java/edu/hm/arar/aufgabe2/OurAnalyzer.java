package edu.hm.arar.aufgabe2;

/**
 * Created by Sümeyye on 08.04.2018.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OurAnalyzer implements ComplexityAnalyzer {

    private Path rootDir=Paths.get(System.getProperty("user.dir"));

    @Override
    public ComplexityAnalyzer setRootdir(Path rootdir) throws IOException {
        if (!rootdir.toFile().exists() || !rootdir.toFile().isDirectory()) {
            throw new IOException();
        }
        this.rootDir = rootdir;
        return this;
    }

    @Override
    public Map<String, Integer> analyzeClassfiles() throws IOException {
        Map<String,Integer> result = new HashMap<>();
        Files.walk(this.rootDir).filter(f->f.toString().endsWith(".class")).forEach(p->result.put(p.toString(), this.calculateComplexity(p)));
        return result;
    }

    //hier wird die eigentliche Arbeit gemacht
    private int calculateComplexity(Path path) {
        int complexity =0;

        try {
            final String disassembledCode=this.runProgram("javap","-c",path.toString());
            System.out.println(disassembledCode);

            //mit regulären Ausdrücken Patterns erstellen und dann findet der Matcher die Matches
            String test = ": if[le|eq|ge|gt|lt|ne|nonnull|null|_acmpeq|_acmpne|_icmpeq|_icmpge|_icmpgt|_icmple|_icmplt|_icmpne]|Code:|\\d.*\\d.*\\d.*Class";
            Pattern pattern = Pattern.compile(test);
            Matcher matcher = pattern.matcher(disassembledCode);


            while(matcher.find()){
                System.out.println(matcher.group());
                complexity++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return complexity;
    }

    /**
     * Startet ein anderes Programm und liefert dessen Konsolenausgabe (out und err) zurueck.
     * @param command Programmname und Kommandozeilenargumente.
     * @return Ausgabe des Programms.
     * @exception IOException bei einem Fehler im Filesystem.
     * @exception InterruptedException bei einer Unterbrechung des Prozesses.
     */
    private String runProgram(String... command) throws IOException, InterruptedException {
        final Process process = new ProcessBuilder(command)
                .redirectErrorStream(true)
                .start();
        final List<String> output = new ArrayList<>();
        try(InputStream inputStream = process.getInputStream();
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            final Thread collector = new Thread(() -> bufferedReader.lines().forEach(output::add));
            collector.start();
            if(process.waitFor() != 0)
                throw new IOException("process failed");
            collector.join();
        }
        return output.stream().collect(Collectors.joining("\n"));
    }


    public static void main(String... args) {
        // set directory
        OurAnalyzer analyzer = new OurAnalyzer();
        Path path = Paths.get("C:\\Users\\Thomas\\workspace\\Softwarearchitektur\\cc02");
        try {
            analyzer.setRootdir(path).analyzeClassfiles();
            Map<String, Integer> result = analyzer.setRootdir(path).analyzeClassfiles();
            for(String s: result.keySet()){
                System.out.println(s+" => "+result.get(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

