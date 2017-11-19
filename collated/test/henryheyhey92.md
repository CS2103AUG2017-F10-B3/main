# henryheyhey92
###### \java\systemtests\FindLetterCommandSystemTest.java
``` java
//reused
public class FindLetterCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void find() {
        /* Case: find multiple persons in address book, command with with no leading spaces and trailing spaces
         * -> 1 persons found
         */
        String command = FindCommandLetter.COMMAND_WORD + " " + KEYWORD_MATCHING_A;
        Model expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, ALICE); // first names of Benson and Daniel are "Meier"
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: repeat previous find command where person list is displaying the persons we are finding
         * -> 2 persons found
         */
        command = FindCommandLetter.COMMAND_WORD + " " + KEYWORD_MATCHING_A;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person where person list is not displaying the person we are finding -> 1 person found */
        command = FindCommandLetter.COMMAND_WORD + " " + "C";
        ModelHelper.setFilteredList(expectedModel, CARL);
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


        /* Case: find person in empty address book -> 0 persons found */
        executeCommand(ClearCommand.COMMAND_WORD);
        assert getModel().getAddressBook().getPersonList().size() == 0;
        command = FindCommandLetter.COMMAND_WORD + " " + KEYWORD_MATCHING_A;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, ALICE);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        command = "lEtTer Meier";
        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);

        /* Case: mixed case command word -> rejected */
        command = "lEtTer k";
        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);
```
