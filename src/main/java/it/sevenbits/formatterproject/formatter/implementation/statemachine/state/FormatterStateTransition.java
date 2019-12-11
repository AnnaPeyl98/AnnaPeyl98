package it.sevenbits.formatterproject.formatter.implementation.statemachine.state;

/**
 * Class for saved and working with elements IFormatterStateMap
 */
public class FormatterStateTransition {
    private IFormatterStateMap formatterStateMap;

    private FormatterStateTransition() {

    }

    /**
     * Constructor for saving state map for formatter
     *
     * @param formatterStateMap map with will be work
     */
    public FormatterStateTransition(final IFormatterStateMap formatterStateMap) {
        this.formatterStateMap = formatterStateMap;
    }

    /**
     * Method for return default state for formatterStateMap
     *
     * @return default state - wait
     */
    public FormatterState getDefaultState() {
        return formatterStateMap.getDefaultState();
    }

    /**
     * Return next state for current state and token from formatterStateMap
     *
     * @param state     current state
     * @param nameToken name token
     * @return next state
     */
    public FormatterState getNextState(final FormatterState state, final String nameToken) {
        return formatterStateMap.getState(state, nameToken);
    }
}
