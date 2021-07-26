package etc;

import java.util.Random;

public class CardDeck {
    public static void main(String[] args) {
        final int rows = 4;
        final int cols = 13;
        int[][] deck = new int[rows][cols];
        initDeck(deck);
        shuffleDeck(deck);
        displayDeck(deck);
    }

    private static void initDeck(int[][] arr) {
        for (int r = 0; r < 4; ++r)
            for (int c = 0; c < 13; ++c)
                arr[r][c] = 0;
    }

    private static void displayDeck(int[][] arr) {
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 13; ++c)
                System.out.print(arr[r][c] + " ");
            System.out.println();
        }
    }

    private static void shuffleDeck(int[][] arr) {
        int row, col;
        Random rand = new Random(System.currentTimeMillis());
        for (int card = 1; card <= 52; ++card) {
            row = rand.nextInt(4);
            col = rand.nextInt(13);
            if (arr[row][col] == 0) arr[row][col] = card;
            else {
                while (arr[row][col] != 0) {
                    row = rand.nextInt(4);
                    col = rand.nextInt(13);
                }
                arr[row][col] = card;
            }
        }
    }
}
