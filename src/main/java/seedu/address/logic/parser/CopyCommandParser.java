package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CopyCommand object
 */
//@@author NUSe0032202
public class CopyCommandParser implements Parser<CopyCommand> {

    public static final int INDEX_TARGET = 0;
    public static final int INDEX_CHOICE = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CopyCommand parse(String args) throws ParseException {

        Index index;
        String choice;

        //Detect empty arguments.
        if (args.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CopyCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(args.trim().substring(INDEX_TARGET, args.trim().indexOf(" ")));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        }

        try {
            choice = ParserUtil.parseChoice(args);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.POSSIBLE_CHOICES));
        }

        return new CopyCommand(index, choice);
    }
}
//@@author
