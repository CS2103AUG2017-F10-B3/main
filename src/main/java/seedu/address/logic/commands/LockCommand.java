package seedu.address.logic.commands;

import seedu.address.LoginBox;

import seedu.address.storage.Storage;

//@@author henryheyhey92
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

} //@@author
