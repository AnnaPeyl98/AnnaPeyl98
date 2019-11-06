package it.sevenbits.writer;

import it.sevenbits.writer.implementation.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringWriterTest {
    private StringWriter stringWriter;

    @Before
    public void init() {
        stringWriter = new StringWriter();
    }

    @Test
    public void testWriteChar() {
        String string = "forma";
        String resultString;
        stringWriter.write('f');
        stringWriter.write('o');
        stringWriter.write('r');
        stringWriter.write('m');
        stringWriter.write('a');
        resultString = stringWriter.toString();
        assertEquals(string, resultString);
    }

    @Test
    public void testEmptyString() {
        String string = "";
        String resultString;
        resultString = stringWriter.toString();
        assertEquals(string, resultString);
    }
}
