package etc.harness;

public class ConsoleDemo {
    private final TextComponent io;

    public ConsoleDemo(TextComponent io) {
        super();
        this.io = io;
    }

    public void run() {
        while (true) {
            String read = readLine();
            System.out.println("ECHO You Typed : " + read);
        }
    }

    private String readLine() {
        io.printf("Enter : ");
        while (true) {
            return io.readLine();
        }
    }

    public static void main(String[] args) {
        new ConsoleDemo(TextComponents.defaultTextComponent()).run();
    }
}
