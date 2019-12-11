package it.sevenbits.formatterproject.formatter.implementation.statemachine.command;

import it.sevenbits.formatterproject.formatter.implementation.statemachine.state.FormatterState;

/**
 * Class for saved and working with elements IFormatterCommandMap
 */
public class FormatterCommandTransition {
    private IFormatterCommandMap commandMap;

    private FormatterCommandTransition() {
    }

    /**
     * Basic constructor
     *
     * @param commandMap map which save state and command
     */
    public FormatterCommandTransition(final IFormatterCommandMap commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Search next command in commandMap
     *
     * @param state     current state in formatter
     * @param nameToken name current token
     * @return command for this state and token
     */
    public IFormatterCommand getCommand(final FormatterState state, final String nameToken) {
        return commandMap.getCommand(state, nameToken);
    }
}
