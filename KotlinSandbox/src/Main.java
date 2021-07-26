import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        float f = 2;
        f++;
        System.out.println(f);
        System.out.println("hoge".charAt(2));
        //Console console = System.console();
        //String s = console.readLine();
        //Scanner scanner = new Scanner(System.in);
        //String s = scanner.nextLine();
        //System.out.println(s);

        int c = 0;
        do {
            System.out.println("hoge");
            c++;
        } while (c < 0);

        List<String> list = Arrays.asList("fuga", "hoge", "awawa");
        list.sort(Comparator.comparing(String::length));
        System.out.println(list);
    }

    public void hoge() {}
}
