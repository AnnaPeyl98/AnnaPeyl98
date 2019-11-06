package it.sevenbits.formatter;

/**
 * Class for formatter exception if was some problem with him
 */
public class FormatterException extends Exception {
    /**
     * Default exception constructor
     */
    public FormatterException() {
    }

    /**
     * Exception constructor
     *
     * @param s - description message
     */
    public FormatterException(final String s) {
        super(s);
    }

    /**
     * Exception constructor
     *
     * @param throwable - previous exception
     */
    public FormatterException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * Exception constructor
     *
     * @param s         - description message
     * @param throwable - previous exception
     */
    public FormatterException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
