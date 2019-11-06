package it.sevenbits.lexer.implementation;

import it.sevenbits.lexer.ILexer;
import it.sevenbits.lexer.LexerException;
import it.sevenbits.lexer.token.IToken;
import it.sevenbits.lexer.token.implementation.Token;
import it.sevenbits.reader.IReader;
import it.sevenbits.reader.ReaderException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for working with token
 */
public class Lexer implements ILexer {
    private IReader reader;


    private static final char CHAR_LEFT_BRACE = '{';
    private static final char CHAR_RIGHT_BRACE = '}';
    private static final char CHAR_SEMICOLON = ';';
    private static final char SPACE = ' ';

    private static final String ELEMENT = "ELEMENT";
    private static final String LEFT_BRACE = "LEFT_BRACE";
    private static final String RIGHT_BRACE = "RIGHT_BRACE";
    private static final String SEMICOLON = "SEMICOLON";

    private char currentSymbol;
    private char previousSymbol;
    private Map<Character, IToken> mapToken;
    private IToken token;

    private boolean lastIndex = false;

    private Lexer() {
    }

    ;

    /**
     * Constructor that copies link of reader and create map tokens
     * @param reader - instance of IReader
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
        mapToken = new HashMap<>();
        mapToken.put(CHAR_LEFT_BRACE, new Token(LEFT_BRACE, "{"));
        mapToken.put(CHAR_RIGHT_BRACE, new Token(RIGHT_BRACE, "}"));
        mapToken.put(CHAR_SEMICOLON, new Token(SEMICOLON, ";"));
    }


    private IToken createToken(final StringBuilder sb) {
        IToken tokenResult = mapToken.get(previousSymbol);
        if (tokenResult != null) {
            return tokenResult;
        } else {
            return new Token(ELEMENT, sb.toString());
        }
    }

    /**
     * Read one token for formatter
     * @return next IToken
     * @throws LexerException if was trouble with reading
     */
    @Override
    public IToken readToken() throws LexerException {
        StringBuilder sb = new StringBuilder();
        try {
            while (hasMoreToken()) {
                if (currentSymbol != 0) {
                    previousSymbol = currentSymbol;
                    sb.append(currentSymbol);
                }

                if (lastIndex) {
                    lastIndex = false;
                    return createToken(sb);
                }

                currentSymbol = reader.read();
                if (currentSymbol == SPACE) {
                    while (hasMoreToken() && currentSymbol == SPACE) {
                        currentSymbol = reader.read();
                    }
                    if (mapToken.get(currentSymbol) == null) {
                        sb.append(SPACE);
                    }
                }
                if (!hasMoreToken()) {
                    lastIndex = true;
                }

                if (sb.length() > 0) {
                    return createToken(sb);
                }

                if (token != null) {
                    return createToken(sb);
                }

                token = mapToken.get(currentSymbol);

            }

        } catch (ReaderException ex) {
            throw new LexerException("Cannot read token");
        }
        return null;
    }

    /**
     * Check if lexer can read next token
     * @return true if can else false
     */
    @Override
    public boolean hasMoreToken() {
        return reader.hasNext() || lastIndex;
    }

}
