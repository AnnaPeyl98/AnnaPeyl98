package it.sevenbits.lexer.statemachine.state;

/**
 * Class for saved and working with elements ILexerStateMap
 */
public class LexerStateTransition {
    private ILexerStateMap lexerStateMap;

    private LexerStateTransition() {

    }

    /**
     * Constructor for saving state map for formatter
     *
     * @param lexerStateMap map with will be work
     */
    public LexerStateTransition(final ILexerStateMap lexerStateMap) {
        this.lexerStateMap = lexerStateMap;
    }

    /**
     * Method for return default state for lexerStateMap
     *
     * @return default state - wait
     */
    public LexerState getStartState() {
        return lexerStateMap.getStartState();
    }

    /**
     * Return next state for current state and current symbol from lexerStateMap
     *
     * @param state       current state
     * @param currentChar current symbol
     * @return next state
     */
    public LexerState getNextState(final LexerState state, final char currentChar) {
        return lexerStateMap.getNextState(state, currentChar);
    }
}
