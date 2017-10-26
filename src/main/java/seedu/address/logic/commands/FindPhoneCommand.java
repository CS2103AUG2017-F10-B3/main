package seedu.address.logic.commands;

import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindPhoneCommand extends Command {

    public static final String COMMAND_WORD = "findPhone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone contain any of "
            + "the specified numbers() and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 999 111 99998888";

    private final PhoneContainsKeywordsPredicate predicate;

    public FindPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPhoneCommand // instanceof handles nulls
                && this.predicate.equals(((FindPhoneCommand) other).predicate)); // state check
    }
}
