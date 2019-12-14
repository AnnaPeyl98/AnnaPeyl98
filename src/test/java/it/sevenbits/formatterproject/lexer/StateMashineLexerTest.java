package it.sevenbits.formatterproject.lexer;

import it.sevenbits.formatterproject.lexer.implementation.Lexer;
import it.sevenbits.formatterproject.lexer.implementation.StateMachineLexer;
import it.sevenbits.formatterproject.reader.IReader;
import it.sevenbits.formatterproject.reader.ReaderException;
import it.sevenbits.formatterproject.reader.implementation.StringReader;
import it.sevenbits.formatterproject.token.IToken;
import it.sevenbits.formatterproject.token.implementation.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StateMashineLexerTest {
        private StateMachineLexer lexer;
        @Test
        public void testWithMockReadToken() throws LexerException{
            Token token = new Token("LEFT_BRACE","{");
            lexer = mock(StateMachineLexer.class);
            when(lexer.readToken()).thenReturn(token);
            IToken result = lexer.readToken();
            assertEquals("{",result.getLexeme());
            assertEquals("LEFT_BRACE", result.getName());
        }
        @Test
        public void testWithMockHasMoreToken(){
            lexer = mock(StateMachineLexer.class);
            when(lexer.hasMoreToken()).thenReturn(true);
            assertEquals(true,lexer.hasMoreToken());
        }
        @Test
        public void testReadToken() throws ReaderException, LexerException {
            IReader stringReader = new StringReader("pu { /*ADSDAS*/ \"   Elena\" ; } ");
            ILexer lexer = new StateMachineLexer(stringReader);

            assertEquals("pu", lexer.readToken().getLexeme());
            assertEquals("{", lexer.readToken().getLexeme());
            assertEquals("/*ADSDAS*/", lexer.readToken().getLexeme());
            assertEquals("\"   Elena\"", lexer.readToken().getLexeme());
            assertEquals(";", lexer.readToken().getLexeme());
            assertEquals("}", lexer.readToken().getLexeme());
        }
        @Test
        public void testHasMoreTokenTrue() throws ReaderException {
            IReader stringReader = new StringReader("pu {");
            ILexer lexer = new StateMachineLexer(stringReader);
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
