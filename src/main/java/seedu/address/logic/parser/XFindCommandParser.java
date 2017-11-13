package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_UPPER;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.XFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.XFindPredicate;


//@@author kikanng
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class XFindCommandParser implements Parser<XFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public XFindCommand parse(String args) throws ParseException {
        Prefix[] prefixes = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_NAME_UPPER,
            PREFIX_PHONE_UPPER, PREFIX_EMAIL_UPPER, PREFIX_ADDRESS_UPPER, PREFIX_TAG_UPPER};

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);

        if (!arePrefixesPresent(argMultimap, prefixes)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, XFindCommand.MESSAGE_USAGE));
        }


        // figure out what arg it carries
        int count = 0;
        String tokenizedArgs = null;
        Prefix prefix = null;

        for (Prefix candidate: prefixes) {
            if (arePrefixesPresent(argMultimap, candidate)) {
                tokenizedArgs = argMultimap.getValue(candidate).get();
                prefix = candidate;
                count++;
            }
        }

        if (count != 1) {        // more than one arg is found
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, XFindCommand.MESSAGE_BOUND));
        }

        // retrieve value
        String trimmedArgs = tokenizedArgs.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, XFindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new XFindCommand(new XFindPredicate(Arrays.asList(keywords), prefix));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
