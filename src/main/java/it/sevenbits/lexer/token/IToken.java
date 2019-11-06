package it.sevenbits.lexer.token;

/**
 * Interface for lexical analyzer's tokens
 */
public interface IToken {
    /**
     * Return name token
     * @return name token
     */
    String getName();

    /**
     * return content token
     * @return lexeme token
     */
    String getLexeme();
}
