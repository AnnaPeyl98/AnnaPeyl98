package it.sevenbits.formatterproject;

import it.sevenbits.formatterproject.formatter.FormatterException;
import it.sevenbits.formatterproject.formatter.IFormatter;
import it.sevenbits.formatterproject.formatter.implementation.StateMachineFormatter;
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
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

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
            IReader reader = new FileReader(args[0]);

            IWriter writer = new FileWriter(args[1]);

            IFormatter bf = new StateMachineFormatter();

            LOGGER.info("Formatter formatted code.");
            bf.format(reader, writer);
            writer.close();
            reader.close();

        } catch (ReaderException e) {
            LOGGER.error(e.toString());
        } catch (WriterException e) {
            LOGGER.error(e.toString());
        } catch (FormatterException e) {
            LOGGER.error(e.toString());
        }
    }
}
