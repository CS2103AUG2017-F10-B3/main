package seedu.address.model.person.exceptions;

/**
 *  Signals to the user that there is a problem with loading data into the lookup table
 */
public class LoadLookUpTableException extends Exception {
    public LoadLookUpTableException() {
        super("Error with loading data for the lookup table");
    }
}
