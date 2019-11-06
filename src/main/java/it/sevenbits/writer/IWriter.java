package it.sevenbits.writer;

/**
 * Interface for writing formatting code into stream
 */
public interface IWriter extends AutoCloseable {
    /**
     * Write symbol into stream
     *
     * @param c - the symbol
     * @throws WriterException if it has some problems with stream
     */
    void write(char c) throws WriterException;

    /**
     * Method close stream
     * @throws WriterException if was trouble with close stream
     */
    void close() throws WriterException;
}
