package it.sevenbits.formatterproject.formatter.implementation.statemachine.command;

import it.sevenbits.formatterproject.writer.WriterException;

/**
 * Interface for running command
 */
public interface IFormatterCommand {
    /**
     * Method run command
     *
     * @throws WriterException if was trouble with writing
     */
    void execute() throws WriterException;
}
