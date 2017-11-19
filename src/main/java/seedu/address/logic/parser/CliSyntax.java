package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_NAME_UPPER = new Prefix("N/");
    public static final Prefix PREFIX_PHONE_UPPER = new Prefix("P/");
    public static final Prefix PREFIX_EMAIL_UPPER = new Prefix("E/");
    public static final Prefix PREFIX_ADDRESS_UPPER = new Prefix("A/");
    public static final Prefix PREFIX_BIRTHDAY_UPPER = new Prefix("B/");
    public static final Prefix PREFIX_TAG_UPPER = new Prefix("T/");

}
