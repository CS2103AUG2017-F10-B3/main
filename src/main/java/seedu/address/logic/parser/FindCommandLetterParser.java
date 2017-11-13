package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommandLetter;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameLetterContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandLetterParser implements Parser<FindCommandLetter> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommandLetter parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommandLetter(new NameLetterContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }

}
