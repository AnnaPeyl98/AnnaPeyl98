package it.sevenbits.formatterproject.formatter.statemachine.command.implementation;

import it.sevenbits.formatterproject.formatter.statemachine.FormatterContext;
import it.sevenbits.formatterproject.formatter.statemachine.command.IFormatterCommand;


/**
 * Class implements IFormatterCommand
 */
public class MinusIndentCommand implements IFormatterCommand {
    private FormatterContext context;

    /**
     * Constructor for only context
     *
     * @param context contains tokens and writer
     */
    public MinusIndentCommand(final FormatterContext context) {
        this.context = context;
    }

    /**
     * Command reduces indent by one
     */
    @Override
    public void execute() {
        context.setIndent(context.getIndent() - 1);
    }
}
