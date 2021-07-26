package pluralsight;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(GoodTestsCategory.class)
public class HelloJUnitTest {

    @Test
    public void test() {
        //fail("Not yet implemented");
        assertEquals("hoge", "hoge");
    }
}
