package lambda_stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RequireNonNullDemo {

    public static void yell(String words) {
        Objects.requireNonNull(words, () -> "Created issue" + RequireNonNullDemo.createIssue());
        System.out.printf("%s!!!!! %n", words.toUpperCase());
    }

    private static String createIssue() {
        System.out.println("Some external API call to a bug tracker");
        return "#ABC123";
    }

    public static void main(String[] args) {
        List<String> ingredients = Arrays.asList(
                "flour",
                "salt",
                //null, // <-
                "baking powder",
                "butter",
                "eggs",
                "milk"
        );

        RequireNonNullDemo.yell("But I want that cupcake");
        ingredients.forEach(RequireNonNullDemo::yell);

        RequireNonNullDemo.yell(null);
    }
}
