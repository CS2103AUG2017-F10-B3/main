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
import seedu.address.model.person.exceptions.LoadLookUpTableException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
//@@author NUSe0032202
public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortAddressBookByName_success() throws UniquePersonList.AddressBookIsEmpty,
            LoadLookUpTableException {
        SortCommand sortCommand = prepareCommand("n", 0, model);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sortAddressBook(1, 0);
        assertCommandSuccess(sortCommand, model, MESSAGE_SUCCESS_NAME, expectedModel);
    }

    @Test
    public void execute_sortAddressBookByAddress_success() throws UniquePersonList.AddressBookIsEmpty,
            LoadLookUpTableException {
        SortCommand sortCommand = prepareCommand("a", 0, model);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.sortAddressBook(2, 0);
        assertCommandSuccess(sortCommand, model, MESSAGE_SUCCESS_ADDRESS, expectedModel);
    }

    @Test
    public void execute_sortEmptyAddressBookByName_throwsAddressBookIsEmptyException()
            throws UniquePersonList.AddressBookIsEmpty {
        ModelManager testModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        testModel.resetData(new AddressBook());
        SortCommand sortCommand = prepareCommand("n", 0, testModel);
        assertCommandFailure(sortCommand, testModel, MESSAGE_EMPTY);
    }

    @Test
    public void execute_sortEmptyAddressBookByAddress_throwsAddressBookIsEmptyException()
            throws UniquePersonList.AddressBookIsEmpty {
        ModelManager testModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        testModel.resetData(new AddressBook());
        SortCommand sortCommand = prepareCommand("a", 0, testModel);
        assertCommandFailure(sortCommand, testModel, MESSAGE_EMPTY);
    }

    /**
     * Returns a {@code SortCommand} with parameters {@code option} and {@code saveOption}.
     */
    private SortCommand prepareCommand(String option, int saveOption, Model model) {
        SortCommand sortCommand = new SortCommand(option, saveOption);
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return sortCommand;
    }
}
//@@author
