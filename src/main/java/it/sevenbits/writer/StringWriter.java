package it.sevenbits.writer;

public class StringWriter{
    private StringBuilder stringBuilder;

    public StringWriter() {
        stringBuilder = new StringBuilder();
    }

    public void write(final char c) {
        stringBuilder.append(c);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}