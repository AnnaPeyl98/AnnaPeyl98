package it.sevenbits.formatterproject.formatter.statemachine.state.implementation;

import it.sevenbits.formatterproject.formatter.statemachine.state.FormatterState;
import it.sevenbits.formatterproject.formatter.statemachine.state.IFormatterStateMap;
import it.sevenbits.formatterproject.containers.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class implements IFormatterStateMap
 */
public class FormatterStateMap implements IFormatterStateMap {
    private Map<Pair<FormatterState, String>, FormatterState> repository;

    private static final String MESSAGE = "MESSAGE";
    private static final String SEMICOLON = "SEMICOLON";
    private static final String LEFT_BRACE = "LEFT_BRACE";
    private static final String RIGHT_BRACE = "RIGHT_BRACE";
    private static final String INLINE_COMMNET = "COMMENT_ONE_STRING";
    private static final String MULTILINE_COMMENT = "AFTER_STAR";
    private static final String STRING = "STRING";

    private static final FormatterState WRITE_STATE = new FormatterState("WRITE_STATE");
    private static final FormatterState WAIT_STATE = new FormatterState("WAIT_STATE");
    private static final FormatterState SEMICOLON_STATE = new FormatterState("SEMICOLON_STATE");
    private static final FormatterState LEFT_BRACE_STATE = new FormatterState("LEFT_BRACE_STATE");
    private static final FormatterState RIGHT_BRACE_STATE = new FormatterState("RIGHT_BRACE_STATE");
    private static final FormatterState COMMENT_STATE = new FormatterState("COMMENT_STATE");

    /**
     * Basic constructor, create map with current state and next state
     */
    public FormatterStateMap() {
        repository = new HashMap<>();

        repository.put(new Pair<>(WAIT_STATE, MESSAGE), WRITE_STATE);
        repository.put(new Pair<>(WAIT_STATE, STRING), WRITE_STATE);
        repository.put(new Pair<>(WAIT_STATE, SEMICOLON), SEMICOLON_STATE);
        repository.put(new Pair<>(WAIT_STATE, LEFT_BRACE), LEFT_BRACE_STATE);
        repository.put(new Pair<>(WAIT_STATE, RIGHT_BRACE), RIGHT_BRACE_STATE);
        repository.put(new Pair<>(WAIT_STATE, null), COMMENT_STATE);

        repository.put(new Pair<>(WRITE_STATE, MESSAGE), WRITE_STATE);
        repository.put(new Pair<>(WRITE_STATE, STRING), WRITE_STATE);
        repository.put(new Pair<>(WRITE_STATE, SEMICOLON), SEMICOLON_STATE);
        repository.put(new Pair<>(WRITE_STATE, LEFT_BRACE), LEFT_BRACE_STATE);
        repository.put(new Pair<>(WRITE_STATE, RIGHT_BRACE), RIGHT_BRACE_STATE);
        repository.put(new Pair<>(WRITE_STATE, null), COMMENT_STATE);

        repository.put(new Pair<>(SEMICOLON_STATE, MESSAGE), WRITE_STATE);
        repository.put(new Pair<>(SEMICOLON_STATE, STRING), WRITE_STATE);
        repository.put(new Pair<>(SEMICOLON_STATE, SEMICOLON), WRITE_STATE);
        repository.put(new Pair<>(SEMICOLON_STATE, LEFT_BRACE), LEFT_BRACE_STATE);
        repository.put(new Pair<>(SEMICOLON_STATE, RIGHT_BRACE), RIGHT_BRACE_STATE);
        repository.put(new Pair<>(SEMICOLON_STATE, null), COMMENT_STATE);

        repository.put(new Pair<>(LEFT_BRACE_STATE, MESSAGE), WRITE_STATE);
        repository.put(new Pair<>(LEFT_BRACE_STATE, STRING), WRITE_STATE);
        repository.put(new Pair<>(LEFT_BRACE_STATE, SEMICOLON), SEMICOLON_STATE);
        repository.put(new Pair<>(LEFT_BRACE_STATE, LEFT_BRACE), LEFT_BRACE_STATE);
        repository.put(new Pair<>(LEFT_BRACE_STATE, RIGHT_BRACE), RIGHT_BRACE_STATE);
        repository.put(new Pair<>(LEFT_BRACE_STATE, null), COMMENT_STATE);

        repository.put(new Pair<>(RIGHT_BRACE_STATE, MESSAGE), WRITE_STATE);
        repository.put(new Pair<>(RIGHT_BRACE_STATE, STRING), WRITE_STATE);
        repository.put(new Pair<>(RIGHT_BRACE_STATE, SEMICOLON), SEMICOLON_STATE);
        repository.put(new Pair<>(RIGHT_BRACE_STATE, LEFT_BRACE), LEFT_BRACE_STATE);
        repository.put(new Pair<>(RIGHT_BRACE_STATE, RIGHT_BRACE), RIGHT_BRACE_STATE);
        repository.put(new Pair<>(RIGHT_BRACE_STATE, null), COMMENT_STATE);

        repository.put(new Pair<>(COMMENT_STATE, MESSAGE), WRITE_STATE);
        repository.put(new Pair<>(COMMENT_STATE, STRING), WRITE_STATE);
        repository.put(new Pair<>(COMMENT_STATE, SEMICOLON), SEMICOLON_STATE);
        repository.put(new Pair<>(COMMENT_STATE, LEFT_BRACE), LEFT_BRACE_STATE);
        repository.put(new Pair<>(COMMENT_STATE, RIGHT_BRACE), RIGHT_BRACE_STATE);
        repository.put(new Pair<>(COMMENT_STATE, null), COMMENT_STATE);

    }

    /**
     * Method for return default state
     *
     * @return default state - wait
     */
    @Override
    public FormatterState getDefaultState() {
        return WAIT_STATE;
    }

    /**
     * Return next state for current state and token
     *
     * @param formatterState current state
     * @param tokenName      name token
     * @return next state
     */
    @Override
    public FormatterState getState(final FormatterState formatterState, final String tokenName) {
        FormatterState nextState = repository.get(new Pair<>(formatterState, tokenName));
        if (nextState == null) {
            return repository.get(new Pair<>(formatterState, null));
        }
        return nextState;
    }
}
