package it.sevenbits.formatterproject.lexer.statemachine;

import it.sevenbits.formatterproject.token.implementation.Token;

/**
 * Class for save buffer , current and next symbols in lexer, creating tokens
 */
public class LexerContext {

    private Token token;
    private char currentSymbol;
    private char nextSymbol;
    private StringBuilder sb;
    private String type;

    /**
     * Constructor created all variable is 0
     */
    public LexerContext() {
        currentSymbol = '\0';
        nextSymbol = '\0';
        token = null;
        this.sb = new StringBuilder();
    }

    /**
     * getter for current symbol
     *
     * @return current symbol
     */
    public char getCurrentSymbol() {
        return currentSymbol;
    }

    /**
     * setter for current symbol
     *
     * @param currentSymbol current symbol in lexer
     */
    public void setCurrentSymbol(final char currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    /**
     * getter fot token
     *
     * @return token
     */
    public Token getToken() {
        return token;
    }

    /**
     * setter for token
     *
     * @param token current token
     */
    public void setToken(final Token token) {
        this.token = token;
    }

    /**
     * getter for next symbol
     *
     * @return next symbol
     */
    public char getNextSymbol() {
        return nextSymbol;
    }

    /**
     * setter for next symbol
     *
     * @param nextSymbol current next symbol
     */
    public void setNextSymbol(final char nextSymbol) {
        this.nextSymbol = nextSymbol;
    }

    /**
     * set symbol in buffer
     *
     * @param symbol symbol which will be save in buffer
     */
    public void setSymbolInBuffer(final char symbol) {
        sb.append(symbol);
    }

    /**
     * getter for buffer
     *
     * @return buffer
     */
    public String getBuffer() {
        return sb.toString();
    }

    /**
     * getter for length buffer
     *
     * @return length sb
     */
    public int lengthBuffer() {
        return sb.length();
    }

    /**
     * clear sb
     */
    public void clearBuffer() {
        sb.setLength(0);
    }

    /**
     * setter for current type
     *
     * @param type current type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * getter for type
     *
     * @return type
     */
    public String getType() {
        return type;
    }


}
