package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_UPPER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_UPPER;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

//@@author kikanng
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
