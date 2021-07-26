package lambda_stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplitAsStream {

    public static void main(String[] args) {
        final Path file = Paths.get("files/hamlet.txt");
        final Pattern delim = Pattern.compile("\\s+"); // 空白区切り
        Map<String, Long> wordCounts = null;

        try (Stream<String> lines = Files.lines(file)) { // default cs: UTF-8
            wordCounts = lines
                    .flatMap(delim::splitAsStream) // 行 -> 単語
                    .collect(Collectors.groupingBy(
                            w -> w, // 単語単位
                            TreeMap::new, // 単語順
                            Collectors.counting())); // 集計
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordCounts.forEach((w, c) -> System.out.println(w + " " + c));
    }
}
