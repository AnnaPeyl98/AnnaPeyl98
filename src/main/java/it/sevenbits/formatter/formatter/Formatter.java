package it.sevenbits.formatter;

import it.sevenbits.reader.IReader;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.writer.IWriter;
import it.sevenbits.writer.WriterException;


/**
 * Class that formats code by code style rules
 */
public class Formatter {
    private final int INDENT_LENGTH = 4;
    private final char LEFT_BRACE = '{';
    private final char RIGHT_BRACE = '}';
    private final char SEMICOLON = ';';
    private final char SPACE = ' ';
    private final char CHAR_NEWLINE = '\n';

    private int nestingLevel = 0;

    /**
     * Translates to a new line and makes four spaces according to the nesting level
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
     *
     * @param reader - instance that contains code for formatting
     * @param writer - instance where we would write formatting code
     * @throws ReaderException if was trouble with read
     * @throws WriterException if was trouble with recording
     */
    public void format(final IReader reader, final IWriter writer) throws ReaderException, WriterException {
        boolean previousIterationResultForNewLine = true;
        boolean iterationResultForNewLine = false;
        char symbol = 0;
        boolean needNewLine = false;
        nestingLevel = 0;
        while (reader.hasNext()) {
            symbol = reader.read();
            while (symbol == SPACE) {
                symbol = reader.read();
            }

            if (symbol == LEFT_BRACE) {
                nestingLevel++;
                writer.write(LEFT_BRACE);
                makeNewLine(writer);
                iterationResultForNewLine = true;
            }
            if (symbol == SEMICOLON) {
                writer.write(SEMICOLON);
                iterationResultForNewLine = true;
                needNewLine = true;

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
            }
            previousIterationResultForNewLine = iterationResultForNewLine;
            iterationResultForNewLine = false;
        }

    }
}