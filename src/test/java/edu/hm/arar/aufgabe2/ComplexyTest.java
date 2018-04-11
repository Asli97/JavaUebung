package edu.hm.arar.aufgabe2;

import edu.hm.arar.aufgabe2.Complexy;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sümeyye on 03.04.2018.
 */
public class ComplexyTest {

    @Test
    public void testComplexy() throws IOException {
        Integer expectedComplexty = 17;

        OurAnalyzer analyzer = new OurAnalyzer();
        analyzer.setRootdir(Paths.get("./src/test/java/edu/hm/arar/aufgabe2"));
        Map<String, Integer> stringIntegerMap = analyzer.analyzeClassfiles();

        assertEquals(expectedComplexty, stringIntegerMap.get(".\\src\\test\\java\\edu\\hm\\arar\\aufgabe2\\TestClass.class"));
    }
}
