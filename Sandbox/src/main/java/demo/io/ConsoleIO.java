package demo.io;

import java.io.Console;

public class ConsoleIO {

    public static void main(String[] args) {
        Console console = System.console();
        if (console != null) { // null on IDEs console
            console.printf("Please Enter userid : ");
            String userid = console.readLine();
            char[] password;
            password = console.readPassword("%s", "Please enter password : ");
            String passwordString = new String(password);
            console.printf("Userid = %s\tPassword = %s", userid, passwordString);
        } else {
            System.out.println("Console unavailable at this time.");
            // Scanner(System.in) or BufferedReader(InputStreamReader(System.in))を使う
        }
    }
}
