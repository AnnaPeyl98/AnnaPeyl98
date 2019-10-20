package it.sevenbits.formatter;

import it.sevenbits.reader.StringReader;
import it.sevenbits.writer.StringWriter;

public class Formatter {
    private final int INDENT_LENGTH = 4;
    private final char LEFT_BRACE = '{';
    private final char RIGHT_BRACE = '}';
    private final char SEMICOLON = ';';
    private final char SPACE = ' ';
    private final char CHAR_NEWLINE = '\n';

    private int nestingLevel = 0;

    private void makeNewLine(StringWriter writer) {
        writer.write(CHAR_NEWLINE);
        for (int i = 0; i < nestingLevel; i++) {
            for (int j = 0; j < INDENT_LENGTH; j++) {
                writer.write(SPACE);
            }
        }
    }


    public void format(StringReader reader, StringWriter writer) {
        boolean previousIterationResultForNewLine = true;
        boolean iterationResultForNewLine = false;
        char symbol = 0;
        boolean needNewLine = false;
        nestingLevel=0;
        while (reader.hasNext()) {
            symbol = reader.read();

            if (symbol == LEFT_BRACE) {
                nestingLevel++;
                writer.write(SPACE);
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