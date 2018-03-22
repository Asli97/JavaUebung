package edu.hm.arar.s;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sümeyye on 22.03.2018.
 */
public class OurReaderTest {

    private OurReader sut;

    @Before
    public void setup() {
        sut = new OurReader();
    }

    @Test
    public void testReadCsv() throws IOException {
        String csv = "aa,bb,cc\nee,ff,gg\n";

        String[][] result = sut.read(new StringReader(csv));

        assertEquals(2, result.length);
        assertEquals("aa", result[0][0]);
        assertEquals("gg", result[1][2]);
    }

    @Test
    public void testReadCsvMitEntwerter() throws IOException {
        String csv = "aa,b\\,b,cc\nee,ff,gg\n";

        String[][] result = sut.read(new StringReader(csv));

        assertEquals(3, result[0].length);
        assertEquals("b,b", result[0][1]);
    }

    @Test
    public void testReadCsvmitnureinemnewline() throws IOException {
        String csv = "\n";

        String[][] result = sut.read(new StringReader(csv));

        assertEquals(1, result.length);
        assertEquals(1, result[0].length);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadCsvfehlerhaft () throws IOException {
        String csv = "aa,bb,cc\nee,ff,gg";
        sut.read(new StringReader(csv));
    }

    @Test
    public void testReadCsvzweiteZeileLeer() throws IOException {
        String csv = "Hello\n\n";
        String[][] result = sut.read(new StringReader(csv));

        assertEquals(2,result.length);
        assertEquals(1,result[1].length);
    }
}
