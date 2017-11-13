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
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
        }
        if (keywords.get(0).length() > 1) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
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
=======
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
