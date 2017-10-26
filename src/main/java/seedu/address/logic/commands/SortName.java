package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;

/**
 * Actual logic for sorting by name
 */
public class SortName extends SortCommand implements Comparator<ReadOnlyPerson> {

    public static final String
            MESSAGE_SUCCESS_NAME = "The address book has been sorted alphabetically according to name!";
    public static final int OPTION_NAME = 1;
    private Model model;

    public SortName() {
    }

    public SortName(Model target) {
        this.model = target;
    }

    @Override
    public CommandResult execute() throws UniquePersonList.AddressBookIsEmpty {
        model.sortAddressBook(OPTION_NAME);
        return new CommandResult(MESSAGE_SUCCESS_NAME);
    }

    @Override
    public int compare(ReadOnlyPerson a, ReadOnlyPerson b) {
        return a.getName().fullName.compareToIgnoreCase(b.getName().fullName);
    }
}
