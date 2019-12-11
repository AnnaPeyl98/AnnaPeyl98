package it.sevenbits.formatterproject.reader;


/**
 * Interface for reading string for formatting
 */
public interface IReader extends AutoCloseable {
    /**
     * Check stream has next symbol after current
     *
     * @return true if has else false
     */
    boolean hasNext();

    /**
     * Read symbol from stream
     *
     * @return current symbol from stream
     * @throws ReaderException if it was some problems with stream
     */
    char read() throws ReaderException;

    /**
     * Method close stream
     * @throws ReaderException if was trouble with close stream
     */

    void close() throws ReaderException;
}
