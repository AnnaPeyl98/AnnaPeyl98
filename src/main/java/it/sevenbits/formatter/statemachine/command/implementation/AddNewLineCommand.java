package it.sevenbits.formatter.statemachine.command.implementation;

import it.sevenbits.formatter.statemachine.FormatterContext;
import it.sevenbits.formatter.statemachine.command.IFormatterCommand;
import it.sevenbits.writer.WriterException;


/**
 * Class implements IFormatterCommand
 */
public class AddNewLineCommand implements IFormatterCommand {
    private IFormatterCommand command;
    private FormatterContext context;

    /**
     * Constructor for only context
     *
     * @param context contains tokens and writer
     */
    public AddNewLineCommand(final FormatterContext context) {
        this.context = context;
    }

    /**
     * Constructor with next command
     *
     * @param context contains tokens and writer
     * @param command command which will be run before this command
     */
    public AddNewLineCommand(final FormatterContext context, final IFormatterCommand command) {
        this.context = context;
        this.command = command;
    }

    /**
     * Command add new line and indent space in writer
     *
     * @throws WriterException if was trouble with writing
     */
    @Override
    public void execute() throws WriterException {
        if (command != null) {
            command.execute();
        }
        context.getWriter().write('\n');
        for (int i = 0; i < context.getIndent(); i++) {
            for (int j = 0; j < context.getIntendSize(); j++) {
                context.getWriter().write(' ');
            }
        }
    }
}
