package it.sevenbits.formatter.implementation;

import it.sevenbits.formatter.IFormatter;
import it.sevenbits.formatter.FormatterException;
import it.sevenbits.lexer.ILexer;
import it.sevenbits.lexer.LexerException;
import it.sevenbits.lexer.factory.ILexerFactory;
import it.sevenbits.lexer.factory.implementation.LexerFactory;
import it.sevenbits.lexer.token.IToken;
import it.sevenbits.reader.IReader;
import it.sevenbits.writer.IWriter;
import it.sevenbits.writer.WriterException;

/**
 * Class that formats code by code style rules
 */
public class LexerFormatter implements IFormatter {
    private ILexerFactory lexerFactory;
    private static final int INDENT_LENGTH = 4;
    private static final String LEFT_BRACE = "{";
    private static final String RIGHT_BRACE = "}";
    private static final String SEMICOLON = ";";
    private static final char SPACE = ' ';
    private static final char CHAR_NEWLINE = '\n';

    private int nestingLevel = 0;

    /**
     * Basic constructor. Create factory Lexer for creating new Lexer
     */
    public LexerFormatter() {
        lexerFactory = new LexerFactory();
    }

    private void writeLexeme(final IWriter writer, final String lexeme) throws WriterException {
        for (char c : lexeme.toCharArray()) {
            writer.write(c);
        }
    }

    private void makeNewLine(final IWriter writer) throws WriterException {
        writer.write(CHAR_NEWLINE);
        for (int i = 0; i < nestingLevel; i++) {
            for (int j = 0; j < INDENT_LENGTH; j++) {
                writer.write(SPACE);
            }
        }
    }

    /**
     * Formats java code according to the rules
     * @param reader - instance that contains code for formatting
     * @param writer - instance where we would write formatting code
     * @throws FormatterException if was trouble with reading or writing
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ILexer lexer = lexerFactory.createLexer("BASIC_LEXER", reader);
        boolean previousIterationResultForNewLine = true;
        boolean iterationResultForNewLine = false;
        boolean beforeSemicolon = false;
        IToken token;
        boolean needNewLine = false;
        nestingLevel = 0;
        try {
            while (lexer.hasMoreToken()) {
                token = lexer.readToken();
                switch (token.getLexeme()) {
                    case (LEFT_BRACE):
                        if (beforeSemicolon) {
                            makeNewLine(writer);
                            beforeSemicolon = false;
                        }
                        nestingLevel++;
                        writeLexeme(writer, LEFT_BRACE);
                        makeNewLine(writer);
                        iterationResultForNewLine = true;
                        break;

                    case (SEMICOLON):
                        writeLexeme(writer, SEMICOLON);
                        iterationResultForNewLine = true;
                        needNewLine = true;
                        beforeSemicolon = true;
                        break;


                    case (RIGHT_BRACE):
                        nestingLevel--;
                        makeNewLine(writer);
                        writeLexeme(writer, RIGHT_BRACE);
                        iterationResultForNewLine = true;
                        needNewLine = true;
                        break;
                    default:
                }
                if (!iterationResultForNewLine) {
                    if (previousIterationResultForNewLine && needNewLine) {
                        makeNewLine(writer);
                        needNewLine = false;
                    }
                    writeLexeme(writer, token.getLexeme());
                    beforeSemicolon = false;
                }
                previousIterationResultForNewLine = iterationResultForNewLine;
                iterationResultForNewLine = false;
            }
            writer.close();
        } catch (LexerException ex) {
            throw new FormatterException("Some problems with reader");
        } catch (WriterException ex) {
            throw new FormatterException("Some problems with writer");
        }

    }
}
