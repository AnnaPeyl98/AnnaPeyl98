package it.sevenbits.lexer.token.implementation;

import it.sevenbits.lexer.token.IToken;

import java.util.Objects;

/**
 * Implementation of IToken
 */
public class Token implements IToken {
    private String name;
    private String lexeme;

    private Token() {
    }

    ;

    /**
     * Basic constructor copy links of name and lexeme
     *
     * @param name   name token
     * @param lexeme content token
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    /**
     * Getter for name
     *
     * @return name token
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for lexeme
     *
     * @return lexeme token
     */
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Token token = (Token) o;
        return Objects.equals(name, token.name) &&
                Objects.equals(lexeme, token.lexeme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lexeme);
    }
}
