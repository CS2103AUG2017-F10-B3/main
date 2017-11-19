package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CopyCommand;


public class CopyCommandParserTest {
    private CopyCommandParser parser = new CopyCommandParser();

    @Test
    public void parse_validArgs_returnsCopyCommand() {
        assertParseSuccess(parser, " 1 n", new CopyCommand(Index.fromOneBased(1), "n"));
    }

    @Test
    public void parse_invalidArgs_failure() {
        //invalid index specified
        assertParseFailure(parser, " @ n", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CopyCommand.MESSAGE_USAGE));

        //invalid choice specified
        assertParseFailure(parser, " 1 ^%F", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CopyCommand.POSSIBLE_CHOICES));
    }
}
