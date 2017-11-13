package systemtests;

import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.KEYWORD_MATCHING_CLASSMATES;

import org.junit.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;

//@@author Labradorites
public class FindTagCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void findTag() {
        /* Case: find multiple persons in address book, command with leading spaces and trailing spaces
         * -> 4 persons found
         */
        String command = "   " + FindTagCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_CLASSMATES + "   ";
        Model expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, HOON, IDA); // Tags of HOON and IDA contain "classmates"
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: repeat previous find command where person list is displaying the persons we are finding
         * -> 2 persons found
         */
        command = FindTagCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_CLASSMATES;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person where person list is not displaying the person we are finding -> 1 person found */
        command = FindTagCommand.COMMAND_WORD + " family";
        ModelHelper.setFilteredList(expectedModel, CARL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 keywords -> 2 persons found */
        command = FindTagCommand.COMMAND_WORD + " family colleagues";
        ModelHelper.setFilteredList(expectedModel, CARL, DANIEL);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 keywords in reversed order -> 2 persons found */
        command = FindTagCommand.COMMAND_WORD + " colleagues family";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 keywords with 1 repeat -> 2 persons found */
        command = FindTagCommand.COMMAND_WORD + " family colleagues family";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 matching keywords and 1 non-matching keyword
         * -> 2 persons found
         */
        command = FindTagCommand.COMMAND_WORD + " colleagues family stranger";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: undo previous find command -> rejected */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: redo previous find command -> rejected */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: find same persons in address book after deleting 1 of them -> 1 person found */
        executeCommand(DeleteCommand.COMMAND_WORD + " 1");
        assert !getModel().getAddressBook().getPersonList().contains(HOON);
        command = FindTagCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_CLASSMATES;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, IDA);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person in address book, keyword is part of tag -> 0 persons found */
        command = FindTagCommand.COMMAND_WORD + " class";
        ModelHelper.setFilteredList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person in empty address book -> 0 persons found */
        executeCommand(ClearCommand.COMMAND_WORD);
        assert getModel().getAddressBook().getPersonList().size() == 0;
        command = FindTagCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_CLASSMATES;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, HOON);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        command = "FiNdTaG classmates";
        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_PERSONS_LISTED_OVERVIEW} with the number of people in the filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel) {
        String expectedResultMessage = String.format(
                MESSAGE_PERSONS_LISTED_OVERVIEW, expectedModel.getFilteredPersonList().size());

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
     * @see AddressBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
