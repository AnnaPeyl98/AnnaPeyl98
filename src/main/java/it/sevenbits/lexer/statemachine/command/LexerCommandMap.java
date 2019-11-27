package it.sevenbits.lexer.statemachine.command;

import it.sevenbits.lexer.statemachine.LexerContext;
import it.sevenbits.lexer.statemachine.state.LexerState;
import it.sevenbits.Pair;
import it.sevenbits.lexer.statemachine.command.implementation.AddToBufferCommand;
import it.sevenbits.lexer.statemachine.command.implementation.IgnoreCommand;
import it.sevenbits.lexer.statemachine.command.implementation.SetNextCharCommand;
import it.sevenbits.lexer.statemachine.command.implementation.SetToTokenCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implements ILexerCommandMap
 */
public class LexerCommandMap implements ILexerCommandMap {
    private Map<Pair<LexerState, Character>, ILexerCommand> repository;
    private static final char CHAR_LEFT_BRACE = '{';
    private static final char CHAR_RIGHT_BRACE = '}';
    private static final char CHAR_SEMICOLON = ';';
    private static final char SPACE = ' ';
    private static final char SLASH = '/';
    private static final char STAR = '*';
    private static final char STRING = '"';
    private static final char NEXT_LINE = '\n';

    private static LexerState waitState = new LexerState("WAIT");
    private static LexerState defaultState = new LexerState("MESSAGE");
    private static LexerState leftBraceState = new LexerState("LEFT_BRACE");
    private static LexerState rightBraceState = new LexerState("RIGHT_BRACE");
    private static LexerState semicolonState = new LexerState("SEMICOLON");
    private static LexerState stringState = new LexerState("STRING");
    private static LexerState commentState = new LexerState("COMMENT");
    private static LexerState afterStarState = new LexerState("AFTER_STAR");
    private static LexerState commentOneStringState = new LexerState("COMMENT_ONE_STRING");
    private static LexerState commentTwoStringState = new LexerState("COMMENT_TWO_STRING");

    private LexerCommandMap() {

    }

    /**
     * Constructor create map which save next command for state and symbol
     *
     * @param context contains variables for working
     */
    public LexerCommandMap(final LexerContext context) {
        repository = new HashMap<>();

        repository.put(new Pair<>(waitState, CHAR_LEFT_BRACE), new AddToBufferCommand(context));
        repository.put(new Pair<>(waitState, CHAR_RIGHT_BRACE), new AddToBufferCommand(context));
        repository.put(new Pair<>(waitState, CHAR_SEMICOLON), new AddToBufferCommand(context));
        repository.put(new Pair<>(waitState, SPACE), new IgnoreCommand());
        repository.put(new Pair<>(waitState, NEXT_LINE), new IgnoreCommand());
        repository.put(new Pair<>(waitState, SLASH), new AddToBufferCommand(context));
        repository.put(new Pair<>(waitState, STRING), new AddToBufferCommand(context));
        repository.put(new Pair<>(waitState, null), new AddToBufferCommand(context));

        repository.put(new Pair<>(stringState, null), new AddToBufferCommand(context));
        repository.put(new Pair<>(stringState, STRING), new SetToTokenCommand(context, new AddToBufferCommand(context)));

        repository.put(new Pair<>(leftBraceState, null), new SetToTokenCommand(context, new SetNextCharCommand(context)));

        repository.put(new Pair<>(rightBraceState, null), new SetToTokenCommand(context, new SetNextCharCommand(context)));

        repository.put(new Pair<>(semicolonState, null), new SetToTokenCommand(context, new SetNextCharCommand(context)));

        repository.put(new Pair<>(defaultState, CHAR_LEFT_BRACE), new SetToTokenCommand(context, new SetNextCharCommand(context)));
        repository.put(new Pair<>(defaultState, CHAR_RIGHT_BRACE), new SetToTokenCommand(context, new SetNextCharCommand(context)));
        repository.put(new Pair<>(defaultState, CHAR_SEMICOLON), new SetToTokenCommand(context, new SetNextCharCommand(context)));
        repository.put(new Pair<>(defaultState, SPACE), new SetToTokenCommand(context, new SetNextCharCommand(context)));
        repository.put(new Pair<>(defaultState, SLASH), new SetToTokenCommand(context, new SetNextCharCommand(context)));
        repository.put(new Pair<>(defaultState, NEXT_LINE), new SetToTokenCommand(context, new SetNextCharCommand(context)));
        repository.put(new Pair<>(defaultState, null), new AddToBufferCommand(context));

        repository.put(new Pair<>(commentState, SPACE), new IgnoreCommand());
        repository.put(new Pair<>(commentState, NEXT_LINE), new IgnoreCommand());
        repository.put(new Pair<>(commentState, SLASH), new AddToBufferCommand(context));
        repository.put(new Pair<>(commentState, STAR), new AddToBufferCommand(context));
        repository.put(new Pair<>(commentState, null), new SetToTokenCommand(context, new SetNextCharCommand(context)));

        repository.put(new Pair<>(commentOneStringState, null), new AddToBufferCommand(context));
        repository.put(new Pair<>(commentOneStringState, NEXT_LINE), new SetToTokenCommand(context, new SetNextCharCommand(context)));

        repository.put(new Pair<>(commentTwoStringState, null), new AddToBufferCommand(context));
        repository.put(new Pair<>(commentTwoStringState, STAR), new AddToBufferCommand(context));

        repository.put(new Pair<>(afterStarState, null), new AddToBufferCommand(context));
        repository.put(new Pair<>(afterStarState, SLASH), new SetToTokenCommand(context, new AddToBufferCommand(context)));
    }

    /**
     * Search next command for this state and symbol
     *
     * @param state  current state in formatter
     * @param symbol current symbol
     * @return command for this state and symbol
     */
    @Override
    public ILexerCommand getCommand(final LexerState state, final char symbol) {
        ILexerCommand command = repository.get(new Pair<>(state, symbol));
        if (command == null) {
            return repository.get(new Pair<>(state, null));
        }
        return command;
    }
}
