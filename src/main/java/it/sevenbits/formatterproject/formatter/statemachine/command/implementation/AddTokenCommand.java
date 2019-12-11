package it.sevenbits.formatterproject.formatter.statemachine.command.implementation;

import it.sevenbits.formatterproject.formatter.statemachine.FormatterContext;
import it.sevenbits.formatterproject.formatter.statemachine.command.IFormatterCommand;
import it.sevenbits.formatterproject.writer.WriterException;


/**
 * Class implements IFormatterCommand
 */
public class AddTokenCommand implements IFormatterCommand {
    private IFormatterCommand command;
    private FormatterContext context;

    /**
     * Constructor for only context
     *
     * @param context contains tokens and writer
     */
    public AddTokenCommand(final FormatterContext context) {
        this.context = context;
    }

    /**
     * Constructor with next command
     *
     * @param context contains tokens and writer
     * @param command command which will be run before this command
     */
    public AddTokenCommand(final FormatterContext context, final IFormatterCommand command) {
        this.context = context;
        this.command = command;
    }

    /**
     * Command write lexeme token in writer
     *
     * @throws WriterException if was trouble with writing
     */
    @Override
    public void execute() throws WriterException {
        if (command != null) {
            command.execute();
        }
        String lexeme = context.getToken().getLexeme();
        for (char c : lexeme.toCharArray()) {
            context.getWriter().write(c);
        }
    }
}
