package it.sevenbits.lexer;

import it.sevenbits.token.IToken;

/**
 * Interface for classes that makes tokens
 */
public interface ILexer {
    /**
     * Read next token
     * @return IToken
     * @throws LexerException if was trouble with some
     */
    IToken readToken() throws LexerException;

    /**
     * Check can read next token
     * @return true if can else false
     */
    boolean hasMoreToken();

}
