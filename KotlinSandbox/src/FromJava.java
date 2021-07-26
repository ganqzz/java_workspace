import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import java.util.Arrays;
import java.util.List;

public class FromJava {
    public static void main(String[] args) {
        System.out.println(Constants.RED);
        System.out.println(Constants.Companion.func());
        System.out.println(Constants.Companion.func2());
        System.out.println(Constants.func2()); // @JvmStatic

        List<String> strings = Arrays.asList("Red", "Green", "Blue");
        strings.forEach(System.out::println);

        // AnonymousClassDemo.kt
        StatefulClass2 obj = new StatefulClass2(new Function1<ClickEvent, Unit>() {
            @Override
            public Unit invoke(ClickEvent event) {
                System.out.printf("Click at %d, %d\n", event.getX(), event.getY());
                return null;
            }
        });
        obj.clickMe(1, 2);
        obj.clickMe(83, 49);
        obj.setListener(null);
        obj.clickMe(5, 9);
    }
}
