package seedu.address.logic.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import seedu.address.MainApp;

/**
 * Utility class for sorting the addressbook by address
 */
public class AddressData {

    private static BufferedReader br;
    private static String line;
    private static ArrayList<String> table = new ArrayList<>();

    /**
     * Initializes the lookup table with values.
     * @throws IOException
     */
    public static void initTable() throws IOException {
        InputStream in = MainApp.class.getResourceAsStream("/tables/data.csv");
        br = new BufferedReader(new InputStreamReader(in));
        while ((line = br.readLine()) != null) {
            table.add(line.trim());
        }
    }

    public static ArrayList<String> getTable() {
        return table;
    }
}
