package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

//@@author kikanng

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
