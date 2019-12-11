package it.sevenbits.formatterproject.formatter.implementation;

import it.sevenbits.formatterproject.formatter.FormatterException;
import it.sevenbits.formatterproject.formatter.IFormatter;
import it.sevenbits.formatterproject.formatter.statemachine.FormatterContext;
import it.sevenbits.formatterproject.formatter.statemachine.command.FormatterCommandMap;
import it.sevenbits.formatterproject.formatter.statemachine.command.FormatterCommandTransition;
import it.sevenbits.formatterproject.formatter.statemachine.command.IFormatterCommand;
import it.sevenbits.formatterproject.formatter.statemachine.state.FormatterState;
import it.sevenbits.formatterproject.formatter.statemachine.state.FormatterStateTransition;
import it.sevenbits.formatterproject.formatter.statemachine.state.implementation.FormatterStateMap;
import it.sevenbits.formatterproject.lexer.ILexer;
import it.sevenbits.formatterproject.lexer.LexerException;
import it.sevenbits.formatterproject.lexer.factory.ILexerFactory;
import it.sevenbits.formatterproject.lexer.factory.implementation.LexerFactory;
import it.sevenbits.formatterproject.reader.IReader;
import it.sevenbits.formatterproject.writer.IWriter;
import it.sevenbits.formatterproject.writer.WriterException;
import it.sevenbits.formatterproject.token.IToken;

/**
 * Implementation IFormatter, realisation - state machine
 */
public class StateMachineFormatter implements IFormatter {
    private ILexerFactory lexerFactory;
    private FormatterCommandTransition commandTransition;
    private FormatterStateTransition stateTransition;
    private FormatterContext context;


    /**
     * Basic constructor. Create factory Lexer for creating new Lexer
     * and variables for state machine
     */
    public StateMachineFormatter() {
        context = new FormatterContext();
        lexerFactory = new LexerFactory();
        commandTransition = new FormatterCommandTransition(new FormatterCommandMap(context));
        stateTransition = new FormatterStateTransition(new FormatterStateMap());
    }


    /**
     * Formats java code according to the rules
     *
     * @param reader - instance that contains code for formatting
     * @param writer - instance where we would write formatting code
     * @throws FormatterException if was trouble with reading or writing
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        ILexer lexer = lexerFactory.createLexer("STATE", reader);
        IToken token;
        IFormatterCommand command;
        context.setWriter(writer);
        try {
            FormatterState state = stateTransition.getDefaultState();
            while (lexer.hasMoreToken()) {
                token = lexer.readToken();
                command = commandTransition.getCommand(state, token.getName());
                context.setToken(token);
                command.execute();
                state = stateTransition.getNextState(state, token.getName());
            }
            writer.close();
        } catch (LexerException ex) {
            throw new FormatterException("Some problems with reader");
        } catch (WriterException ex) {
            throw new FormatterException("Some problems with writer");
        }

    }
}
