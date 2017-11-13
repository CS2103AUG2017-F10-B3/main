package seedu.address.ui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * In-put birthday window of the App.
 */

//@@author NUSe0032202
public class SelectBirthdayWindow extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(SelectBirthdayWindow.class);
    private final Stage dialogStage;
    private String birthdate;
    @FXML
    private DatePicker birthday;

    public SelectBirthdayWindow() {
        super("Calendar.fxml");
        Scene scene = new Scene(getRoot(), 300, 100);
        dialogStage = createDialogStage("", null, scene);
        dialogStage.setScene(scene);
        birthday.setValue(nowLocalDate());
        this.birthdayArgs();
    }

    @FXML
    private void birthdayArgs() {
        birthdate = Integer.toString(birthday.getValue().getDayOfMonth()) + "/"
                + Integer.toString(birthday.getValue().getMonth().getValue()) + "/"
                + Integer.toString(birthday.getValue().getYear());
    }

    public String getBirthday() {
        return birthdate;
    }

    /**
     *  Shows the birthday picker window.
     */
    public void show() {
        logger.fine("Showing birthday picker.");
        dialogStage.showAndWait();
    }

    /**
     * Gets the current calender date
     * @return LocalDate
     */
    public static final LocalDate nowLocalDate() {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }
}
//@@author
