package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
//@@author NUSe0032202
public class SortCommandParser implements Parser<SortCommand> {

    public static final int INDEX_OPTION = 0;
    public static final int INDEX_SAVE_OPTION = 1;
    public static final String SAVE = "se";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String[] placeHolder;
        String option;
        final int save = 1;
        final int noSave = 0;

        placeHolder = args.trim().split("\\s+");

        //Detect empty arguments.
        if (placeHolder[INDEX_OPTION].equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                   SortCommand.MESSAGE_USAGE));
        }

        try {
            option = ParserUtil.parseSortOption(placeHolder[INDEX_OPTION]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.POSSIBLE_CHOICES));
        }

        if (placeHolder.length == 1) {
            return new SortCommand(option, noSave);
        }

        if (placeHolder[INDEX_SAVE_OPTION].compareTo(SAVE) == 0) {
            return new SortCommand(option, save);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.SAVE_OPTION));
        }
    }
    //@@author
}
