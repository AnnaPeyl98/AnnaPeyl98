package it.sevenbits.lexer.statemachine.command.implementation;

import it.sevenbits.lexer.statemachine.LexerContext;
import it.sevenbits.lexer.statemachine.command.ILexerCommand;

/**
 * Class implements ILexerCommand
 */
public class AddToBufferCommand implements ILexerCommand {
    private LexerContext context;
    private ILexerCommand command;

    private AddToBufferCommand() {

    }

    /**
     * Constructor for only context
     *
     * @param context contains variables for working
     */
    public AddToBufferCommand(final LexerContext context) {
        this.context = context;
    }

    /**
     * Constructor with next command
     *
     * @param context contains variables for working
     * @param command command which will be run after this command
     */
    public AddToBufferCommand(final LexerContext context, final ILexerCommand command) {
        this.context = context;
        this.command = command;
    }

    /**
     * Set current symbol in buffer
     */
    @Override
    public void execute() {
        context.setSymbolInBuffer(context.getCurrentSymbol());
        if (command != null) {
            command.execute();
        }
    }
}
