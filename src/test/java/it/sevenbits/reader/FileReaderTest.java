package it.sevenbits.reader;

import it.sevenbits.reader.implementation.FileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class FileReaderTest {
    private FileReader fileReader;

    @Before
    public void init() {
        fileReader = mock(FileReader.class);
    }

    @Test
    public void testRead() throws ReaderException {
        when(fileReader.read()).thenReturn('a');
        assertEquals('a', fileReader.read());
    }

    @Test
    public void testHasNextTrue() throws ReaderException {
        when(fileReader.hasNext()).thenReturn(true);
        assertEquals(true, fileReader.hasNext());
    }

    @Test
    public void testHasNextFalse() throws ReaderException {
        when(fileReader.hasNext()).thenReturn(false);
        assertEquals(false, fileReader.hasNext());
    }

    @Test(expected = ReaderException.class)
    public void testException() throws ReaderException {
        doThrow(ReaderException.class).when(fileReader).read();
        fileReader.read();
    }

    @Test(expected = ReaderException.class)
    public void testNullString() throws ReaderException {
        fileReader = new FileReader(null);
    }
}
