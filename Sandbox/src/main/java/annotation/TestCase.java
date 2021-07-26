package annotation;

import java.lang.annotation.Repeatable;

@Repeatable(TestCases.class) // Wrapping automatically
public @interface TestCase {
    int param();

    boolean expected();
}
