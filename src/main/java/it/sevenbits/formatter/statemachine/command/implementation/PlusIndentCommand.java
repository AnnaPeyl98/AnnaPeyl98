package it.sevenbits.formatter.statemachine.command.implementation;

import it.sevenbits.formatter.statemachine.FormatterContext;
import it.sevenbits.formatter.statemachine.command.IFormatterCommand;
import it.sevenbits.writer.WriterException;

/**
 * Class implements IFormatterCommand
 */
public class PlusIndentCommand implements IFormatterCommand {
    private IFormatterCommand command;
    private FormatterContext context;

    private PlusIndentCommand() {
    }

    ;

    /**
     * Constructor for only context
     *
     * @param context contains tokens and writer
     */
    public PlusIndentCommand(final FormatterContext context) {
        this.context = context;
    }

    /**
     * Constructor with next command
     *
     * @param context contains tokens and writer
     * @param command command which will be run before this command
     */
    public PlusIndentCommand(final FormatterContext context, final IFormatterCommand command) {
        this.context = context;
        this.command = command;
    }

    /**
     * Run command which add indent
     *
     * @throws WriterException if was trouble with writing
     */
    @Override
    public void execute() throws WriterException {
        if (command != null) {
            command.execute();
        }
        context.setIndent(context.getIndent() + 1);
    }
}
