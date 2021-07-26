package breakout;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {

    public int x;
    public int y;
    public int imageWidth;
    public int imageHeight;
    public Image image;
    public int rightEdge;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y,
                             image.getWidth(null), image.getHeight(null));
    }

    protected void loadImage(String filename) {
        try {
            image = ImageIO.read(Sprite.class
                                         .getResourceAsStream("/resources/" + filename));
        } catch (IOException e) {
            System.out.println("Error while reading: " + filename);
            e.printStackTrace();
        }

        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
}
