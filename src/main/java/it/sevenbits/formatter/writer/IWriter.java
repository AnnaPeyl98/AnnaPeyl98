package it.sevenbits.formatter.writer;

/**
 *Interface for writing formatting code into stream
 */
public interface IWriter {
    /**
     *Write symbol into stream
     * @param c - the symbol
     * @throws WriterException if it has some problems with stream
     */
    void write(char c) throws WriterException;
}
