package demo.strings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(?i)vTc"); // regex, literal string
        Matcher matcher = pattern.matcher("VTC"); // string to search
        Pattern pattern2 = Pattern.compile("[0-5&&[^345]]"); // && means Intersection => [0-2]
        Matcher matcher2 = pattern2.matcher("340526789");
        Pattern pattern3 = Pattern.compile("\\b(\\w+) \\1\\b"); // backward reference
        Matcher matcher3 = pattern3.matcher("hoge hoge vtc");

        // find the next subsequence of the input sequence that matches the pattern
        matcher.find();
        matcher2.find();
        matcher3.find();

        String output = String.format("" + "found the text \"%s\" beginning at "
                                      + "index %d and ending at index %d.%n", matcher.group(),
                                      matcher.start(),
                                      matcher.end());
        System.out.println(output);

        String output2 = String.format("" + "found the text \"%s\" beginning at "
                                       + "index %d and ending at index %d.%n", matcher2.group(),
                                       matcher2.start(),
                                       matcher2.end());
        System.out.println(output2);

        String output3 = String.format("" + "found the text \"%s\" beginning at "
                                       + "index %d and ending at index %d.%n", matcher3.group(),
                                       matcher3.start(),
                                       matcher3.end());
        System.out.println(output3);
    }
}
