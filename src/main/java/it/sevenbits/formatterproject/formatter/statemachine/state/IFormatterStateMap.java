package it.sevenbits.formatterproject.formatter.statemachine.state;

/**
 * Interface for classes which will be save map next state
 */
public interface IFormatterStateMap {
    /**
     * Method for return default state
     *
     * @return default state
     */
    FormatterState getDefaultState();

    /**
     * Return next state for current state and token
     *
     * @param formatterState current state
     * @param tokenName      name token
     * @return next state
     */
    FormatterState getState(FormatterState formatterState, String tokenName);
}
