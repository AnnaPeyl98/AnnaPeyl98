package it.sevenbits.lexer.statemachine.command.implementation;

import it.sevenbits.lexer.statemachine.LexerContext;
import it.sevenbits.lexer.statemachine.command.ILexerCommand;
import it.sevenbits.token.implementation.Token;

/**
 * Class implements ILexerCommand
 */
public class SetToTokenCommand implements ILexerCommand {
    private LexerContext context;
    private ILexerCommand command;

    private SetToTokenCommand() {

    }

    /**
     * Constructor for only context
     *
     * @param context contains variables for working
     */
    public SetToTokenCommand(final LexerContext context) {
        this.context = context;
    }

    /**
     * Constructor with next command
     *
     * @param context contains variables for working
     * @param command command which will be run before this command
     */
    public SetToTokenCommand(final LexerContext context, final ILexerCommand command) {
        this.context = context;
        this.command = command;
    }

    /**
     * Create token end clear buffer in context
     */
    @Override
    public void execute() {
        if (command != null) {
            command.execute();
        }
        context.setToken(new Token(context.getType(), context.getBuffer()));
        context.clearBuffer();

    }
}
