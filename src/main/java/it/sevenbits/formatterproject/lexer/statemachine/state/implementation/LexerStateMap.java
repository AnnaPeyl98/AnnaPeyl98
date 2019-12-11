package it.sevenbits.formatterproject.lexer.statemachine.state.implementation;

import it.sevenbits.formatterproject.Pair;
import it.sevenbits.formatterproject.lexer.statemachine.state.ILexerStateMap;
import it.sevenbits.formatterproject.lexer.statemachine.state.LexerState;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implements ILexerStateMap
 */
public class LexerStateMap implements ILexerStateMap {
    private Map<Pair<LexerState, Character>, LexerState> repository;
    private static final char CHAR_LEFT_BRACE = '{';
    private static final char CHAR_RIGHT_BRACE = '}';
    private static final char CHAR_SEMICOLON = ';';
    private static final char SPACE = ' ';
    private static final char SLASH = '/';
    private static final char STAR = '*';
    private static final char STRING = '\"';
    private static final char NEXT_LINE = '\n';

    private static LexerState waitState = new LexerState("WAIT");
    private static LexerState defaultState = new LexerState("MESSAGE");
    private static LexerState finishState = new LexerState("FINISH");
    private static LexerState leftBraceState = new LexerState("LEFT_BRACE");
    private static LexerState rightBraceState = new LexerState("RIGHT_BRACE");
    private static LexerState semicolonState = new LexerState("SEMICOLON");
    private static LexerState stringState = new LexerState("STRING");
    private static LexerState commentState = new LexerState("COMMENT");
    private static LexerState afterStarState = new LexerState("AFTER_STAR");
    private static LexerState commentOneStringState = new LexerState("COMMENT_ONE_STRING");
    private static LexerState commentTwoStringState = new LexerState("COMMENT_TWO_STRING");

    /**
     * Basic constructor, create map with current state and next state
     */
    public LexerStateMap() {
        repository = new HashMap<>();

        repository.put(new Pair<>(waitState, CHAR_LEFT_BRACE), leftBraceState);
        repository.put(new Pair<>(waitState, CHAR_RIGHT_BRACE), rightBraceState);
        repository.put(new Pair<>(waitState, CHAR_SEMICOLON), semicolonState);
        repository.put(new Pair<>(waitState, SPACE), waitState);
        repository.put(new Pair<>(waitState, NEXT_LINE), waitState);
        repository.put(new Pair<>(waitState, SLASH), commentState);
        repository.put(new Pair<>(waitState, STRING), stringState);
        repository.put(new Pair<>(waitState, null), defaultState);

        repository.put(new Pair<>(stringState, null), stringState);
        repository.put(new Pair<>(stringState, STRING), finishState);

        repository.put(new Pair<>(leftBraceState, null), finishState);

        repository.put(new Pair<>(rightBraceState, null), finishState);

        repository.put(new Pair<>(semicolonState, null), finishState);

        repository.put(new Pair<>(defaultState, CHAR_LEFT_BRACE), finishState);
        repository.put(new Pair<>(defaultState, CHAR_RIGHT_BRACE), finishState);
        repository.put(new Pair<>(defaultState, CHAR_SEMICOLON), finishState);
        repository.put(new Pair<>(defaultState, SPACE), finishState);
        repository.put(new Pair<>(defaultState, SLASH), finishState);
        repository.put(new Pair<>(defaultState, NEXT_LINE), finishState);
        repository.put(new Pair<>(defaultState, null), defaultState);

        repository.put(new Pair<>(commentState, SPACE), commentState);
        repository.put(new Pair<>(commentState, NEXT_LINE), commentState);
        repository.put(new Pair<>(commentState, SLASH), commentOneStringState);
        repository.put(new Pair<>(commentState, STAR), commentTwoStringState);
        repository.put(new Pair<>(commentState, null), finishState);

        repository.put(new Pair<>(commentOneStringState, null), commentOneStringState);
        repository.put(new Pair<>(commentOneStringState, NEXT_LINE), finishState);

        repository.put(new Pair<>(commentTwoStringState, null), commentTwoStringState);
        repository.put(new Pair<>(commentTwoStringState, STAR), afterStarState);

        repository.put(new Pair<>(afterStarState, null), commentTwoStringState);
        repository.put(new Pair<>(afterStarState, SLASH), finishState);
    }

    /**
     * Method for return start state
     *
     * @return default state - wait
     */
    @Override
    public LexerState getStartState() {
        return waitState;
    }

    /**
     * Return next state for current state and symbol
     *
     * @param state       current state
     * @param currentChar current symbol
     * @return next state
     */
    @Override
    public LexerState getNextState(final LexerState state, final char currentChar) {
        LexerState stateNext = repository.get(new Pair<>(state, currentChar));
        if (stateNext == null) {
            return repository.get(new Pair<>(state, null));
        }
        return stateNext;
    }
}
