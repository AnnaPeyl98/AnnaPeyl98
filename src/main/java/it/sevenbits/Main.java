package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.FormatterException;
import it.sevenbits.formatter.formatter.IFormatter;
import it.sevenbits.formatter.formatter.implementation.LexerFormatter;
import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.reader.implementation.FileReader;
import it.sevenbits.formatter.writer.IWriter;
import it.sevenbits.formatter.writer.implementation.FileWriter;
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
   public static void main(final String[] args) throws FormatterException {
        try {
            IReader testTwo = new FileReader(args[0]);

            IWriter stringWriterForTestTwo = new FileWriter(args[1]);

            IFormatter bf = new LexerFormatter();


           System.out.println("String two after Formatter:");
            bf.format(testTwo, stringWriterForTestTwo);
            System.out.println(stringWriterForTestTwo.toString());



        } catch (ReaderException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
   }
}
