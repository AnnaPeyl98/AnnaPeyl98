package it.sevenbits.formatterproject.lexer.implementation;

import it.sevenbits.formatterproject.lexer.ILexer;
import it.sevenbits.formatterproject.lexer.LexerException;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.LexerContext;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.command.LexerCommandMap;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.command.LexerCommandTransition;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.state.LexerState;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.state.LexerStateTransition;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.state.implementation.LexerStateMap;
import it.sevenbits.formatterproject.reader.IReader;
import it.sevenbits.formatterproject.reader.ReaderException;
import it.sevenbits.formatterproject.lexer.implementation.statemachine.command.ILexerCommand;
import it.sevenbits.formatterproject.token.IToken;
import it.sevenbits.formatterproject.token.implementation.Token;

/**
 * Class implements ILexer
 */
public class StateMachineLexer implements ILexer {
    private IReader reader;
    private LexerStateTransition transition;
    private LexerCommandTransition commandTransition;
    private LexerContext context;

    private StateMachineLexer() {
    }

    ;

    /**
     * Constructor that copies link of reader and create map tokens
     *
     * @param reader - instance of IReader
     */
    public StateMachineLexer(final IReader reader) {
        this.reader = reader;

        transition = new LexerStateTransition(new LexerStateMap());
        context = new LexerContext();
        commandTransition = new LexerCommandTransition(new LexerCommandMap(context));

    }

    /**
     * Read one token for formatter
     *
     * @return next IToken
     * @throws LexerException if was trouble with reading
     */
    @Override
    public IToken readToken() throws LexerException {
        LexerState currentState = transition.getStartState();
        context.clearBuffer();
        try {
            while (hasMoreToken() && !currentState.toString().equals("FINISH")) {
                if (context.getNextSymbol() != '\0') {
                    context.setCurrentSymbol(context.getNextSymbol());
                    context.setNextSymbol('\0');
                } else {
                    context.setCurrentSymbol(reader.read());
                }
                ILexerCommand command = commandTransition.getNextCommand(currentState, context.getCurrentSymbol());
                command.execute();
                currentState = transition.getNextState(currentState, context.getCurrentSymbol());
                context.setType(currentState.toString());
            }

            if (context.getToken() != null) {
                IToken token = context.getToken();
                context.setToken(null);
                return token;
            }
            if (context.lengthBuffer() != 0) {
                IToken token = new Token(context.getType(), context.getBuffer());
                context.setNextSymbol('\0');
                return token;
            }
        } catch (ReaderException ex) {
            throw new LexerException("Cannot read token", ex);
        }
        return null;
    }

    /**
     * Check if lexer can read next token
     *
     * @return true if can else false
     */
    @Override
    public boolean hasMoreToken() {
        return reader.hasNext() || context.getNextSymbol() != '\0';
    }


}
