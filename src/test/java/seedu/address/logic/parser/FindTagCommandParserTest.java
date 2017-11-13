package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindTagCommand;
import seedu.address.model.person.TagContainsKeywordsPredicate;

//@@author Labradorites
public class FindTagCommandParserTest {

    private FindTagCommandParser parser = new FindTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FindTagCommand expectedFindTagCommand =
                new FindTagCommand(new TagContainsKeywordsPredicate(Arrays.asList("neighbours", "family")));
        assertParseSuccess(parser, " neighbours family", expectedFindTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n neighbours \n \t family  \t", expectedFindTagCommand);
    }

}
