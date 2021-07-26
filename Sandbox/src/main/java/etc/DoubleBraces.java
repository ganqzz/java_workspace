package etc;

import java.util.HashMap;
import java.util.Map;

// こんなこともできるけど、非常に危険な使い方（Memory leak）。
// initializerブロック{...}を使うことで、初期化をしている。
class DoubleBraces {
    public static void main(String[] args) {
        Map<Object, Object> source = new HashMap<Object, Object>() {{
            put("firstName", "John");
            put("lastName", "Smith");
            put("organizations", new HashMap<Object, Object>() {{
                put("0", new HashMap<Object, Object>() {{
                    put("id", "1234");
                }});
                put("abc", new HashMap<Object, Object>() {{
                    put("id", "5678");
                }});
            }});
        }};
        System.out.println(source);
    }
}
