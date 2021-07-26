package basic;

class SwitchString {
    public static void main(String[] args) {
        int h = 0;
        String s = args.length == 0 ? "" : args[0].trim().toLowerCase();
        switch (s) {
            case "hoge":
                System.out.println("Case1: " + s);
                break;
            case "fuga":
                System.out.println("Case2: " + s);
                // break; // スルー
            case "fefe":
                System.out.println("Case3: " + s);
                break;
            default:
                System.out.println("Default.");
                break;
        }
        System.out.println();
    }
}
