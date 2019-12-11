package it.sevenbits.formatterproject.lexer.implementation.factory;

import it.sevenbits.formatterproject.lexer.ILexer;
import it.sevenbits.formatterproject.reader.IReader;

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
