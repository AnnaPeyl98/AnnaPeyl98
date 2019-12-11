package it.sevenbits.formatterproject.writer.implementation;

import it.sevenbits.formatterproject.writer.IWriter;
import it.sevenbits.formatterproject.writer.WriterException;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Class for writing formatted strings in file
 */
public class FileWriter implements IWriter {
    private Writer writer;

    private FileWriter() {
    }

    ;

    /**
     * Constructor which create stream for writing in file
     * @param path path file for writing
     * @throws WriterException if was trouble with writing
     */
    public FileWriter(final String path) throws WriterException {
        try {
            writer = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new WriterException("Cannot create file", ex);
        }

    }

    /**
     * write symbol in file
     * @param c - the symbol
     * @throws WriterException if was trouble with writing
     */
    @Override
    public void write(final char c) throws WriterException {
        try {
            writer.write(c);
        } catch (IOException ex) {
            throw new WriterException("Cannot write into stream", ex);
        }
    }

    /**
     * close stream
     * @throws WriterException if was trouble with stream
     */
    @Override
    public void close() throws WriterException {
        try {
            writer.close();
        } catch (IOException ex) {
            throw new WriterException("Cannot close stream", ex);
        }
    }
}
