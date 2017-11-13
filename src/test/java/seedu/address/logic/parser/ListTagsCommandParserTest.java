package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.ListTagsCommand;

//@@author Labradorites
public class ListTagsCommandParserTest {
    private ListTagsCommandParser parser = new ListTagsCommandParser();

    @Test
    public void parse_validArgs_returnsListTagsCommand() {
        //No argument at input
        assertParseSuccess(parser, "", new ListTagsCommand(""));
        //Multiple whitespace in argument at input
        assertParseSuccess(parser, " ", new ListTagsCommand(""));
        //f as argument at input
        assertParseSuccess(parser, " f", new ListTagsCommand("f"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " e", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " f1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " 222", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
    }
}
