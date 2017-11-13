# Labradorites
###### /java/systemtests/FindTagCommandSystemTest.java
``` java
public class FindTagCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void findTag() {
        /* Case: find multiple persons in address book, command with leading spaces and trailing spaces
         * -> 4 persons found
         */
        String command = "   " + FindTagCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_CLASSMATES + "   ";
        Model expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, HOON, IDA);// Tags of HOON and IDA contain "classmates"
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
```
###### /java/systemtests/FindPhoneCommandSystemTest.java
``` java
public class FindPhoneCommandSystemTest extends AddressBookSystemTest {

    @Test
    public void findPhone() {
        /* Case: find multiple persons in address book, command with leading spaces and trailing spaces
         * -> 2 persons found
         */
        String command = "   " + FindPhoneCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_84 + "   ";
        Model expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, HOON, IDA); // Phone numbers of Hoon and Ida contain "84"
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: repeat previous find command where person list is displaying the persons we are finding
         * -> 2 persons found
         */
        command = FindPhoneCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_84;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person where person list is not displaying the person we are finding -> 1 person found */
        command = FindPhoneCommand.COMMAND_WORD + " 8482424";
        ModelHelper.setFilteredList(expectedModel, HOON);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 keywords -> 2 persons found */
        command = FindPhoneCommand.COMMAND_WORD + " 8482424 8482131";
        ModelHelper.setFilteredList(expectedModel, HOON, IDA);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 keywords in reversed order -> 2 persons found */
        command = FindPhoneCommand.COMMAND_WORD + " 8482131 8482424";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 keywords with 1 repeat -> 2 persons found */
        command = FindPhoneCommand.COMMAND_WORD + " 8482424 8482131 8482424";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple persons in address book, 2 matching keywords and 1 non-matching keyword
         * -> 2 persons found
         */
        command = FindPhoneCommand.COMMAND_WORD + " 8482424 8482131 123456789";
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
        command = FindPhoneCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_84;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, IDA);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person in address book, keyword is other part of phone -> 0 persons found */
        command = FindPhoneCommand.COMMAND_WORD + " Mei";
        ModelHelper.setFilteredList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find person in empty address book -> 0 persons found */
        executeCommand(ClearCommand.COMMAND_WORD);
        assert getModel().getAddressBook().getPersonList().size() == 0;
        command = FindPhoneCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_84;
        expectedModel = getModel();
        ModelHelper.setFilteredList(expectedModel, HOON);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        command = "FiNd Meier";
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
```
###### /java/systemtests/FindCommandSystemTest.java
``` java
        /* Case: find phone number of person in address book -> 1 persons found */
        command = FindCommand.COMMAND_WORD + " " + DANIEL.getPhone().value;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();
```
###### /java/seedu/address/commons/util/StringUtilTest.java
``` java
    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */
    @Test
    public void containsAny_validInputs_correctResult() {
        // Matches multiple words in sentence
        assertTrue(StringUtil.containsAny("111 222 333  444", "222"));
    }
```
###### /java/seedu/address/logic/parser/ListTagsCommandParserTest.java
``` java
public class ListTagsCommandParserTest {
    private ListTagsCommandParser parser = new ListTagsCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " e", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " 222", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
    }
}
```
###### /java/seedu/address/logic/parser/FindTagCommandParserTest.java
``` java
public class FindTagCommandParserTest {

    private FindTagCommandParser parser = new FindTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FindTagCommand expectedFindTagCommand =
                new FindTagCommand(new TagContainsKeywordsPredicate(Arrays.asList("neighbours", "family")));
        assertParseSuccess(parser, " neighbours family", expectedFindTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n neighbours \n \t family  \t", expectedFindTagCommand);
    }

}
```
###### /java/seedu/address/logic/parser/FindPhoneCommandParserTest.java
``` java
public class FindPhoneCommandParserTest {

    private FindPhoneCommandParser parser = new FindPhoneCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPhoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPhoneCommand() {
        // no leading and trailing whitespaces
        FindPhoneCommand expectedFindPhoneCommand =
                new FindPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("85355255", "22222222")));
        assertParseSuccess(parser, " 85355255 22222222", expectedFindPhoneCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 85355255 \n \t 22222222  \t", expectedFindPhoneCommand);
    }

}
```
###### /java/seedu/address/logic/commands/ListTagsCommandTest.java
``` java
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
```
###### /java/seedu/address/logic/commands/FindTagCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model) for {@code FindTagCommand}.
 */
public class FindTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("neighbours"));

        FindTagCommand findFirstCommand = new FindTagCommand(firstPredicate);
        FindTagCommand findSecondCommand = new FindTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTagCommand findFirstCommandCopy = new FindTagCommand(firstPredicate);
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
        FindTagCommand command = prepareCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindTagCommand command = prepareCommand("owesMoney family colleagues");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(BENSON, CARL, DANIEL));
    }

    /**
     * Parses {@code userInput} into a {@code FindTagCommand}.
     */
    private FindTagCommand prepareCommand(String userInput) {
        FindTagCommand command =
                new FindTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<ReadOnlyPerson>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(FindTagCommand command, String expectedMessage,
                                      List<ReadOnlyPerson> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredPersonList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
```
###### /java/seedu/address/logic/commands/FindPhoneCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model) for {@code FindPhoneCommand}.
 */
public class FindPhoneCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PhoneContainsKeywordsPredicate firstPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("123"));
        PhoneContainsKeywordsPredicate secondPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("567"));

        FindPhoneCommand findFirstCommand = new FindPhoneCommand(firstPredicate);
        FindPhoneCommand findSecondCommand = new FindPhoneCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPhoneCommand findFirstCommandCopy = new FindPhoneCommand(firstPredicate);
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
        FindPhoneCommand command = prepareCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindPhoneCommand command = prepareCommand("95352563 9482224 9482427");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(CARL, ELLE, FIONA));
    }

    /**
     * Parses {@code userInput} into a {@code FindPhoneCommand}.
     */
    private FindPhoneCommand prepareCommand(String userInput) {
        FindPhoneCommand command =
                new FindPhoneCommand(new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<ReadOnlyPerson>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(FindPhoneCommand command, String expectedMessage,
                                      List<ReadOnlyPerson> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredPersonList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
```
###### /java/seedu/address/model/person/TagContainsKeywordsPredicateTest.java
``` java

/******/
public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("neighbours");
        List<String> secondPredicateKeywordList = Arrays.asList("neighbours", "friends");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // Exact keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends", "neighbours"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("colleagues", "friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends"));
        assertFalse(predicate.test(new PersonBuilder().withTags("colleagues").build()));

        // Keywords match name, phone, email and address, but does not match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Alice", "09876", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("neighbours").build()));
    }
}
```
###### /java/seedu/address/model/person/PhoneContainsKeywordsPredicateTest.java
``` java
/******/
public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("123");
        List<String> secondPredicateKeywordList = Arrays.asList("123", "456");

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy = new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // Exact keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("99901234"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("99901234").build()));

        // Contains one keyword
        predicate = new PhoneContainsKeywordsPredicate(Collections.singletonList("22"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("11221234").build()));

        // Multiple keywords
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("33", "44"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("11331244").build()));

        // Only one matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("55", "66"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("00551111").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("01234567").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("666"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("Alice", "09876", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
```
