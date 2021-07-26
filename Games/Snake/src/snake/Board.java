package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private static final int B_WIDTH = 300;
    private static final int B_HEIGHT = 300;
    private static final int DOT_SIZE = 10;
    private static final int ALL_DOTS = 900;
    private static final int RAND_POS = 29; // B_WIDTH / DOT_SIZE or B_HEIGHT / DOT_SIZE
    private static final int DELAY = 100;

    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];

    private int dots;
    private int appleX;
    private int appleY;

    private Direction direction = Direction.RIGHT;
    private boolean inGame;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private int score;

    public Board() {
        loadImages();
        initBoard();
        initGame();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
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

    private void loadImages() {
        ball = loadImage("dot.png");
        apple = loadImage("apple.png");
        head = loadImage("head.png");
    }

    private void initGame() {
        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        inGame = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            drawScore(g);

            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Score: " + score, 10, 20);
    }

    private void gameOver(Graphics g) {
        String msg = String.format("Game Over with score %d", score);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            dots++;
            score = dots - 3;
            locateApple();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        switch (direction) {
        case LEFT:
            x[0] -= DOT_SIZE;
            break;
        case RIGHT:
            x[0] += DOT_SIZE;
            break;
        case UP:
            y[0] -= DOT_SIZE;
            break;
        case DOWN:
            y[0] += DOT_SIZE;
            break;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (x[0] < 0 || x[0] > B_WIDTH - DOT_SIZE ||
            y[0] < 0 || y[0] > B_HEIGHT - DOT_SIZE) {
            inGame = false;
        }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        appleX = r * DOT_SIZE;

        r = (int) (Math.random() * RAND_POS);
        appleY = r * DOT_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollision();

        if (inGame) {
            checkApple();
            move();
        } else {
            timer.stop();
        }

        repaint();
    }

    private enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (direction != Direction.RIGHT)) {
                direction = Direction.LEFT;
            }

            if ((key == KeyEvent.VK_RIGHT) && (direction != Direction.LEFT)) {
                direction = Direction.RIGHT;
            }

            if ((key == KeyEvent.VK_UP) && (direction != Direction.DOWN)) {
                direction = Direction.UP;
            }

            if ((key == KeyEvent.VK_DOWN) && (direction != Direction.UP)) {
                direction = Direction.DOWN;
            }
        }
    }
}
