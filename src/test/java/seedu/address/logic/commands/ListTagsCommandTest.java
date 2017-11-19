package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

//@@author Labradorites
/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTagsCommand.
 */
public class ListTagsCommandTest {

    private Model model;
    private Model expectedModel;
    private ListTagsCommand listTagsCommand;
    private ListTagsCommand listFilteredTagsCommand;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        listTagsCommand = new ListTagsCommand("");
        listFilteredTagsCommand = new ListTagsCommand("f");
        listTagsCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        listFilteredTagsCommand.setData(expectedModel, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_listIsNotFiltered_showsUnfiltered() {
        assertCommandResult(listTagsCommand, String.format(ListTagsCommand.MESSAGE_SUCCESS,
                String.join("\n", expectedModel.getTagsListAsString(expectedModel.getNormalTagsList()))));
    }

    @Test
    public void execute_listIsFiltered_showsFiltered() {
        showFirstPersonOnly(model);
        assertCommandResult(listFilteredTagsCommand, String.format(ListTagsCommand.MESSAGE_SUCCESS,
                String.join("\n", expectedModel.getTagsListAsString(expectedModel.getFilteredTagsList()))));
    }

    private void assertCommandResult(ListTagsCommand listTagsCommand, String expectedMessage) {
        assertEquals(expectedMessage, listTagsCommand.execute().feedbackToUser);
    }
}
