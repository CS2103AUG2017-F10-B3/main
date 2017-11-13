package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_UPPER;

import seedu.address.model.person.XFindPredicate;


//@@author kikanng
/**
 * A more powerful find tool
 */
public class XFindCommand extends Command {

    public static final String COMMAND_WORD = "xFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Advance find feature, "
            + "can find everything containing the keywords\n"
            + "Parameters: "
            + PREFIX_NAME_UPPER + "NAME or "
            + PREFIX_PHONE_UPPER + "PHONE or "
            + PREFIX_EMAIL_UPPER + "EMAIL or "
            + PREFIX_ADDRESS_UPPER + "ADDRESS or "
            + PREFIX_TAG_UPPER + "TAG\n"
            + "if you want to only search for items start with the keywords, use "
            + PREFIX_NAME + PREFIX_PHONE + PREFIX_EMAIL + PREFIX_ADDRESS + PREFIX_TAG + "instead\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME_UPPER + "John Alan or "
            + PREFIX_PHONE_UPPER + "98765432 or "
            + PREFIX_EMAIL_UPPER + "johnd@example.com or "
            + PREFIX_ADDRESS_UPPER + "Clementi or "
            + PREFIX_TAG_UPPER + "friends";

    public static final String MESSAGE_BOUND = COMMAND_WORD + ": only one parameter is allowed";

    private final XFindPredicate predicate;

    public XFindCommand(XFindPredicate predicate) {
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
                || (other instanceof XFindCommand // instanceof handles nulls
                && this.predicate.equals(((XFindCommand) other).predicate)); // state check
    }
}
