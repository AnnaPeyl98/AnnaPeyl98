package it.sevenbits.reader;

public class StringReader {
    private String string;
    private int position;

    private StringReader() {
    }

    public StringReader(final String string){
        this.string = string;
        position = 0;
    }

    public boolean hasNext() {
        return position < string.length();
    }
    public char read(){
        return string.charAt(position++);

    }
}
