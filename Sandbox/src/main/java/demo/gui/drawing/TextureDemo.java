package demo.gui.drawing;

import demo.gui.Constants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TextureDemo extends JPanel {

    private BufferedImage slate;
    private BufferedImage java;
    private BufferedImage pane;

    public TextureDemo() {
        try {
            slate = ImageIO.read(new File(Constants.RESOURCE_DIR + "slate.png"));
            java = ImageIO.read(new File(Constants.RESOURCE_DIR + "java.png"));
            pane = ImageIO.read(new File(Constants.RESOURCE_DIR + "pane.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                                          "Could not load images", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        TexturePaint slateTp = new TexturePaint(slate, new Rectangle(0, 0, 90, 60));
        TexturePaint javaTp = new TexturePaint(java, new Rectangle(0, 0, 90, 60));
        TexturePaint paneTp = new TexturePaint(pane, new Rectangle(0, 0, 90, 60));

        g2d.setPaint(slateTp);
        g2d.fillRect(10, 15, 90, 60);

        g2d.setPaint(javaTp);
        g2d.fillRect(130, 15, 90, 60);

        g2d.setPaint(paneTp);
        g2d.fillRect(250, 15, 90, 60);

        g2d.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Line");
            frame.setBounds(100, 100, 400, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new TextureDemo());
            frame.setVisible(true);
        });
    }
}
