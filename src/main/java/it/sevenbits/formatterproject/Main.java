package it.sevenbits.formatterproject;

import it.sevenbits.formatterproject.formatter.FormatterException;
import it.sevenbits.formatterproject.formatter.IFormatter;
import it.sevenbits.formatterproject.formatter.implementation.LexerFormatter;
import it.sevenbits.formatterproject.reader.IReader;
import it.sevenbits.formatterproject.reader.ReaderException;
import it.sevenbits.formatterproject.reader.implementation.FileReader;
import it.sevenbits.formatterproject.writer.IWriter;
import it.sevenbits.formatterproject.writer.WriterException;
import it.sevenbits.formatterproject.writer.implementation.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for running formatter
 */
public final class Main {
    final static Logger logger = LoggerFactory.getLogger(Main.class);

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
            IReader testTwo = new FileReader(args[0]);

            IWriter stringWriterForTestTwo = new FileWriter(args[1]);

            IFormatter bf = new LexerFormatter();

            logger.info("Formatter formatted code.");
            bf.format(testTwo, stringWriterForTestTwo);


        } catch (ReaderException e) {
            logger.error(e.toString());
        } catch (WriterException e) {
            logger.error(e.toString());
        } catch (FormatterException e) {
            logger.error(e.toString());
        }
    }
}
