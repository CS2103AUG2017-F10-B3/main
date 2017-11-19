package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

//@@author Labradorites
/**
 * Lists all unique tags added to AddressBook or PersonListPanel depending on choice to the user.
 * Does not show duplicate tags.
 */
public class ListTagsCommand extends Command {

    public static final String FILTERED = "f";

    public static final String COMMAND_WORD = "listTags";
    public static final String MESSAGE_SUCCESS = "List all tags:\n%1$s";
    public static final String MESSAGE_NO_TAGS = "There are no tags available.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds tags according to choice.\n"
            + "1. To list all tags available in AddressBook: " + COMMAND_WORD + "\n"
            + "2. To list all tags of persons currently shown below: " + COMMAND_WORD + " " + FILTERED + "\n";

    private List<String> listOfTags = null;
    private String option;

    public ListTagsCommand(String argument) {
        this.option = argument;
    }

    public List<String> getListOfTags() {
        return listOfTags;
    }

    @Override
    public CommandResult execute() {
        requireNonNull(model);

        switch (option) {
        case FILTERED:
            listOfTags = model.getTagsListAsString(model.getFilteredTagsList());
            break;
        default:
            listOfTags = model.getTagsListAsString(model.getNormalTagsList());
            break;
        }

        if (listOfTags.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", listOfTags)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTagsCommand // instanceof handles nulls
                && this.option.equals(((ListTagsCommand) other).option)); // state check
    }
}
