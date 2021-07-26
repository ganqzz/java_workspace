package demo.strings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFlagsDemo {

    public static void main(String[] args) {
        String regex = "\\Glog";
        String data = "hello";
        // [1]
        Pattern pattern = Pattern.compile("the regex!"); // regex, literal string
        Pattern patternDelimited = Pattern.compile("\\d"); // regex, literal string

        // [3]
        Pattern patternWithFlag = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Pattern compoundPattern = Pattern.compile("sample compound flag regex",
                                                  Pattern.UNICODE_CASE | Pattern.COMMENTS);

        // [4]
        // flags
        int flags = compoundPattern.flags();
        boolean commentFlagIsSet = (Pattern.UNICODE_CASE == (Pattern.UNICODE_CASE & flags));

        // [5]
        Matcher matcher = pattern.matcher("nap"); // string to search

        // [6]
        boolean isMatch = Pattern.matches(regex, data);

        // find the next subsequence of the input sequence that matches the pattern
        matcher.find();

        String output = String.format("" + "found the text \"%s\" beginning at "
                                      + "index %d and ending at index %d.%n", matcher.group(),
                                      matcher.start(),
                                      matcher.end());
        System.out.println(output);
    }
}
