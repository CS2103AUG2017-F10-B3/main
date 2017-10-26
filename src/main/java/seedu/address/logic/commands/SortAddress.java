package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;

/**
 * Actual logic for sorting by address
 */
public class SortAddress extends SortCommand implements Comparator<ReadOnlyPerson> {

    public static final String
            MESSAGE_SUCCESS_ADDRESS = "The address book has been sorted alphabetically according to address!";
    public static final int OPTION_ADDRESS = 2;
    private Model model;

    public SortAddress() {
    }

    public SortAddress(Model target) {
        this.model = target;
    }

    @Override
    public CommandResult execute() throws UniquePersonList.AddressBookIsEmpty {
        model.sortAddressBook(OPTION_ADDRESS);
        return new CommandResult(MESSAGE_SUCCESS_ADDRESS);
    }

    @Override
    public int compare(ReadOnlyPerson a, ReadOnlyPerson b) {
        return a.getAddress().value.compareToIgnoreCase(b.getAddress().value);
    }
}
