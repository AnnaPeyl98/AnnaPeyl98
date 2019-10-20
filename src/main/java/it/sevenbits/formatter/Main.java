package it.sevenbits;

import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.StringReader;
import it.sevenbits.formatter.Formatter;
import it.sevenbits.writer.implementation.StringWriter;
import it.sevenbits.writer.WriterException;

/**
 * Main class for running formatter
 */
public class Main {
    /**
     * Method that run formatter
     *
     * @param args args console arguments
     */
    public static void main(final String[] args) {
        try {
            StringReader testTwo = new StringReader("public class HelloWorld{public static void main(String[] args){"
                    + "System.out.println(\"Hello, World from branch2\");" + "}}");
            StringReader testThree = new StringReader("{{{}");
            StringReader testFour = new StringReader("{{{           {}}}}");

            StringWriter stringWriterForTestTwo = new StringWriter();
            StringWriter stringWriterForTestThree = new StringWriter();
            StringWriter stringWriterForTestFour = new StringWriter();

            Formatter bf = new Formatter();

            System.out.println("String two after Formatter:");
            bf.format(testTwo, stringWriterForTestTwo);
            System.out.println(stringWriterForTestTwo.toString());

            System.out.println("String three after Formatter:");
            bf.format(testThree, stringWriterForTestThree);
            System.out.println(stringWriterForTestThree.toString());

            System.out.println("String four after Formatter:");
            bf.format(testFour, stringWriterForTestFour);
            System.out.println(stringWriterForTestFour.toString());
        } catch (ReaderException | WriterException e) {
            e.printStackTrace();
        }
    }
}
