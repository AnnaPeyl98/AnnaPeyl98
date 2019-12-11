package it.sevenbits.formatterproject.reader;

/**
 * Class for IReader that create exception if it was some problems with reading
 */
public class ReaderException extends Exception {
    /**
     * Default exception constructor
     */
    public ReaderException() {
    }

    /**
     * Exception constructor
     *
     * @param s - description message
     */
    public ReaderException(final String s) {
        super(s);
    }

    /**
     * Exception constructor
     *
     * @param throwable - previous exception
     */
    public ReaderException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Exception constructor
     *
     * @param s         - description message
     * @param throwable - previous exception
     */
    public ReaderException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
