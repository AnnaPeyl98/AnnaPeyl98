package it.sevenbits.writer.implementation;

import it.sevenbits.writer.IWriter;

/**
 * Class for writing formatted strings
 */
public class StringWriter implements IWriter {
    private StringBuilder stringBuilder;

    /**
     * Constructor for create stream for formatted string
     */
    public StringWriter() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Write symbol in stream for formatted string
     *
     * @param c the symbol that will write in stream
     */
    public void write(final char c) {
        stringBuilder.append(c);
    }

    /**
     * Return string value of stream
     *
     * @return string value of stream
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}