package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Sorts the addressbook base on name or address
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String
            MESSAGE_EMPTY = "Addressbook is currently empty please add at least 2 entries before sorting.";
    private String option;
    private CommandResult result;

    public SortCommand(String argument) {
        this.option = argument.trim();
    }

    public SortCommand() {
    }

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);

        switch (option) {
        case "/a":
            Command address = new SortAddress(model);
            result = address.execute();
            break;

        case "/n":
            Command name = new SortName(model);
            result = name.execute();
            break;

        default:
            break;
        }

        return result;

    }
}
