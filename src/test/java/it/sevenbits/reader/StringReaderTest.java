package it.sevenbits.reader;

import it.sevenbits.reader.implementation.StringReader;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringReaderTest {
    private StringReader stringReader;

    @Before
    public void init() {
    }

    @Test
    public void testRead() throws ReaderException {
        stringReader = new StringReader("abcd");
        assertEquals('a', stringReader.read());
    }

    @Test
    public void testHasNextTrue() throws ReaderException {
        stringReader = new StringReader("abcd");
        assertEquals(true, stringReader.hasNext());
    }

    @Test
    public void testHasNextFalse() throws ReaderException {
        stringReader = new StringReader("");
        assertEquals(false, stringReader.hasNext());
    }

    @Test(expected = ReaderException.class)
    public void testException() throws ReaderException {
        stringReader = new StringReader("");
        stringReader.read();
    }

    @Test(expected = ReaderException.class)
    public void testNullString() throws ReaderException {
        stringReader = new StringReader(null);
    }
}
