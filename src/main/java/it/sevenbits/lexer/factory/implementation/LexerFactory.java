package it.sevenbits.lexer.factory.implementation;

import it.sevenbits.lexer.ILexer;
import it.sevenbits.lexer.factory.ILexerFactory;
import it.sevenbits.lexer.implementation.Lexer;
import it.sevenbits.lexer.implementation.StateMachineLexer;
import it.sevenbits.reader.IReader;

/**
 * Class for creating new Lexers
 */
public class LexerFactory implements ILexerFactory {
    /**
     * Basic constructor
     */
    public LexerFactory() {
    }

    ;

    /**
     * Create new Lexer
     * @param type - type lexer
     * @param reader - stream for reading
     * @return new Lexer if type true else null
     */
    public ILexer createLexer(final String type, final IReader reader) {
        if ("STATE".equals(type)) {
            return new StateMachineLexer(reader);
        }
        if (type.equals("BASIC_LEXER")) {
            return new Lexer(reader);
        }
        return null;
    }
}
