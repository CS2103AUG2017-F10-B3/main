package seedu.address.logic.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.LoadLookUpTableException;

/**
 * Actual logic for sorting by address
 */
//@@author NUSe0032202
public class SortAddress extends SortCommand implements Comparator<ReadOnlyPerson> {

    public static final String
            MESSAGE_SUCCESS_ADDRESS = "The address book has been sorted alphabetically according to address";
    public static final int OPTION_ADDRESS = 2;
    private static ArrayList<String> table;
    private Model model;
    private int saveOption;

    //@@author

    //@@author NUSe0032202
    public SortAddress() throws IOException {
        AddressData.initTable();
        table = AddressData.getTable();
    }

    public SortAddress(Model target, int saveOption) throws IOException {
        this.model = target;
        this.saveOption = saveOption;
    }

    @Override
    public CommandResult executeUndoableCommand() throws UniquePersonList.AddressBookIsEmpty {
        try {
            model.sortAddressBook(OPTION_ADDRESS, saveOption);
            return new CommandResult(MESSAGE_SUCCESS_ADDRESS);
        } catch (LoadLookUpTableException le) {
            return new CommandResult("Error with loading data for lookup table");
        }
    }

    @Override
    public int compare(ReadOnlyPerson a, ReadOnlyPerson b) {
        String compareFirst = "";
        String compareSecond = "";
        Boolean firstAddressFound = false;
        Boolean secondAddressFound = false;

        for (String compare : table) {
            if (a.getAddress().value.toLowerCase().contains(compare.toLowerCase())) {
                compareFirst = compare;
                firstAddressFound = true;
                break;
            }
        }

        for (String compare : table) {
            if (b.getAddress().value.toLowerCase().contains(compare.toLowerCase())) {
                compareSecond = compare;
                secondAddressFound = true;
                break;
            }
        }

        //If the address can't be found in the table return a positive value to ensure that the person is always placed
        //last
        if (firstAddressFound == false) {
            return 1;
        }

        if (secondAddressFound == false) {
            return -1;
        }

        return compareFirst.compareToIgnoreCase(compareSecond);
    }
    //@@author
}
