package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortCommand() {
        assertParseSuccess(parser, "a", new SortCommand("a", 0));
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid sort option
        assertParseFailure(parser, " ^G&F", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.POSSIBLE_CHOICES));

        //invalid save option
        assertParseFailure(parser, "a @V", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.SAVE_OPTION));
    }
}
