package edu.hm.arar.aufgabe2;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by Sümeyye on 03.04.2018.
 */
public class Complexy implements ComplexityAnalyzer {

    private  Path rootDir = Paths.get(System.getProperty("user.dir"));

    public Complexy() {}

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
        Map<String, Integer> result = new HashMap<>();
        Files.walk(this.rootDir)
                .filter(path -> path.endsWith(".class"))
                .map(this::calculateComplexity)
                .forEach(entry -> result.put(entry.getKey(), entry.getValue()));
        return result;
    }

    private Map.Entry<String, Integer> calculateComplexity(Path path) throws RuntimeException  {
        try {
            Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
