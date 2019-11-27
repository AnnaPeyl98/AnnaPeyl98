package it.sevenbits.formatter.statemachine;

import it.sevenbits.token.IToken;
import it.sevenbits.writer.IWriter;

import java.util.Objects;

/**
 * Class for save writer and token for working in commands
 */
public class FormatterContext {

    private IWriter writer;
    private IToken token;
    private int indent;
    private static final int INDENT_SIZE = 4;

    /**
     * Constructor.
     * Puts indent =0;
     */
    public FormatterContext() {
        indent = 0;
    }

    /**
     * getter for writer
     *
     * @return writer
     */
    public IWriter getWriter() {
        return writer;
    }

    /**
     * setter for writer
     *
     * @param writer when will be write token
     */
    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    /**
     * getter token
     *
     * @return token
     */
    public IToken getToken() {
        return token;
    }

    /**
     * setter for token
     *
     * @param token current token
     */
    public void setToken(final IToken token) {
        this.token = token;
    }

    /**
     * getter indent
     *
     * @return indent
     */
    public int getIndent() {
        return indent;
    }

    /**
     * setter indent
     *
     * @param indent current indent
     */
    public void setIndent(final int indent) {
        if (indent < 0) {
            this.indent = 0;
        } else {
            this.indent = indent;
        }
    }

    /**
     * getter for indent size
     *
     * @return INDENT_SIZE
     */
    public int getIntendSize() {
        return INDENT_SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FormatterContext that = (FormatterContext) o;
        return indent == that.indent &&
                INDENT_SIZE == that.INDENT_SIZE &&
                Objects.equals(writer, that.writer) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, token, indent, INDENT_SIZE);
    }
}
