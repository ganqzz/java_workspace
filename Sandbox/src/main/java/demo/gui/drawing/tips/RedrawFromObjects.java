package demo.gui.drawing.tips;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * 描画オブジェクトを保存し、すべて再描画し直すことで、以前の状態を維持する方法
 * オブジェクトとして保持するので、特定のオブジェクトだけ変更することができる
 * オブジェクトが増えるにつれて、メモリ使用量と描画コストが高くなる
 */
class RedrawFromObjects extends DrawingArea {
    private ArrayList<ColoredRectangle> coloredRectangles = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paint all the Rectangles from the List
        Color foreground = g2d.getColor();
        g2d.setColor(Color.BLACK);
        g2d.drawString("Add a rectangle by doing mouse press, drag and release!", 40, 15);

        for (ColoredRectangle cr : coloredRectangles) {
            g2d.setColor(cr.foreground);
            g2d.draw(cr.rectangle);
        }

        // Paint the Rectangle as the mouse is being dragged
        if (shape != null) {
            g2d.setColor(foreground);
            g2d.draw(shape);
        }
    }

    @Override
    public void addRectangle(Rectangle rectangle, Color color) {
        // Add the Rectangle to the List so it can be repainted
        ColoredRectangle cr = new ColoredRectangle(color, rectangle);
        coloredRectangles.add(cr);
        repaint();
    }

    @Override
    public void clear() {
        coloredRectangles.clear();
        repaint();
    }

    // Drawing object
    private static class ColoredRectangle {
        final Color foreground;
        final Rectangle rectangle;

        ColoredRectangle(Color foreground, Rectangle rectangle) {
            this.foreground = foreground;
            this.rectangle = rectangle;
        }
    }

    public static void main(String[] args) {
        DrawingArea.start(new RedrawFromObjects(), "Draw On Component");
    }
}
