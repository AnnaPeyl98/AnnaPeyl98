package it.sevenbits.reader.implementation;

import it.sevenbits.reader.IReader;
import it.sevenbits.reader.ReaderException;

/**
 * Class for reading unformatted strings
 */
public class StringReader implements IReader {
    private String string;
    private int position;

    /**
     * That could not call the constructor without parameters
     */
    private StringReader() {
    }

    /**
     * Constructor that copies link on string
     *
     * @param string source string that will be copied
     * @throws ReaderException if source is null
     */
    public StringReader(final String string) throws ReaderException {
        if (string == null) {
            throw new ReaderException("String cannot be null");
        }
        this.string = string;
        position = 0;
    }

    /**
     * Сheck if string has symbol after current position
     *
     * @return true if string has symbol, else false
     */
    public boolean hasNext() {
        return position < string.length();
    }

    /**
     * Read symbol in current position and increase position if string has more characters
     *
     * @return symbol in current position
     * @throws ReaderException if stream is empty
     */
    public char read() throws ReaderException {
        if (hasNext()) {
            return string.charAt(position++);
        }
        throw new ReaderException("Stream is empty");
    }
}
