package it.sevenbits;

import it.sevenbits.reader.StringReader;
import it.sevenbits.formatter.Formatter;
import it.sevenbits.writer.StringWriter;

public class Main {
    public static void main(String[] args) {
        StringReader testOne = new StringReader("while (it.sevenbits.formatter.reader.hasNext()){currentSymbol = it.sevenbits.formatter.reader.read();if (currentSymbol == LCBRACE) {indentLevel++;it.sevenbits.formatter.writer.write(SPACE);"+
                "it.sevenbits.formatter.writer.write(LCBRACE);makeNewLine(it.sevenbits.formatter.writer);needWrite=false;}if (currentSymbol == SEMICOLON) {it.sevenbits.formatter.writer.write(SEMICOLON);needWrite=false;significantNow=true;}"+
                "if (currentSymbol == RCBRACE) {indentLevel--;makeNewLine(it.sevenbits.formatter.writer);it.sevenbits.formatter.writer.write(RCBRACE);needWrite=false;significantNow=true;}if (!significantNow) {if (needNewLine)"+
                " {makeNewLine(it.sevenbits.formatter.writer);needNewLine = false;}if(needWrite) {if(significantBefore){makeNewLine(it.sevenbits.formatter.writer);}it.sevenbits.formatter.writer.write(currentSymbol);}}needWrite = true;significantBefore"+
                " = significantNow;significantNow = false;}");
        StringReader testTwo = new StringReader("public class HelloWorld{public static void main(String[] args){" + "System.out.println(\"Hello, World from branch2\");" + "}}");
        StringReader testThree = new StringReader("{{{}");
        StringReader testFour = new StringReader("{{{{}}}}");

        StringWriter stringWriterForTestOne = new StringWriter();
        StringWriter stringWriterForTestTwo = new StringWriter();
        StringWriter stringWriterForTestThree = new StringWriter();
        StringWriter stringWriterForTestFour = new StringWriter();

        Formatter bf = new Formatter();

        System.out.println("String one after Formatter:");
        bf.format(testOne,stringWriterForTestOne);
        System.out.println(stringWriterForTestOne.toString());

        System.out.println("String two after Formatter:");
        bf.format(testTwo,stringWriterForTestTwo);
        System.out.println(stringWriterForTestTwo.toString());

        System.out.println("String three after Formatter:");
        bf.format(testThree,stringWriterForTestThree);
        System.out.println(stringWriterForTestThree.toString());

        System.out.println("String four after Formatter:");
        bf.format(testFour,stringWriterForTestFour);
        System.out.println(stringWriterForTestFour.toString());
    }
}
