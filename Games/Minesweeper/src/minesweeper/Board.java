package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.function.BiConsumer;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board extends JPanel {

    private static final int NUM_IMAGES = 13; // empty, 1-8, mine, covered, marked, wrong marked
    private static final int CELL_SIZE = 15;

    private static final int COVER_FOR_CELL = 10;
    private static final int MARK_FOR_CELL = 10;
    private static final int EMPTY_CELL = 0; // uncovered
    private static final int MINE_CELL = 9;
    private static final int COVERED_MINE_CELL = MINE_CELL + COVER_FOR_CELL; // 19
    private static final int MARKED_MINE_CELL = COVERED_MINE_CELL + MARK_FOR_CELL;  // 29

    private static final int DRAW_MINE = 9;
    private static final int DRAW_COVER = 10;
    private static final int DRAW_MARK = 11;
    private static final int DRAW_WRONG_MARK = 12;

    private static final int N_MINES = 30;
    private static final int N_COLS = 20; // x
    private static final int N_ROWS = 10; // y
    private static final int ALL_CELLS = N_COLS * N_ROWS;

    public static final int BOARD_WIDTH = N_COLS * CELL_SIZE;
    public static final int BOARD_HEIGHT = N_ROWS * CELL_SIZE;

    private int[][] field;
    private boolean inGame;
    private int marksLeft;
    private Image[] img;

    private final JLabel statusbar;

    public Board(JLabel statusbar) {
        this.statusbar = statusbar;

        img = new Image[NUM_IMAGES];
        for (int i = 0; i < NUM_IMAGES; i++) {
            img[i] = loadImage(i + ".png");
        }

        initBoard();
        newGame();
    }

    private static BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Board.class.getResourceAsStream("/resources/" + filename));
        } catch (IOException e) {
            System.out.println("Error while reading: " + filename);
            e.printStackTrace();
        }
        return img;
    }

    private void initBoard() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        addMouseListener(new MinesAdapter());
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void newGame() {
        Random random = new Random();
        inGame = true;
        marksLeft = N_MINES;
        statusbar.setText(Integer.toString(marksLeft));

        field = new int[N_COLS][N_ROWS]; // TODO: RowとColが逆

        for (int x = 0; x < N_COLS; x++) {
            for (int y = 0; y < N_ROWS; y++) {
                field[x][y] = COVER_FOR_CELL;
            }
        }

        int i = 0;
        while (i < N_MINES) {
            final int position = random.nextInt(ALL_CELLS);
            final int col = position % N_COLS;
            final int row = position / N_COLS;
            //System.out.println(position + " " + col + " " + row);

            // 被ったら引き直し
            if (field[col][row] == COVERED_MINE_CELL) continue;

            field[col][row] = COVERED_MINE_CELL;

            // mineの周りをカウントアップする
            walkAround(col, row, this::countUp);

            i++;
        }
    }

    private void countUp(int x, int y) {
        if (field[x][y] != COVERED_MINE_CELL) {
            field[x][y] += 1;
        }
    }

    // 周りのEmptyを開けていく
    private void uncoverEmptyCells(final int col, final int row) {
        walkAround(col, row, this::uncover);
    }

    private void uncover(int x, int y) {
        if (field[x][y] > MINE_CELL && field[x][y] <= COVERED_MINE_CELL) { // exclude marked
            field[x][y] -= COVER_FOR_CELL;
            if (field[x][y] == EMPTY_CELL) {
                uncoverEmptyCells(x, y); // recursive
            }
        }
    }

    // 周り8セルに対して、BiConsumerの処理をする
    private void walkAround(final int col, final int row, BiConsumer<Integer, Integer> consumer) {
        int x, y; // temporary

        // left
        if (col > 0) {
            x = col - 1;
            y = row - 1;
            if (y >= 0) {
                consumer.accept(x, y);
            }

            consumer.accept(x, row);

            y = row + 1;
            if (y < N_ROWS) {
                consumer.accept(x, y);
            }
        }

        // center
        y = row - 1;
        if (y >= 0) {
            consumer.accept(col, y);
        }

        y = row + 1;
        if (y < N_ROWS) {
            consumer.accept(col, y);
        }

        // right
        if (col < (N_COLS - 1)) {
            x = col + 1;
            y = row - 1;
            if (y >= 0) {
                consumer.accept(x, y);
            }

            consumer.accept(x, row);

            y = row + 1;
            if (y < N_ROWS) {
                consumer.accept(x, y);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        int uncovered = 0;
        for (int x = 0; x < N_COLS; x++) {
            for (int y = 0; y < N_ROWS; y++) {
                int cell = field[x][y];

                if (inGame && cell == MINE_CELL) {
                    inGame = false;
                }

                if (!inGame) {
                    if (cell == COVERED_MINE_CELL) {
                        cell = DRAW_MINE;
                    } else if (cell == MARKED_MINE_CELL) {
                        cell = DRAW_MARK;
                    } else if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_WRONG_MARK;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                    }
                } else {
                    if (cell > COVERED_MINE_CELL) {
                        cell = DRAW_MARK;
                    } else if (cell > MINE_CELL) {
                        cell = DRAW_COVER;
                        uncovered++;
                    }
                }

                g.drawImage(img[cell], x * CELL_SIZE, y * CELL_SIZE, this);
            }
        }

        if (!inGame) {
            statusbar.setText("Game lost");
        } else if (uncovered == 0) {
            inGame = false;
            statusbar.setText("Game won");
        }
    }

    private class MinesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (!inGame) { // new game
                newGame();
                repaint();
                return;
            }

            int x = e.getX();
            int y = e.getY();

            if (x < 0 || x >= BOARD_WIDTH ||
                y < 0 || y >= BOARD_HEIGHT) return; // out of the board

            int col = x / CELL_SIZE;
            int row = y / CELL_SIZE;
            boolean doRepaint = false;

            if (e.getButton() == MouseEvent.BUTTON3) { // right click
                if (field[col][row] > MINE_CELL) {
                    doRepaint = true;

                    if (field[col][row] <= COVERED_MINE_CELL) {
                        if (marksLeft > 0) {
                            field[col][row] += MARK_FOR_CELL;
                            marksLeft--;
                            String msg = Integer.toString(marksLeft);
                            statusbar.setText(msg);
                        } else {
                            statusbar.setText("No marks left");
                        }
                    } else {
                        field[col][row] -= MARK_FOR_CELL;
                        marksLeft++;
                        String msg = Integer.toString(marksLeft);
                        statusbar.setText(msg);
                    }
                }
            } else {
                if (field[col][row] > COVERED_MINE_CELL) return; // marked

                if (field[col][row] > MINE_CELL &&
                    field[col][row] <= COVERED_MINE_CELL) { // covered
                    field[col][row] -= COVER_FOR_CELL;
                    doRepaint = true;

                    if (field[col][row] == MINE_CELL) inGame = false;

                    if (field[col][row] == EMPTY_CELL) uncoverEmptyCells(col, row);
                }
            }

            if (doRepaint) repaint();
        }
    }
}
