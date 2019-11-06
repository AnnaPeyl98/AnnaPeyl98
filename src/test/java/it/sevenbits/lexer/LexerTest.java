package it.sevenbits.lexer;

import it.sevenbits.lexer.implementation.Lexer;
import it.sevenbits.lexer.token.IToken;
import it.sevenbits.lexer.token.implementation.Token;
import it.sevenbits.reader.IReader;
import it.sevenbits.reader.ReaderException;
import it.sevenbits.reader.implementation.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LexerTest {
    private Lexer lexer;
    @Test
    public void testWithMockReadToken() throws LexerException{
        Token token = new Token("BRAKE","{");
        lexer = mock(Lexer.class);
        when(lexer.readToken()).thenReturn(token);
        IToken result = lexer.readToken();
        assertEquals("{",result.getLexeme());
        assertEquals("BRAKE", result.getName());
    }
    @Test
    public void testWithMockHasMoreToken(){
        lexer = mock(Lexer.class);
        when(lexer.hasMoreToken()).thenReturn(true);
        assertEquals(true,lexer.hasMoreToken());
    }
    @Test
    public void testReadToken() throws ReaderException, LexerException {
        IReader stringReader = new StringReader("pu {");
        ILexer lexer = new Lexer(stringReader);
        assertEquals("p", lexer.readToken().getLexeme());
        assertEquals("u", lexer.readToken().getLexeme());
        assertEquals("{", lexer.readToken().getLexeme());
    }
    @Test
    public void testHasMoreTokenTrue() throws ReaderException {
        IReader stringReader = new StringReader("pu {");
        ILexer lexer = new Lexer(stringReader);
        assertEquals(true, lexer.hasMoreToken());
    }
    @Test
    public void testHasMoreTokenFalse() throws ReaderException {
        IReader stringReader = new StringReader("");
        ILexer lexer = new Lexer(stringReader);
        assertEquals(false, lexer.hasMoreToken());
    }
    @Test(expected = LexerException.class)
    public void testException() throws ReaderException, LexerException {
        IReader stringReader = new StringReader("");
        ILexer lexer = mock(Lexer.class);
        doThrow(LexerException.class).when(lexer).readToken();
        lexer.readToken();
    }
}
