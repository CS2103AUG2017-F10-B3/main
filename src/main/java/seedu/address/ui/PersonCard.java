package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.ReadOnlyPerson;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static String[] colors = {"red", "yellow", "blue", "orange", "brown", "green", "pink", "black", "grey"};
    private static HashMap<String, String> tagColors = new HashMap<String, String>();
    private static Random random = new Random();
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ReadOnlyPerson person;
    private final Logger logger = LogsCenter.getLogger(this.getClass());
    private final Logic logic;
    private SelectBirthdayWindow calendar;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthday;
    @FXML
    private FlowPane tags;

    /**
     * method for giving a tag name to the person. Eg: family, friends...etc
     *
     * @param person
     * @param displayedIndex
     */
    public PersonCard(ReadOnlyPerson person, Logic logic, int displayedIndex) {
        super(FXML);
        this.person = person;
        this.logic = logic;
        id.setText(displayedIndex + ". ");
        initTags(person);
        bindListeners(person);
    }

    /**
     * Add color tag to the category
     *
     * @param tagValue
     * @return
     */
    private static String getColorForTag(String tagValue) {
        if (!tagColors.containsKey(tagValue)) {
            tagColors.put(tagValue, colors[random.nextInt(colors.length)]);
        }
        return tagColors.get(tagValue);
    }

    /**
     * Updates the birthday label to display the date picked and changes the specified person's birthday
     */
    //@@author NUSe0032202
    @FXML
    private void showArgs() {
        requireNonNull(calendar);
        birthday.setText(calendar.getBirthday());
        person.getBirthday().edit(calendar.getBirthday());
        logic.getModel().save();
    }
    //@@author

    /**
     * Opens the birthday input window.
     */
    //@@author NUSe0032202
    @FXML
    public void handleCalendar() {
        calendar = new SelectBirthdayWindow();
        calendar.show();
    }
    //@@author

    /**
     * Binds the individual UI elements to observe their respective {@code Person} properties
     * so that they will be notified of any changes.
     */
    private void bindListeners(ReadOnlyPerson person) {
        SimpleStringProperty selected = new SimpleStringProperty();
        selected.setValue(Bindings.convert(person.birthdayProperty()).getValue());
        name.textProperty().bind(Bindings.convert(person.nameProperty()));
        phone.textProperty().bind(Bindings.convert(person.phoneProperty()));
        address.textProperty().bind(Bindings.convert(person.addressProperty()));
        email.textProperty().bind(Bindings.convert(person.emailProperty()));
        birthday.textProperty().bindBidirectional(selected);
        person.tagProperty().addListener((observable, oldValue, newValue) -> {
            tags.getChildren().clear();
            /*person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));*/
            initTags(person);
        });
    }

    /**
     * to give a color tag to the person in the list
     *
     * @param person
     */
    //@@author henryheyhey92
    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.setStyle("-fx-background-color: " + getColorForTag(tag.tagName));
            tags.getChildren().add(tagLabel);
        });
    } //@@author

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
