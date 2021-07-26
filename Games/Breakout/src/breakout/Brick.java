package breakout;

public class Brick extends Sprite {

    private boolean destroyed = false;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;

        loadImage("brick.png");
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean val) {
        destroyed = val;
    }
}
