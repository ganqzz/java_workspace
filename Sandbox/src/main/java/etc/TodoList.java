package etc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoList {
    private static final String FILE_NAME = "./temp/todo.txt";

    public static void main(String[] args) {
        int menuItem = -1;
        while (menuItem != 0) {
            menuItem = menu();
            switch (menuItem) {
                case 1:
                    showList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Don't recognize input.");
            }
        }
    }

    private static int menu() {
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("\nMain Menu\n");
        System.out.println("0. Exit the program");
        System.out.println("1. Display to-do list");
        System.out.println("2. Add item to to-do list");
        System.out.println("3. Remove item from to-do list");
        System.out.println();
        System.out.print("Enter a choice: ");
        choice = input.nextInt();
        return choice;
    }

    private static void showList() {
        System.out.println("\nTo-do List\n");
        try (BufferedReader inFile = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int number = 1;
            while ((line = inFile.readLine()) != null) {
                System.out.println(number + " " + line);
                ++number;
            }
            System.out.println();
        } catch (IOException ioe) {
            System.out.println("Can't access file.");
        }
    }

    private static void addItem() {
        System.out.println("\nAdd Item\n");
        System.out.print("Enter an item: ");
        Scanner input = new Scanner(System.in);
        String item = input.nextLine();

        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            outFile.write(item);
            outFile.newLine();
        } catch (IOException ioe) {
            System.out.println("Can't access file.");
        }
    }

    private static void removeItem() {
        showList();
        System.out.print("Which item do you want to remove? ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        List<String> items = new ArrayList<>();

        try (BufferedReader inFile = new BufferedReader(new FileReader(FILE_NAME));) {
            String line;
            int number = 1;
            while ((line = inFile.readLine()) != null) {
                if (number != choice) items.add(line);
                ++number;
            }
        } catch (IOException ioe) {
            System.out.println("Can't access file.");
        }

        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(FILE_NAME));) {
            for (String item : items) {
                outFile.write(item);
                outFile.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Can't access file.");
        }
    }
}
