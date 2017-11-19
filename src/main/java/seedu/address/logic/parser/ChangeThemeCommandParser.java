package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ChangeThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;


//@@author kikanng
/**
 * Parses input arguments and creates a new ChangeThemeCommand object
 */
public class ChangeThemeCommandParser implements Parser<ChangeThemeCommand> {

    /**
     * Parses the given {@code String} of argument in the context of the ChangeThemeCommand
     * and returns an ChangeThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeThemeCommand parse(String args) throws ParseException {
        String[] possibleArgs = {"light", "dark"};
        String[] cssFiles = {"LightTheme.css", "DarkTheme.css"};

        String arg = args.trim();
        if (arg.isEmpty() || !(arg.equals("light") || arg.equals("dark"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_USAGE));
        }

        boolean legalArg = false;
        for (int i = 0; i < possibleArgs.length; i++) {
            if (arg.equals(possibleArgs[i])) {
                arg = cssFiles[i];  // arg now become the css file path
                legalArg = true;
                break;
            }
        }

        if (!legalArg) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_USAGE));
        }

        return new ChangeThemeCommand(arg);
    }

}
