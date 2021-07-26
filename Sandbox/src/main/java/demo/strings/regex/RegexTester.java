package demo.strings.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("************************************************************");
            displayCheatSheet();
            System.out.println("************************************************************");
            System.out.println();
            test();
        }
    }

    private static void displayCheatSheet() {
        try (BufferedReader in = Files.newBufferedReader(
                Paths.get("./files/regex_cheatsheet.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        Pattern pattern = Pattern.compile(input("Enter your regex: "));
        Matcher matcher = pattern.matcher(input("Enter input string to search: "));

        System.out.println();

        boolean found = false;
        while (matcher.find()) {
            String output = String.format("Found the text \"%s\" beginning at "
                                          + "index %d and ending at index %d.", matcher.group(),
                                          matcher.start(),
                                          matcher.end());
            System.out.println(output);
            found = true;
        }

        if (!found) {
            String output = "No match found.";
            System.out.println(output);
        }
    }

    private static String input(String msg) {
        System.out.print(msg);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
