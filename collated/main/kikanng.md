# kikanng
###### /resources/view/LightTheme.css
``` css
.background {
    -fx-background-color: derive(#FEFEFE, 20%);
    background-color: #FEFEFE /*#383838;*/ /* Used in the default.html file */
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: #555555;
    -fx-opacity: 0.9;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: #FAFAFA;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: #FAFAFA;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.tab-pane {
    -fx-padding: 0 0 0 1;
}

.tab-pane .tab-header-area {
    -fx-padding: 0 0 0 0;
    -fx-min-height: 0;
    -fx-max-height: 0;
}

.table-view {
    -fx-base: #FEFEFE;
    -fx-control-inner-background: #FEFEFE;
    -fx-background-color: #FEFEFE;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color:
        transparent
        transparent
        derive(-fx-base, 80%)
        transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: black;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal .split-pane-divider {
    -fx-background-color: derive(#FEFEFE, 20%);
    -fx-border-color: transparent transparent transparent #FEFEFE;
}

.split-pane {
    -fx-border-radius: 1;
    -fx-border-width: 1;
    -fx-background-color: derive(#FEFEFE, 20%);
}

.list-view {
    -fx-background-insets: 0;
    -fx-padding: 0;
}

.list-cell {
    -fx-label-padding: 0 0 0 0;
    -fx-graphic-text-gap : 0;
    -fx-padding: 0 0 0 0;
}

.list-cell:filled:even {
    -fx-background-color: #FEFEFE;
}

.list-cell:filled:odd {
    -fx-background-color: #FEFEFE;
}

.list-cell:filled:selected {
    -fx-background-color: #F0F0F0;
}

.list-cell:filled:selected #cardPane {
    -fx-border-color: #ffffe0;
    -fx-border-width: 1;
}

.list-cell .label {
    -fx-text-fill: #1d1d1d;
}

.cell_big_label {
    -fx-font-family: "Segoe UI Semibold";
    -fx-font-size: 16px;
    -fx-text-fill: #010504;
}

.cell_small_label {
    -fx-font-family: "Segoe UI";
    -fx-font-size: 13px;
    -fx-text-fill: #010504;
}

.anchor-pane {
     -fx-background-color: derive(#FEFEFE, 20%);
}

.pane-with-border {
     -fx-background-color: derive(#FEFEFE, 20%);
     -fx-border-color: derive(#FEFEFE, 10%);
     -fx-border-top-width: 1px;
}

.status-bar {
    -fx-background-color: derive(#FEFEFE, 20%);
    -fx-text-fill: white;
}

.result-display {
    -fx-background-color: transparent;
    -fx-font-family: "Segoe UI Light";
    -fx-font-size: 13pt;
    -fx-text-fill: black;
}

.result-display .label {
    -fx-text-fill: white !important;
}

.status-bar .label {
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: black;
}

.status-bar-with-border {
    -fx-background-color: derive(#FEFEFE, 30%);
    -fx-border-color: derive(#FEFEFE, 25%);
    -fx-border-width: 1px;
}

.status-bar-with-border .label {
    -fx-text-fill: black;
}

.grid-pane {
    -fx-background-color: derive(#FEFEFE, 30%);
    -fx-border-color: derive(#FEFEFE, 30%);
    -fx-border-width: 1px;
}

.grid-pane .anchor-pane {
    -fx-background-color: derive(#FEFEFE, 30%);
}

.context-menu {
    -fx-background-color: derive(#FEFEFE, 50%);
}

.context-menu .label {
    -fx-text-fill: black;
}

.menu-bar {
    -fx-background-color: derive(#FEFEFE, 20%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: black;
    -fx-opacity: 0.9;
}

.menu .left-container {
    -fx-background-color: white;
}

/*
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #FEFEFE;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: black;
  -fx-text-fill: #FEFEFE;
}

.button:focused {
    -fx-border-color: black, black;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #FEFEFE;
    -fx-text-fill: black;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color, 30%);
}

.dialog-pane {
    -fx-background-color: #FEFEFE;
}

.dialog-pane > *.button-bar > *.container {
    -fx-background-color: #FEFEFE;
}

.dialog-pane > *.label.content {
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-text-fill: black;
}

.dialog-pane:header *.header-panel {
    -fx-background-color: derive(#FEFEFE, 25%);
}

.dialog-pane:header *.header-panel *.label {
    -fx-font-size: 18px;
    -fx-font-style: italic;
    -fx-fill: black;
    -fx-text-fill: black;
}

.scroll-bar {
    -fx-background-color: derive(#FEFEFE, 20%);
}

.scroll-bar .thumb {
    -fx-background-color: derive(#FEFEFE, 50%);
    -fx-background-insets: 3;
}

.scroll-bar .increment-button, .scroll-bar .decrement-button {
    -fx-background-color: transparent;
    -fx-padding: 0 0 0 0;
}

.scroll-bar .increment-arrow, .scroll-bar .decrement-arrow {
    -fx-shape: " ";
}

.scroll-bar:vertical .increment-arrow, .scroll-bar:vertical .decrement-arrow {
    -fx-padding: 1 8 1 8;
}

.scroll-bar:horizontal .increment-arrow, .scroll-bar:horizontal .decrement-arrow {
    -fx-padding: 8 1 8 1;
}

#cardPane {
    -fx-background-color: transparent;
    -fx-border-width: 0;
}

#commandTypeLabel {
    -fx-font-size: 11px;
    -fx-text-fill: #F70D1A;
}

#commandTextField {
    -fx-background-color: transparent #383838 transparent #383838;
    -fx-background-insets: 0;
    -fx-border-color: #383838 #383838 #383838 #383838;
    -fx-border-insets: 0;
    -fx-border-width: 1;
    -fx-font-family: "Segoe UI Light";
    -fx-font-size: 13pt;
    -fx-text-fill: black;
}

#filterField, #personListPanel, #personWebpage {
    -fx-effect: innershadow(gaussian, white, 10, 0, 0, 0);
}

#resultDisplay .content {
    -fx-background-color: transparent, #B8B8B8, transparent, #B8B8B8;
    -fx-background-radius: 0;
}

#tags {
    -fx-hgap: 7;
    -fx-vgap: 3;
}

#tags .label {
    -fx-text-fill: white;
    -fx-background-color: #ffffe0;
    -fx-padding: 1 3 1 3;
    -fx-border-radius: 2;
    -fx-background-radius: 2;
    -fx-font-size: 11;
}
```
###### /java/seedu/address/ui/MainWindow.java
``` java
    /**
     * Set color theme
     */
    private void setTheme(String themeUrl) {
        this.getPrimaryStage().getScene().getStylesheets().clear();
        this.getPrimaryStage().getScene().getStylesheets().add(MainApp.class
                .getResource("/view/" + themeUrl).toExternalForm());
    }

    @Subscribe
    private void handleChangeThemeEvent(ChangeThemeEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        setTheme(event.getTheme());
    }

    /**
     * Change color theme to light
     */
    @FXML
    public void handleLightTheme() {
        setTheme("LightTheme.css");
    }

    /**
     * Change color theme to dark
     */
    @FXML
    public void handleDarkTheme() {
        setTheme("DarkTheme.css");
    }
```
###### /java/seedu/address/commons/util/StringUtil.java
``` java
    /**
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty
     */
    private static boolean universalCompare(String sentence, String word, boolean startWith,
                                            boolean endWith, boolean ignoreCase) {
        requireNonNull(sentence);
        requireNonNull(word);

        if (ignoreCase) {
            sentence = sentence.toLowerCase();
            word = word.toLowerCase();
        }

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String regex = preppedWord;
        if (!startWith) {
            regex = String.format(".*" + regex);
        }
        if (!endWith) {
            regex = String.format(regex + ".*");
        }
        regex = String.format("^" + regex + "$");

        if (sentence.matches(regex)) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the {@code sentence} start with {@code word}.
     *   Ignores case, full word match is not required.
     *   <br>examples:<pre>
     *       startWithWordIgnoreCase("Abcdef", "abc") == true
     *       startWithWordIgnoreCase("Abcdef", "bc") == false //not start with "bc"
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty
     */
    public static boolean startWithWordIgnoreCase(String sentence, String word) {
        return universalCompare(sentence, word, true, false, true);
    }

    /**
     * Returns true if the {@code sentence} end with {@code word}.
     *   Ignores case, full word match is not required.
     *   <br>examples:<pre>
     *       endWithWordIgnoreCase("Abcdef", "def") == true
     *       endWithWordIgnoreCase("Abcdef", "ab") == false //not start with "ab"
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty
     */
    public static boolean endWithWordIgnoreCase(String sentence, String word) {
        return universalCompare(sentence, word, false, true, true);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, matches if sentence contains word.
     *   <br>examples:<pre>
     *       containsAny("abcde", "abc") == true
     *       containsAny("ABcdef", "def") == true
     *       containsAny("ABcdef", "AC") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsAny(String sentence, String word) {
        return universalCompare(sentence, word, false, false, true);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, matches if sentence contains word.
     *   <br>examples:<pre>
     *       exactWord("abc", "abc") == true
     *       exactWord("ABcdef", "abc") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean exactWord(String sentence, String word) {
        return universalCompare(sentence, word, true, true, true);
    }
```
###### /java/seedu/address/commons/events/ui/ChangeThemeEvent.java
``` java

/**
 * Chanage the color theme og the GUI
 */
public class ChangeThemeEvent extends BaseEvent {

    private String theme;

    public ChangeThemeEvent(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
```
###### /java/seedu/address/logic/parser/XFindCommandParser.java
``` java
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class XFindCommandParser implements Parser<XFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public XFindCommand parse(String args) throws ParseException {
        Prefix[] prefixes = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_NAME_UPPER,
            PREFIX_PHONE_UPPER, PREFIX_EMAIL_UPPER, PREFIX_ADDRESS_UPPER, PREFIX_TAG_UPPER};

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);

        if (!arePrefixesPresent(argMultimap, prefixes)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, XFindCommand.MESSAGE_USAGE));
        }


        // figure out what arg it carries
        int count = 0;
        String tokenizedArgs = null;
        Prefix prefix = null;

        for (Prefix candidate: prefixes) {
            if (arePrefixesPresent(argMultimap, candidate)) {
                tokenizedArgs = argMultimap.getValue(candidate).get();
                prefix = candidate;
                count++;
            }
        }

        if (count != 1) {        // more than one arg is found
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, XFindCommand.MESSAGE_BOUND));
        }

        // retrieve value
        String trimmedArgs = tokenizedArgs.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, XFindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new XFindCommand(new XFindPredicate(Arrays.asList(keywords), prefix));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
```
###### /java/seedu/address/logic/parser/ChangeThemeCommandParser.java
``` java
/**
 * Parses input arguments and creates a new ChangeThemeCommand object
 */
public class ChangeThemeCommandParser implements Parser<ChangeThemeCommand> {

    /**
     * Parses the given {@code String} of argument in the context of the ChangeThemeCommand
     * and returns an ChangeThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeThemeCommand parse(String args) throws ParseException {
        String[] possibleArgs = {"light", "dark"};
        String[] cssFiles = {"LightTheme.css", "DarkTheme.css"};

        String arg = args.trim();
        if (arg.isEmpty() || !(arg.equals("light") || arg.equals("dark"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_USAGE));
        }

        boolean legalArg = false;
        for (int i = 0; i < possibleArgs.length; i++) {
            if (arg.equals(possibleArgs[i])) {
                arg = cssFiles[i];  // arg now become the css file path
                legalArg = true;
                break;
            }
        }

        if (!legalArg) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeThemeCommand.MESSAGE_USAGE));
        }

        return new ChangeThemeCommand(arg);
    }

}
```
###### /java/seedu/address/logic/parser/FindEmailCommandParser.java
``` java
/**
 * Parses input arguments and creates a new FindEmailCommand object
 */
public class FindEmailCommandParser implements Parser<FindEmailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEmailCommand
     * and returns an FindEmailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEmailCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEmailCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindEmailCommand(new EmailContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
```
###### /java/seedu/address/logic/commands/ChangeThemeCommand.java
``` java
/**
 * Change GUI color theme.
 */
public class ChangeThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change GUI color theme.\n"
            + "Example: " + COMMAND_WORD + " light/dark";

    public static final String SHOWING_HELP_MESSAGE = "Changed color theme.";

    private static String theme;

    public ChangeThemeCommand(String theme) {
        this.theme = theme;
    }

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ChangeThemeEvent(theme));
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
```
###### /java/seedu/address/logic/commands/FindEmailCommand.java
``` java
/**
 * Finds and lists all persons in address book whose email contains any of the argument keywords.
 * Keyword matching is not case sensitive.
 */
public class FindEmailCommand extends Command {

    public static final String COMMAND_WORD = "findEmail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds persons whose email contain any of the "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " john doe johnny";

    private final EmailContainsKeywordsPredicate predicate;

    public FindEmailCommand(EmailContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindEmailCommand // instanceof handles nulls
                && this.predicate.equals(((FindEmailCommand) other).predicate)); // state check
    }
}
```
###### /java/seedu/address/logic/commands/XFindCommand.java
``` java
/**
 * A more powerful find tool
 */
public class XFindCommand extends Command {

    public static final String COMMAND_WORD = "xFind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Advance find feature, "
            + "can find everything containing the keywords\n"
            + "Parameters: "
            + PREFIX_NAME_UPPER + "NAME or "
            + PREFIX_PHONE_UPPER + "PHONE or "
            + PREFIX_EMAIL_UPPER + "EMAIL or "
            + PREFIX_ADDRESS_UPPER + "ADDRESS or "
            + PREFIX_TAG_UPPER + "TAG\n"
            + "if you want to only search for items start with the keywords, use "
            + PREFIX_NAME + PREFIX_PHONE + PREFIX_EMAIL + PREFIX_ADDRESS + PREFIX_TAG + "instead\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME_UPPER + "John Alan or "
            + PREFIX_PHONE_UPPER + "98765432 or "
            + PREFIX_EMAIL_UPPER + "johnd@example.com or "
            + PREFIX_ADDRESS_UPPER + "Clementi or "
            + PREFIX_TAG_UPPER + "friends";

    public static final String MESSAGE_BOUND = COMMAND_WORD + ": only one parameter is allowed";

    private final XFindPredicate predicate;

    public XFindCommand(XFindPredicate predicate) {
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
                || (other instanceof XFindCommand // instanceof handles nulls
                && this.predicate.equals(((XFindCommand) other).predicate)); // state check
    }
}
```
###### /java/seedu/address/model/person/XFindPredicate.java
``` java
/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Name} matches any of the keywords given.
 */
public class XFindPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;
    private Prefix prefix;

    public XFindPredicate(List<String> keywords, Prefix prefix) {
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        if (prefix == PREFIX_ADDRESS_UPPER) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsAny(person.getAddress().toString(), keyword));
        }

        if (prefix == PREFIX_EMAIL_UPPER) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsAny(person.getEmail().toString(), keyword));
        }

        if (prefix == PREFIX_NAME_UPPER) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsAny(person.getName().toString(), keyword));
        }

        if (prefix == PREFIX_PHONE_UPPER) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsAny(person.getPhone().toString(), keyword));
        }

        if (prefix == PREFIX_TAG_UPPER) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsAny(person.getTags().toString(), keyword));
        }

        if (prefix == PREFIX_ADDRESS) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.startWithWordIgnoreCase(person.getAddress().toString(), keyword));
        }

        if (prefix == PREFIX_EMAIL) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.startWithWordIgnoreCase(person.getEmail().toString(), keyword));
        }

        if (prefix == PREFIX_NAME) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.startWithWordIgnoreCase(person.getName().toString(), keyword));
        }

        if (prefix == PREFIX_PHONE) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.startWithWordIgnoreCase(person.getPhone().toString(), keyword));
        }

        // if (prefix == PREFIX_TAG)
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsAny(person.getTags().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof XFindPredicate // instanceof handles nulls
                && this.keywords.equals(((XFindPredicate) other).keywords)); // state check
    }

}
```
###### /java/seedu/address/model/person/EmailContainsKeywordsPredicate.java
``` java
/**
 * Tests that a {@code ReadOnlyPerson}'s {@code Name} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<ReadOnlyPerson> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ReadOnlyPerson person) {
        return keywords.stream()
        .anyMatch(keyword -> StringUtil.startWithWordIgnoreCase(person.getEmail().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
```
