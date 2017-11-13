package seedu.address.logic.commands;

import seedu.address.model.person.NameLetterContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommandLetter extends Command {

    public static final String COMMAND_WORD = "letter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (non case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " A ";

    private final NameLetterContainsKeywordPredicate predicate;

    public FindCommandLetter(NameLetterContainsKeywordPredicate predicate) {
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
                || (other instanceof FindCommandLetter // instanceof handles nulls
                && this.predicate.equals(((FindCommandLetter) other).predicate)); // state check
    }
}
