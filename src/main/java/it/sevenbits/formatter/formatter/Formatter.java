package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.reader.IReader;
import it.sevenbits.formatter.reader.ReaderException;
import it.sevenbits.formatter.writer.IWriter;
import it.sevenbits.formatter.writer.WriterException;


/**
 * Class that formats code by code style rules
 */
public class Formatter {
    private static final int INDENT_LENGTH = 4;
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char SEMICOLON = ';';
    private static final char SPACE = ' ';
    private static final char CHAR_NEWLINE = '\n';

    private int nestingLevel = 0;

    /**
     * Translates to a new line and makes four spaces according to the nesting level
     *
     * @param writer - instance of IWriter
     * @throws WriterException if was trouble with recording
     */

    private void makeNewLine(final IWriter writer) throws WriterException {
        writer.write(CHAR_NEWLINE);
        for (int i = 0; i < nestingLevel; i++) {
            for (int j = 0; j < INDENT_LENGTH; j++) {
                writer.write(SPACE);
            }
        }
    }

    /**
     * @param reader - instance that contains code for formatting
     * @param writer - instance where we would write formatting code
     * @throws ReaderException if was trouble with read
     * @throws WriterException if was trouble with recording
     */
    public void format(final IReader reader, final IWriter writer) throws ReaderException, WriterException {
        boolean previousIterationResultForNewLine = true;
        boolean iterationResultForNewLine = false;
        boolean beforeSemicolon = false;
        char symbol = 0;
        boolean needNewLine = false;
        nestingLevel = 0;
        while (reader.hasNext()) {
            symbol = reader.read();
            if (previousIterationResultForNewLine && symbol == SPACE) {
                while (symbol == SPACE) {
                    symbol = reader.read();
                }
            }

            if (symbol == LEFT_BRACE) {
                if (beforeSemicolon) {
                    makeNewLine(writer);
                    beforeSemicolon = false;
                }
                nestingLevel++;
                writer.write(LEFT_BRACE);
                makeNewLine(writer);
                iterationResultForNewLine = true;
            }
            if (symbol == SEMICOLON) {
                writer.write(SEMICOLON);
                iterationResultForNewLine = true;
                needNewLine = true;
                beforeSemicolon = true;

            }
            if (symbol == RIGHT_BRACE) {
                nestingLevel--;
                makeNewLine(writer);
                writer.write(RIGHT_BRACE);
                iterationResultForNewLine = true;
                needNewLine = true;
            }
            if (!iterationResultForNewLine) {
                if (previousIterationResultForNewLine && needNewLine) {
                    makeNewLine(writer);
                    needNewLine = false;
                }
                writer.write(symbol);
                beforeSemicolon = false;
            }
            previousIterationResultForNewLine = iterationResultForNewLine;
            iterationResultForNewLine = false;
        }

    }
}