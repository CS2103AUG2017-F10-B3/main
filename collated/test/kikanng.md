# kikanng
###### /java/seedu/address/logic/commands/FindEmailCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model) for {@code FindEmailCommand}.
 */
public class FindEmailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        EmailContainsKeywordsPredicate firstPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("first"));
        EmailContainsKeywordsPredicate secondPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("second"));

        FindEmailCommand findFirstCommand = new FindEmailCommand(firstPredicate);
        FindEmailCommand findSecondCommand = new FindEmailCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindEmailCommand findFirstCommandCopy = new FindEmailCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindEmailCommand command = prepareCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindEmailCommand command = prepareCommand("heinz werner lydia");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, ELLE, FIONA));
    }

    /**
     * Parses {@code userInput} into a {@code FindEmailCommand}.
     */
    private FindEmailCommand prepareCommand(String userInput) {
        FindEmailCommand command =
                new FindEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<ReadOnlyPerson>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(FindEmailCommand command, String expectedMessage, List<ReadOnlyPerson> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredPersonList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
```
