# henryheyhey92
###### /java/seedu/address/ConfirmBox.java
``` java
public class ConfirmBox {
    private static boolean answer;

    /**
     * To display the Confirm exit box
     * @param title
     * @param message
     * @return
     */
    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(150);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }
}

```
###### /java/seedu/address/logic/commands/FindCommandLetter.java
``` java
/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommandLetter extends Command {

    public static final String COMMAND_WORD = "letter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (non case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " A ";

    private final NameLetterContainsKeywordPredicate predicate;

    public FindCommandLetter(NameLetterContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommandLetter // instanceof handles nulls
                && this.predicate.equals(((FindCommandLetter) other).predicate)); // state check
    }
```
###### /java/seedu/address/logic/commands/LockCommand.java
``` java
/**
 * lock application
 */
public class LockCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "lock";
    public static final String MESSAGE_SUCCESS = "Address book is unlock!";
    private static LoginBox lock;
    protected Storage storage;
    /**
     * lock the application when command is inputted
     */
    public LockCommand() {
        lock = new LoginBox();
        lock.display ("AddressBook");
    }

    @Override
    public CommandResult executeUndoableCommand() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

```
###### /java/seedu/address/logic/parser/FindCommandLetterParser.java
``` java
/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandLetterParser implements Parser<FindCommandLetter> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommandLetter parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommandLetter(new NameLetterContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }
```
###### /java/seedu/address/LoginBox.java
``` java
/**
 * This is to create the login window.
 */
public class LoginBox {

    private static boolean answer;
    private static TextField nameInput = new TextField();
    private static TextField passwordInput = new TextField();
    /**
     * create the login box display
     *
     */
    public static boolean display(String title) {
        //create window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        //create Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(12);

        //create label for name and password
        Label nameLabel = new Label("User name:");
        GridPane.setConstraints(nameLabel, 1, 2);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 1, 3);

        // Name and Password input
        //TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name");
        GridPane.setConstraints(nameInput, 2, 2);

        //TextField passwordInput = new TextField();
        passwordInput.setPromptText("Password");
        GridPane.setConstraints(passwordInput, 2, 3);

        //Create login buttons to access app
        Button yesButton = new Button("Login");
        GridPane.setConstraints(yesButton, 2, 4);

        window.setOnCloseRequest(e -> {
            e.consume();
            stop();
        });

        yesButton.setOnAction (e -> {
            if (isInt(nameInput, passwordInput)) {
                answer = true;
                window.close();
            }
        });
        grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, yesButton);
        window.setScene(new Scene(grid, 350, 200));
        window.showAndWait();

        return answer;
    }

    /**
     * to create a exit checker
     */
    private static void stop() {
        boolean answer = ConfirmBox.display("Exit Check Protocol", "Confirm on exiting the program?");

        if (answer) {
            Platform.exit();
            System.exit(0);
        }
    }

    /**
     *
     * @param name
     * @param pass
     * @return true or false
     */
    private static boolean isInt(TextField name, TextField pass) {
        String name2 = name.getText();
        String pass2 = pass.getText();
        try {
            if (name2.compareTo("NUS") == 0) {
                if (pass2.compareTo("1234") == 0) {
                    nameInput.setText("");
                    passwordInput.setText("");
                    return true;
                }
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
```
###### /java/seedu/address/MainApp.java
``` java
    @Override
    public void start(Stage primaryStage) throws Exception {

        logger.info("Starting AddressBook " + MainApp.VERSION);
        window = primaryStage;
        boolean answer = LoginBox.display("AddressBook Login");
        if (answer) {

            ui.start(primaryStage);

            window.setOnCloseRequest(e -> {
                e.consume();
                stop();
            });

        }

```
###### /java/seedu/address/model/person/NameLetterContainsKeywordPredicate.java
``` java
//reused
/**
 *  return the name that matches the key letter
 */
public class NameLetterContainsKeywordPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;

    public NameLetterContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {

        //String letter = person.getName().fullName;
        if (keywords.isEmpty()) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(" ", keyword));
        }
        if (keywords.get(0).length() > 1) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase("", keyword));
        } else {
            String letter2 = String.valueOf(person.getName().fullName.charAt(0));
            //System.out.println(letter2.length());
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(letter2, keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameLetterContainsKeywordPredicate // instanceof handles nulls
                && this.keywords.equals(((NameLetterContainsKeywordPredicate) other).keywords)); // state check
    }

```
###### /java/seedu/address/ui/PersonCard.java
``` java
    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.setStyle("-fx-background-color: " + getColorForTag(tag.tagName));
            tags.getChildren().add(tagLabel);
        });
```
###### /java/seedu/address/ui/ResultDisplay.java
``` java
    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        /* Platform.runLater(() -> displayed.setValue(event.message)); */
        displayed.setValue(event.message);
```
###### /java/seedu/address/ui/StatusBarFooter.java
``` java
    public StatusBarFooter(String saveLocation, int totalPersons) {
        super(FXML);
        setSyncStatus(SYNC_STATUS_INITIAL);
        setSaveLocation("./" + saveLocation);
        setTotalPersons(totalPersons);
        registerAsAnEventHandler(this);
```
###### /java/seedu/address/ui/StatusBarFooter.java
``` java
    private void setTotalPersons(int totalPersons) {
        this.totalPersons.setText(totalPersons + " person(s) total");
    }
```
