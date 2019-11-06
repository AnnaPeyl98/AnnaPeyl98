package it.sevenbits.formatter;

import it.sevenbits.reader.IReader;
import it.sevenbits.writer.IWriter;

/**
 * Interface for classes which format code
 */
public interface IFormatter {
    /**
     * Method format code
     * @param reader stream with unformatted code
     * @param writer stream where write formatted code
     * @throws FormatterException if was trouble with reading or writing
     */
    void format(IReader reader, IWriter writer) throws FormatterException;
}
