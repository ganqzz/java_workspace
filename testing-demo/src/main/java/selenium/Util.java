package selenium;

public class Util {
    public static String getStaticFileUrlString(String res) {
        return Util.class.getResource(res).toString();
    }

    public static void main(String[] args) {
        System.out.println(Util.class.getName());
        System.out.println(getStaticFileUrlString("/TableTest.html"));
        System.out.println(getStaticFileUrlString("/website/SearchPage.html"));
    }
}
