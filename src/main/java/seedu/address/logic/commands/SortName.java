package seedu.address.logic.commands;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.LoadLookUpTableException;

/**
 * Actual logic for sorting by name
 */
//author NUSe0032202
public class SortName extends SortCommand implements Comparator<ReadOnlyPerson> {

    public static final String
            MESSAGE_SUCCESS_NAME = "The address book has been sorted alphabetically according to name";
    public static final int OPTION_NAME = 1;
    private Model model;
    private int saveOption;

    public SortName() {
    }

    public SortName(Model target, int saveOption) {

        this.model = target;
        this.saveOption = saveOption;
    }

    @Override
    public CommandResult executeUndoableCommand() throws UniquePersonList.AddressBookIsEmpty {
        try {
            model.sortAddressBook(OPTION_NAME, saveOption);
            return new CommandResult(MESSAGE_SUCCESS_NAME);
        } catch (LoadLookUpTableException le) {
            return new CommandResult("This should never be displayed");
        }
    }

    @Override
    public int compare(ReadOnlyPerson a, ReadOnlyPerson b) {
        return a.getName().fullName.compareToIgnoreCase(b.getName().fullName);
    }
}
//@@author
