package it.sevenbits.formatterproject.token;

import it.sevenbits.formatterproject.token.implementation.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenTest {
    private Token token;
    @Test
    public void testWithMockGetName(){
        String name = "SEMICOLON";
        token = mock(Token.class);
        when(token.getName()).thenReturn(name);
        String resultName = token.getName();
        assertEquals(name, resultName);
    }
    @Test
    public void testWithMockGetLexeme(){
        String lexeme = ";";
        token = mock(Token.class);
        when(token.getLexeme()).thenReturn(lexeme);
        String resultName = token.getLexeme();
        assertEquals(lexeme, resultName);
    }

    @Test
    public void testGetName(){
        token = new Token("SEMICOLON",";");
        String name = "SEMICOLON";
        assertEquals(name, token.getName());
    }
    @Test
    public void testGetLexeme(){
        token = new Token("SEMICOLON",";");
        String name = ";";
        assertEquals(name, token.getLexeme());
    }
}
