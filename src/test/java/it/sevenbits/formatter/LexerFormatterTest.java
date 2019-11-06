package it.sevenbits.formatter;

import it.sevenbits.formatter.implementation.LexerFormatter;
import it.sevenbits.reader.IReader;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.StringReader;
import it.sevenbits.writer.IWriter;
import it.sevenbits.writer.implementation.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LexerFormatterTest {
    private IReader stringReader;
    private IWriter stringWriter;
    private IFormatter formatter;

    @Before
    public void init() {
        formatter = new LexerFormatter();
        stringWriter = new StringWriter();
    }

    @Test
    public void testOnlyBraces() throws ReaderException, FormatterException {
        String result = "{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "                \n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        stringReader = new StringReader("{{{{}}}}");
        formatter.format(stringReader, stringWriter);
        assertEquals(result, stringWriter.toString());
    }

    @Test
    public void testWithUnpairedBraces() throws ReaderException, FormatterException {
        String result = "{\n" +
                "    {\n" +
                "        {\n" +
                "            {\n" +
                "                \n" +
                "            }\n" +
                "        }\n" +
                "    }";
        stringReader = new StringReader("{{{{}}}");
        formatter.format(stringReader, stringWriter);
        assertEquals(result, stringWriter.toString());
    }

    @Test
    public void testHelloWord() throws ReaderException, FormatterException {
        String result = "public class HelloWorld{\n" +
                "    public static void main(String[] args){\n" +
                "        System.out.println(\"Hello, World from branch2\");\n" +
                "    }\n" +
                "}";
        stringReader = new StringReader("public class HelloWorld{public static void main(String[] args){"
                + "System.out.println(\"Hello, World from branch2\");" + "}}");
        formatter.format(stringReader, stringWriter);
        assertEquals(result, stringWriter.toString());
    }

    @Test
    public void testWithExtraSpaces() throws ReaderException, FormatterException {
        String result = "{\n" +
                "    r;\n" +
                "    t;\n" +
                "}";
        stringReader = new StringReader("{r;          t;}");
        formatter.format(stringReader, stringWriter);
        assertEquals(result, stringWriter.toString());
    }

    @Test
    public void testWithoutSpaces() throws ReaderException, FormatterException {
        String result = "{\n" +
                "    r;\n" +
                "    t;\n" +
                "}";
        stringReader = new StringReader("{r;t;}");
        formatter.format(stringReader, stringWriter);
        assertEquals(result, stringWriter.toString());
    }
}
