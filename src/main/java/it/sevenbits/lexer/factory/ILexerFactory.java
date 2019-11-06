package it.sevenbits.lexer.factory;

import it.sevenbits.lexer.ILexer;
import it.sevenbits.reader.IReader;

/**
 * Interface for lexer factory
 */
public interface ILexerFactory {
    /**
     * Created new Lexer given type
     * @param type - type lexer
     * @param reader - stream for reading
     * @return new Lexer
     */
    ILexer createLexer(String type, IReader reader);
}
