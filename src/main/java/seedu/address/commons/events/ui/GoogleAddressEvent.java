package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Represents GoogleAddressCommand executed
 */
//@@author Labradorites
public class GoogleAddressEvent extends BaseEvent {


    private final ReadOnlyPerson personToSearch;

    public GoogleAddressEvent(ReadOnlyPerson personToSearch) {
        this.personToSearch = personToSearch;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public ReadOnlyPerson getPersonToSearch() {
        return personToSearch;
    }
}
