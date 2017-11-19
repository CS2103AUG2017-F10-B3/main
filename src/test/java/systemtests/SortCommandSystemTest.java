package systemtests;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortAddress.MESSAGE_SUCCESS_ADDRESS;
import static seedu.address.logic.commands.SortCommand.CHOICE_ADDRESS;
import static seedu.address.logic.commands.SortCommand.CHOICE_NAME;
import static seedu.address.logic.commands.SortName.MESSAGE_SUCCESS_NAME;

import org.junit.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.LoadLookUpTableException;

//@@author NUSe0032202
public class SortCommandSystemTest extends AddCommandSystemTest {

    private static final String MESSAGE_INVALID_SORT_COMMAND_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.POSSIBLE_CHOICES);
    private static final String MESSAGE_INVALID_SORT_COMMAND_FORMAT_2 =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.SAVE_OPTION);

    @Test
    public void sort() throws UniquePersonList.AddressBookIsEmpty, LoadLookUpTableException {

        /*Case: sort the addressbook by name with save not enabled, command with leading and trailing spaces
        -> sorted by name */
        Model expectedModelName = getModel();
        String commandSortName = " " + SortCommand.COMMAND_WORD + " " + CHOICE_NAME;
        expectedModelName.sortAddressBook(1, 0);
        assertCommandSuccessSecondVersion(commandSortName, expectedModelName, MESSAGE_SUCCESS_NAME);

        /*Case: sort the addressbook by address with save not enabled, command with leading and trailing spaces
        -> sorted by address */
        Model expectedModelAddress = getModel();
        String commandSortAddress = " " + SortCommand.COMMAND_WORD + " " + CHOICE_ADDRESS;
        expectedModelAddress.sortAddressBook(2, 0);
        assertCommandSuccessSecondVersion(commandSortAddress, expectedModelAddress, MESSAGE_SUCCESS_ADDRESS);

        /*Case: sort the addressbook by name with save enabled, command with leading and trailing spaces
        -> sorted by name */
        expectedModelName = getModel();
        commandSortName = " " + SortCommand.COMMAND_WORD + " " + CHOICE_NAME + " " + SortCommandParser.SAVE;
        expectedModelName.sortAddressBook(1, 1);
        assertCommandSuccess(commandSortName, expectedModelName, MESSAGE_SUCCESS_NAME);

        /*Case: sort the addressbook by address with save enabled, command with leading and trailing spaces
         -> sorted by address */
        expectedModelAddress = getModel();
        commandSortAddress = " " + SortCommand.COMMAND_WORD + " " + CHOICE_ADDRESS + " " + SortCommandParser.SAVE;
        expectedModelAddress.sortAddressBook(2, 1);
        assertCommandSuccess(commandSortAddress, expectedModelAddress, MESSAGE_SUCCESS_ADDRESS);

        /* --------------------------------- Performing invalid sort operation ------------------------------------ */

        /* Case: invalid sort option ("zZt") -> rejected */
        String command = SortCommand.COMMAND_WORD + " zZt";
        assertCommandFailure(command, MESSAGE_INVALID_SORT_COMMAND_FORMAT);

        /* Case: invalid sort option ("@& se") -> rejected */
        command = SortCommand.COMMAND_WORD + " @& se";
        assertCommandFailure(command, MESSAGE_INVALID_SORT_COMMAND_FORMAT);

        /* Case: invalid save option (" n cxh") -> rejected */
        command = SortCommand.COMMAND_WORD + " n cxh";
        assertCommandFailure(command, MESSAGE_INVALID_SORT_COMMAND_FORMAT_2);

        /* Case: invalid save option (" a 78%@!*") -> rejected */
        command = SortCommand.COMMAND_WORD + " a 78%@!*";
        assertCommandFailure(command, MESSAGE_INVALID_SORT_COMMAND_FORMAT_2);
    }
    //@@author

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the model related components equal to {@code expectedModel}.<br>
     * 4. Asserts that the command box has the default style class.<br>
     * Verifications 1 to 3 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.
     *
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    //@@author NUSe0032202
    public void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
    }
    //@@author

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String)} except that instead of
     * comparing the addressbook in storage with expectedModel , comparison is with addressbook loaded with data
     * from TypicalPersons to assert that the addressbook has not been saved at all in line with the save option.
     *
     * @see AddressBookSystemTest#assertApplicationDisplaysExpectedSecondVersion(String, String, Model)
     */
    //@@author NUSe0032202
    public void assertCommandSuccessSecondVersion(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpectedSecondVersion("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
    }
    //@@author

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the model related components equal to the current model.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 to 3 are performed by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     *
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    //@@author NUSe0032202
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
    //@@author
}

