package it.sevenbits.formatterproject.writer;

import it.sevenbits.formatterproject.reader.implementation.FileReader;
import it.sevenbits.formatterproject.writer.implementation.FileWriter;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWriterTest {
    private FileWriter fileWriter;
    private FileReader fileReader;

    @Before
    public void init() {
        fileWriter = mock(FileWriter.class);
        fileReader = mock(FileReader.class);
    }

    @Test
    public void testWriteChar() throws WriterException{
        doAnswer(invocation -> {
            when(fileReader.read()).thenReturn('f');
            assertEquals('f', fileReader.read());
            return null;
        }).when(fileWriter).write('f');
        fileWriter.write('f');
    }

}
