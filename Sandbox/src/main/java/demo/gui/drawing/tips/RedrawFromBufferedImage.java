package demo.gui.drawing.tips;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * BufferedImageを利用して、以前の状態を維持する方法
 * メモリ使用量は、BufferedImageのサイズだけでほぼ一定
 * 描画した結果だけしか保持しない
 */
class RedrawFromBufferedImage extends DrawingArea {
    private BufferedImage image =
            new BufferedImage(AREA_SIZE, AREA_SIZE, BufferedImage.TYPE_INT_ARGB);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // painting from the BufferedImage
        if (image != null) {
            g2d.drawImage(image, 0, 0, null);
        }

        // Paint the Rectangle as the mouse is being dragged
        if (shape != null) {
            g2d.draw(shape);
        }
    }

    @Override
    public void addRectangle(Rectangle rectangle, Color color) {
        // Draw the Rectangle onto the BufferedImage
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(color);
        g2d.draw(rectangle);
        repaint();
    }

    @Override
    public void clear() {
        createEmptyImage();
        repaint();
    }

    private void createEmptyImage() {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawString("Add a rectangle by doing mouse press, drag and release!", 40, 15);
    }

    public static void main(String[] args) {
        DrawingArea.start(new RedrawFromBufferedImage(), "Draw On Image");
    }
}
