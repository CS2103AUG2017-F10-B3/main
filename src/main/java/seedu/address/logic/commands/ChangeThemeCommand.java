package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ChangeThemeEvent;

//@@author kikanng
/**
 * Change GUI color theme.
 */
public class ChangeThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change GUI color theme.\n"
            + "Example: " + COMMAND_WORD + " light/dark";

    public static final String SHOWING_HELP_MESSAGE = "Changed color theme.";

    private static String theme;

    public ChangeThemeCommand(String theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ChangeThemeEvent(theme));
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
