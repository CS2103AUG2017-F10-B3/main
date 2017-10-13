package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortAddress.MESSAGE_SUCCESS_ADDRESS;
import static seedu.address.logic.commands.SortCommand.MESSAGE_EMPTY;
import static seedu.address.logic.commands.SortName.MESSAGE_SUCCESS_NAME;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.UniquePersonList;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortAddressBookByName_success() throws UniquePersonList.AddressBookIsEmpty {
        SortCommand sortCommand = prepareCommand("/n", model);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sortAddressBook(1);
        assertCommandSuccess(sortCommand, model, MESSAGE_SUCCESS_NAME, expectedModel);
    }

    @Test
    public void execute_sortAddressBookByAddress_success() throws UniquePersonList.AddressBookIsEmpty {
        SortCommand sortCommand = prepareCommand("/a", model);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sortAddressBook(2);
        assertCommandSuccess(sortCommand, model, MESSAGE_SUCCESS_ADDRESS, expectedModel);
    }

    @Test
    public void execute_sortEmptyAddressBookByName_throwsAddressBookIsEmptyException()
            throws UniquePersonList.AddressBookIsEmpty {
        ModelManager testModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        testModel.resetData(new AddressBook());
        SortCommand sortCommand = prepareCommand("/n", testModel);
        assertCommandFailure(sortCommand, testModel, MESSAGE_EMPTY);
    }

    @Test
    public void execute_sortEmptyAddressBookByAddress_throwsAddressBookIsEmptyException()
            throws UniquePersonList.AddressBookIsEmpty {
        ModelManager testModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        testModel.resetData(new AddressBook());
        SortCommand sortCommand = prepareCommand("/a", testModel);
        assertCommandFailure(sortCommand, testModel, MESSAGE_EMPTY);
    }

    private SortCommand prepareCommand(String option, Model model) {
        SortCommand sortCommand = new SortCommand(option);
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return sortCommand;
    }
}
