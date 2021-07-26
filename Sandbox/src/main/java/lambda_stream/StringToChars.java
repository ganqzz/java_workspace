package lambda_stream;

public class StringToChars {
    public static void main(String[] args) {
        "ほげ".chars()
              .mapToObj(i -> (char) i)
              .forEach(System.out::println);

        "ふが".codePoints()
              .mapToObj(i -> (char) i)
              .forEach(System.out::println);
    }
}
