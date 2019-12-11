package it.sevenbits.formatterproject.lexer.implementation.statemachine.command;

import it.sevenbits.formatterproject.lexer.implementation.statemachine.state.LexerState;

/**
 * Class for saved and working with elements ILexerCommandMap
 */
public class LexerCommandTransition {
    private ILexerCommandMap commandMap;

    private LexerCommandTransition() {

    }

    /**
     * Basic constructor
     *
     * @param commandMap map which save state and command
     */
    public LexerCommandTransition(final ILexerCommandMap commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Search next command in commandMap
     *
     * @param state  current state in lexer
     * @param symbol current symbol
     * @return command for this state and symbol
     */
    public ILexerCommand getNextCommand(final LexerState state, final char symbol) {
        return commandMap.getCommand(state, symbol);
    }
}
