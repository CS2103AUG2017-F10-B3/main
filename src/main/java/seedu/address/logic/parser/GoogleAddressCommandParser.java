package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.GoogleAddressCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author Labradorites
/**
 * Parses input arguments and creates a new GoogleAddressCommand object
 */
public class GoogleAddressCommandParser implements Parser<GoogleAddressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GoogleAddressCommand
     * and returns an GoogleAddressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoogleAddressCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new GoogleAddressCommand(index);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoogleAddressCommand.MESSAGE_USAGE));
        }

    }

}
