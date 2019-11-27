package it.sevenbits.lexer.statemachine.command;

import it.sevenbits.lexer.statemachine.command.ILexerCommand;
import it.sevenbits.lexer.statemachine.state.LexerState;

/**
 * Interface for classes which will be save map commands
 */
public interface ILexerCommandMap {
    /**
     * Search next command for this state and symbol
     *
     * @param state  current state in formatter
     * @param symbol current symbol
     * @return command for this state and symbol
     */
    ILexerCommand getCommand(LexerState state, char symbol);
}
