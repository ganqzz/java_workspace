package etc.harness;

import java.io.Console;
import java.io.PrintWriter;
import java.io.Reader;

public class ConsoleWrapper extends TextComponent {
    private final Console console;

    public ConsoleWrapper(Console console) {
        this.console = console;
    }

    @Override
    public TextComponent printf(String fmt, Object... params) throws ConsoleException {
        console.format(fmt, params);
        return this;
    }

    @Override
    public Reader reader() throws ConsoleException {
        return console.reader();
    }

    @Override
    public String readLine() throws ConsoleException {
        return console.readLine();
    }

    @Override
    public char[] readPassword() throws ConsoleException {
        return console.readPassword();
    }

    @Override
    public PrintWriter writer() throws ConsoleException {
        return console.writer();
    }
}
