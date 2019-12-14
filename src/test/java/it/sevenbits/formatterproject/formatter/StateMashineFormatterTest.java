package it.sevenbits.formatterproject.formatter;

import it.sevenbits.formatterproject.formatter.implementation.StateMachineFormatter;
import it.sevenbits.formatterproject.reader.IReader;
import it.sevenbits.formatterproject.reader.ReaderException;
import it.sevenbits.formatterproject.reader.implementation.StringReader;
import it.sevenbits.formatterproject.writer.IWriter;
import it.sevenbits.formatterproject.writer.implementation.StringWriter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StateMashineFormatterTest {
        private IReader stringReader;
        private IWriter stringWriter;
        private IFormatter formatter;

        @Before
        public void init() {
            formatter = new StateMachineFormatter();
            stringWriter = new StringWriter();
        }

        @Test
        public void testOnlyBraces() throws ReaderException, FormatterException {
            String result = "{\n" +
                    "    {\n" +
                    "        {\n" +
                    "            {\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            stringReader = new StringReader("{{{{ }}}}");
            formatter.format(stringReader, stringWriter);
            assertEquals(result, stringWriter.toString());
        }

        @Test
        public void testWithUnpairedBraces() throws ReaderException, FormatterException {
            String result = "{\n" +
                    "    {\n" +
                    "        {\n" +
                    "            {\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }";
            stringReader = new StringReader("{{{{}}}");
            formatter.format(stringReader, stringWriter);
            assertEquals(result, stringWriter.toString());
        }

        @Test
        public void testWithValidCodeHelloWord() throws ReaderException, FormatterException {
            String result = "public class HelloWorld {\n" +
                    "    //hiiiiii\n"+
                    "    public static void main(String[] args) {\n" +
                    "        /*comment two*/\n"+
                    "        String s=\"sss\";\n"+
                    "        System.out.println(\"   Hello, World from branch2\");\n" +
                    "    }\n" +
                    "}";
            stringReader = new StringReader("public class HelloWorld      {//hiiiiii\npublic static void main(String[] args){ /*comment two*/"
                    + "String s=\"sss\"; System.out.println(\"   Hello, World from branch2\");" + "}}");
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
