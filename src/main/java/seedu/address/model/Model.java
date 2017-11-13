package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.LoadLookUpTableException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<ReadOnlyPerson> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Deletes the given person. */
    void deletePerson(ReadOnlyPerson target) throws PersonNotFoundException;

    /** Adds the given person */
    void addPerson(ReadOnlyPerson person) throws DuplicatePersonException;

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    void updatePerson(ReadOnlyPerson target, ReadOnlyPerson editedPerson)
            throws DuplicatePersonException, PersonNotFoundException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<ReadOnlyPerson> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<ReadOnlyPerson> predicate);

    /**
     * Copies a single data field of the person specified by {@code person},the choice of field to be
     * copied is specified by {@code choice}.
     * @param person
     * @param choice
     */
    void copy(ReadOnlyPerson person, String choice);

    /**
     * Utility function to save the state of the addressbook.This function is only used when editing birthday's
     * from the GUI.
     */
    void save();

    /**
     *  Sorts the addressbook base on name or address
     *  option = 1 {sorts by name}
     *  option = 2 {sorts by address}
     */

    void sortAddressBook (int option, int saveOption) throws UniquePersonList.AddressBookIsEmpty,
            LoadLookUpTableException;

    /**
     * Returns a unique tags list as {@code List<String>}
     */
    List<String> getTagsListAsString(List<Tag> tagStringList);

    /**
     * Returns list of all unique tags present in AddressBook that are sorted alphabetically
     */
    List<Tag> getNormalTagsList();

    /**
     * Returns list of all unique tags currently present in PersonListPanel that are sorted alphabetically
     */
    List<Tag> getFilteredTagsList();

}
