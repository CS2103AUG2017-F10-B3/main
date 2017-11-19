package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.GoogleAddressEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.ui.testutil.EventsCollectorRule;

//@@author Labradorites
/**
 * Contains integration tests (interaction with the Model) for {@code GoogleAddressCommand}.
 */
public class GoogleAddressCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index lastPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size());

        assertExecutionSuccess(INDEX_FIRST_PERSON);
        assertExecutionSuccess(INDEX_THIRD_PERSON);
        assertExecutionSuccess(lastPersonIndex);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFirstPersonOnly(model);

        assertExecutionSuccess(INDEX_FIRST_PERSON);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        showFirstPersonOnly(model);

        Index outOfBoundsIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundsIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        assertExecutionFailure(outOfBoundsIndex, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GoogleAddressCommand googleAddressFirstCommand = new GoogleAddressCommand(INDEX_FIRST_PERSON);
        GoogleAddressCommand googleAddressSecondCommand = new GoogleAddressCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(googleAddressFirstCommand.equals(googleAddressFirstCommand));

        // same values -> returns true
        GoogleAddressCommand googleAddressFirstCommandCopy = new GoogleAddressCommand(INDEX_FIRST_PERSON);
        assertTrue(googleAddressFirstCommand.equals(googleAddressFirstCommandCopy));

        // different types -> returns false
        assertFalse(googleAddressFirstCommand.equals(1));

        // null -> returns false
        assertFalse(googleAddressFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(googleAddressFirstCommand.equals(googleAddressSecondCommand));
    }

    /**
     * Executes a {@code GoogleAddressCommand} with the given {@code index}, and checks that {@code GoogleAddressEvent}
     * is raised with the correct index.
     */
    private void assertExecutionSuccess(Index index) {
        GoogleAddressCommand googleAddressCommand = prepareCommand(index);
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
        ReadOnlyPerson personToSearch = lastShownList.get(index.getZeroBased());

        try {
            CommandResult commandResult = googleAddressCommand.execute();
            assertEquals(String.format(GoogleAddressCommand.MESSAGE_SUCCESS, personToSearch.getName()),
                    commandResult.feedbackToUser);
        } catch (CommandException ce) {
            throw new IllegalArgumentException("Execution of command should not fail.", ce);
        }

        GoogleAddressEvent lastEvent = (GoogleAddressEvent) eventsCollectorRule.eventsCollector.getMostRecent();
        assertEquals(personToSearch, lastEvent.getPersonToSearch());
    }

    /**
     * Executes a {@code GoogleAddressCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        GoogleAddressCommand googleAddressCommand = prepareCommand(index);

        try {
            googleAddressCommand.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException ce) {
            assertEquals(expectedMessage, ce.getMessage());
            assertTrue(eventsCollectorRule.eventsCollector.isEmpty());
        }
    }

    /**
     * Returns a {@code GoogleAddressCommand} with parameters {@code index}.
     */
    private GoogleAddressCommand prepareCommand(Index index) {
        GoogleAddressCommand googleAddressCommand = new GoogleAddressCommand(index);
        googleAddressCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return googleAddressCommand;
    }
}
