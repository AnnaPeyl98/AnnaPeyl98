package it.sevenbits.formatterproject.formatter.implementation.statemachine.command.implementation;

import it.sevenbits.formatterproject.formatter.implementation.statemachine.FormatterContext;
import it.sevenbits.formatterproject.formatter.implementation.statemachine.command.IFormatterCommand;
import it.sevenbits.formatterproject.writer.WriterException;


/**
 * Class implements IFormatterCommand
 */
public class AddSpaceCommand implements IFormatterCommand {
    private IFormatterCommand command;
    private FormatterContext context;

    /**
     * Constructor for only context
     *
     * @param context contains tokens and writer
     */
    public AddSpaceCommand(final FormatterContext context) {
        this.context = context;
    }

    /**
     * Constructor with next command
     *
     * @param context contains tokens and writer
     * @param command command which will be run before this command
     */
    public AddSpaceCommand(final FormatterContext context, final IFormatterCommand command) {
        this.context = context;
        this.command = command;
    }

    /**
     * Command write space in writer
     *
     * @throws WriterException if was trouble with writing
     */
    @Override
    public void execute() throws WriterException {
        if (command != null) {
            command.execute();
        }
        context.getWriter().write(' ');
    }
}
