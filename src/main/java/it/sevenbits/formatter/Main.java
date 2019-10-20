package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.reader.implementation.StringReader;
import it.sevenbits.formatter.writer.implementation.StringWriter;
import it.sevenbits.formatter.reader.ReaderException;
import it.sevenbits.formatter.writer.WriterException;

/**
 * Main class for running formatter
 */
public final class Main {
    /**
     * That could not call the constructor without parameters
     */
    private Main() {
    }

    /**
     * Method that run formatter
     *
     * @param args args console arguments
     */
   public static void main(final String[] args) {
        try {
            StringReader testTwo = new StringReader("public class HelloWorld{public static void main(String[] args){"
                    + "System.out.println(\"Hello, World from branch2\");" + "}}");
            StringReader testThree = new StringReader("{r;          t;}");
            StringReader testFour = new StringReader("{{{{}}}");

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
