package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Sorts the addressbook base on name or address
 */
//@@author NUSe0032202
public class SortCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "s";
    public static final String COMMAND_ALIAS = "sort";
    public static final String CHOICE_NAME = "n";
    public static final String CHOICE_ADDRESS = "a";

    public static final String
            MESSAGE_EMPTY = "Addressbook is currently empty please add at least 2 entries before sorting.";
    public static final String POSSIBLE_CHOICES = "Invalid choice input,the valid choices to be sorted are:\n"
            + "1. " + CHOICE_NAME + " (name)\n"
            + "2. " + CHOICE_ADDRESS + " (address)\n";
    public static final String SAVE_OPTION = "Invalid input for save option, to save please use keyword -> se ";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the addressbook by name or by address, "
            + "the state of the addressbook can be saved if specified explicitly.\n"
            + "Parameters: SORT_OPTION(must be one of the valid choices)  SAVE_OPTION(optional field)\n"
            + "Example: " + COMMAND_WORD + " a " + "se";
    public static final int SAVE = 1;
    protected int saveOption;
    private String option;
    private CommandResult result;
    public SortCommand() {
    }

    public SortCommand(String argument, int saveOption) {
        this.option = argument.trim();
        this.saveOption = saveOption;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            switch (option)  {
            case CHOICE_ADDRESS:
                SortCommand address = new SortAddress(model, saveOption);
                result = address.executeUndoableCommand();
                break;

            case CHOICE_NAME:
                SortCommand name = new SortName(model, saveOption);
                result = name.executeUndoableCommand();
                break;

            default:
                break;
            }

            //In the event the user filters the addressbook and no Person is shown, the message gets shown to the
            //user after the sort command is executed.
            //hello
            if (model.getFilteredPersonList().isEmpty()) {
                return new CommandResult("Sorting has been done on addressbook, please list all "
                        + "contacts to see the changes");
            } else {
                return result;
            }

        } catch (IOException e) {
            return  new CommandResult("Error with internal data");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && this.option.equals(((SortCommand) other).option))
                && this.saveOption == (((SortCommand) other).saveOption); // state check
    }

}
//@@author
