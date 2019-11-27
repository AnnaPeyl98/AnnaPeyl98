package it.sevenbits.formatter.statemachine.command;

import it.sevenbits.writer.WriterException;

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
