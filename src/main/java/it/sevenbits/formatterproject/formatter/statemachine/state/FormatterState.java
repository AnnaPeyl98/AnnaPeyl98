package it.sevenbits.formatterproject.formatter.statemachine.state;


import java.util.Objects;

/**
 * Class for creating states
 */
public class FormatterState {

    private String state;

    private FormatterState() {
    }

    /**
     * Constructor save name state
     *
     * @param state name state
     */
    public FormatterState(final String state) {
        this.state = state;
    }

    /**
     * toString state
     *
     * @return name state
     */
    @Override
    public String toString() {
        return state;
    }

    /**
     * equals states
     *
     * @param o state for equals
     * @return true if states are equal else false
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FormatterState that = (FormatterState) o;
        return Objects.equals(state, that.state);
    }

    /**
     * Method count hash
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
