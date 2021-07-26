package breakout;

import java.awt.event.KeyEvent;

public class Paddle extends Sprite {

    private int dx;

    public Paddle() {
        loadImage("paddle.png");
        resetState();

        rightEdge = Commons.WIDTH - imageWidth;
    }

    public void move() {
        x += dx;

        if (x <= 0) {
            x = 0;
            return;
        }

        if (x >= rightEdge) {
            x = rightEdge;
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            dx = -2;
            break;
        case KeyEvent.VK_RIGHT:
            dx = 2;
            break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_RIGHT:
            dx = 0;
            break;
        }
    }

    private void resetState() {
        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }
}
