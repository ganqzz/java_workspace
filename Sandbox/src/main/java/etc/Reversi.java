package etc;

import java.util.Scanner;

public class Reversi {

    private enum Piece {BLACK, WHITE}

    private static class Player {
        final Piece piece;
        int count = 2;

        Player(Piece piece) {
            this.piece = piece;
        }

        public String toString() {
            return piece + ": " + count;
        }
    }

    private static class Board {
        private final Piece[][] grid = new Piece[8][8];

        private enum Direction {
            N(-1, 0), NE(-1, 1), E(0, 1), SE(1, 1),
            S(1, 0), SW(1, -1), W(0, -1), NW(-1, -1);
            final int drow, dcol;

            Direction(int drow, int dcol) {
                this.drow = drow;
                this.dcol = dcol;
            }
        }

        Board() {
            grid[3][3] = Piece.WHITE;
            grid[3][4] = Piece.BLACK;
            grid[4][3] = Piece.BLACK;
            grid[4][4] = Piece.WHITE;
        }

        boolean canPlace(Player player) {
            final Piece[][] work = new Piece[8][8];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    work[i][j] = grid[i][j];
                }
            }
            boolean val = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == null) {
                        for (Direction dir : Direction.values()) {
                            int n = reverse(player.piece, i, j, dir);
                            if (n > 0) {
                                val = true;
                                break;
                            }
                        }
                    }
                }
                if (val) {
                    break;
                }
            }
            for (int i = 0; i < work.length; i++) {
                for (int j = 0; j < work[i].length; j++) {
                    grid[i][j] = work[i][j];
                }
            }
            return val;
        }

        private int reverse(Piece piece, int row, int col, Direction dir) {
            int nrow = row + dir.drow;
            int ncol = col + dir.dcol;
            Piece next = null;
            if ((nrow >= 0 && nrow < grid.length) &&
                (ncol >= 0 && ncol < grid[nrow].length)) {
                next = grid[nrow][ncol];
            }
            if (next == null) {
                return -1;
            }
            if (next == piece) {
                return 0;

            }
            int count = reverse(piece, nrow, ncol, dir);
            if (count >= 0) {
                grid[nrow][ncol] = piece;
                count++;
            }
            return count;
        }

        int place(Player player, String position) {
            int count = 0;
            int col = position.charAt(0) - 'a';
            int row = position.charAt(1) - '1';
            if (grid[row][col] != null) {
                return -1;
            }
            for (Direction dir : Direction.values()) {
                int n = reverse(player.piece, row, col, dir);
                if (n > 0) {
                    count += n;
                }
            }
            if (count > 0) {
                grid[row][col] = player.piece;
            }
            return count;
        }

        void display() {
            System.out.print("\n");
            System.out.print("  a b c d e f g h");
            System.out.print("\n");
            for (int i = 0; i < grid.length; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < grid[i].length; j++) {
                    Piece stone = grid[i][j];
                    if (stone == null) System.out.print("□");
                    if (stone == Piece.BLACK) System.out.print("●");
                    if (stone == Piece.WHITE) System.out.print("○");
                }
                System.out.print("\n");
            }
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player(Piece.BLACK);
        Player opponent = new Player(Piece.WHITE);
        while (true) {
            if (board.canPlace(player)) {
                board.display();
                int n;
                while ((n = board.place(player, prompt(player))) <= 0) {
                    System.out.println("wrong position");
                }
                player.count += n + 1;
                opponent.count -= n;
                System.out.println(player + ", " + opponent);
                if (player.count + opponent.count == 8 * 8 ||
                    opponent.count == 0) {
                    board.display();
                    break;
                }
            } else if (!board.canPlace(opponent)) {
                board.display();
                break;
            }
            Player p = opponent;
            opponent = player;
            player = p;
        }
    }

    private static String prompt(Player player) {
        System.out.print(player.piece + "の番ですよ>>");
        return new Scanner(System.in).next();
    }
}
