package it.sevenbits.lexer.statemachine.command.implementation;

import it.sevenbits.lexer.statemachine.LexerContext;
import it.sevenbits.lexer.statemachine.command.ILexerCommand;

/**
 * Class implements ILexerCommand
 */
public class SetNextCharCommand implements ILexerCommand {
    private LexerContext context;

    private SetNextCharCommand() {

    }

    /**
     * Constructor for only context
     *
     * @param context contains variables for working
     */
    public SetNextCharCommand(final LexerContext context) {
        this.context = context;
    }

    /**
     * set next char for lexer in context
     */
    @Override
    public void execute() {
        context.setNextSymbol(context.getCurrentSymbol());
    }
}
