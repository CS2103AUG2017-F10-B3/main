package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        for (String wordInSentence: wordsInPreppedSentence) {
            if (wordInSentence.equalsIgnoreCase(preppedWord)) {
                return true;
            }
        }
        return false;
    }

    //@@author kikanng
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
    //@@author

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
