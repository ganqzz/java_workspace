package breakout;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;

    public Ball() {
        xdir = 1;
        ydir = -1;

        loadImage("ball.png");
        resetState();

        rightEdge = Commons.WIDTH - imageWidth;
    }

    public void move() {
        x += xdir;
        y += ydir;

        if (x == 0) {
            xdir = 1;
        }

        if (x == rightEdge) {
            xdir = -1;
        }

        if (y == 0) {
            ydir = 1;
        }
    }

    private void resetState() {
        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }
}
