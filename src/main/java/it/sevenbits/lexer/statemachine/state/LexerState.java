package it.sevenbits.lexer.statemachine.state;

import java.util.Objects;

/**
 * Class for creating state for Lexer
 */
public class LexerState {

    private String state;

    private LexerState() {

    }

    /**
     * Constructor for creating state with this name
     *
     * @param state name state
     */
    public LexerState(final String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LexerState state1 = (LexerState) o;
        return Objects.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }


}
