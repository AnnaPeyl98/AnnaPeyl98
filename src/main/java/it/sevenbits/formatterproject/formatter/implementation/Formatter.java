package it.sevenbits.formatterproject.formatter.implementation;

import it.sevenbits.formatterproject.formatter.IFormatter;
import it.sevenbits.formatterproject.formatter.FormatterException;
import it.sevenbits.formatterproject.reader.IReader;
import it.sevenbits.formatterproject.reader.ReaderException;
import it.sevenbits.formatterproject.writer.IWriter;
import it.sevenbits.formatterproject.writer.WriterException;


/**
 * Class that formats code by code style rules
 */
public class Formatter implements IFormatter {
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
     * Formats java code according to the rules
     * @param reader - instance that contains code for formatting
     * @param writer - instance where we would write formatting code
     * @throws FormatterException if was trouble with reading or writing
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        boolean previousIterationResultForNewLine = true;
        boolean iterationResultForNewLine = false;
        boolean beforeSemicolon = false;
        char symbol = 0;
        boolean needNewLine = false;
        nestingLevel = 0;
        try {
            while (reader.hasNext()) {
                symbol = reader.read();
                if (previousIterationResultForNewLine && symbol == SPACE) {
                    while (symbol == SPACE) {
                        symbol = reader.read();
                    }
                }
                switch (symbol) {
                    case (LEFT_BRACE):
                        if (beforeSemicolon) {
                            makeNewLine(writer);
                            beforeSemicolon = false;
                        }
                        nestingLevel++;
                        writer.write(LEFT_BRACE);
                        makeNewLine(writer);
                        iterationResultForNewLine = true;
                        break;

                    case (SEMICOLON):
                        writer.write(SEMICOLON);
                        iterationResultForNewLine = true;
                        needNewLine = true;
                        beforeSemicolon = true;
                        break;


                    case (RIGHT_BRACE):
                        nestingLevel--;
                        makeNewLine(writer);
                        writer.write(RIGHT_BRACE);
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
                    writer.write(symbol);
                    beforeSemicolon = false;
                }
                previousIterationResultForNewLine = iterationResultForNewLine;
                iterationResultForNewLine = false;
            }
        } catch (ReaderException ex) {
            throw new FormatterException("Some problems with reader", ex);
        } catch (WriterException ex) {
            throw new FormatterException("Some problems with writer", ex);
        }

    }
}