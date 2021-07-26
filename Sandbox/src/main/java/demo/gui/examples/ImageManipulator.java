package demo.gui.examples;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageManipulator {
    private static final String IMAGE_FILENAME = "src/main/resources/testimage.jpg";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageManipulator test = new ImageManipulator();
            test.createSwingWindow();
        });
    }

    public JFrame createSwingWindow() {
        JFrame frame = new JFrame("Test Swing Application");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Confirmation Dialog on exit
        frame.addWindowListener(new WindowAdapter() { // ignore setDefaultCloseOperation(...)
            @Override
            public void windowClosing(WindowEvent e) {
                int shouldClose = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to close this window?",
                        "Confirm close",
                        JOptionPane.YES_NO_OPTION);
                if (shouldClose == JOptionPane.YES_OPTION) System.exit(0);
            }
        });

        JLabel label = new JLabel("This is our test Swing application");
        JButton button = new JButton("Click Me!");

        ImageIcon icon = new ImageIcon(IMAGE_FILENAME);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setPreferredSize(new Dimension(
                icon.getIconWidth(), icon.getIconHeight()));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel pctSizeLabel = new JLabel("Percent Size: ");
        JTextField pctSizeField = new JTextField();
        JLabel rotationLabel = new JLabel("Degree Rotation: ");
        JTextField rotationField = new JTextField();

        button.addActionListener(event -> adjustImage(pctSizeField, rotationField, iconLabel));

        panel.add(pctSizeLabel);
        panel.add(pctSizeField);
        panel.add(rotationLabel);
        panel.add(rotationField);

        frame.add(label);
        frame.add(iconLabel);
        frame.add(panel);
        //frame.add(button);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(button);

        JButton reloadButton = new JButton("Reload Image");
        reloadButton.addActionListener(event -> reloadImage(iconLabel));
        buttonPanel.add(reloadButton);

        JButton saveButton = new JButton("Save Image");
        saveButton.addActionListener(event -> saveImageToFile(iconLabel));
        buttonPanel.add(saveButton);

        frame.add(buttonPanel);

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    private void adjustImage(JTextField pctField, JTextField degreeField,
                             JLabel imageLabel) {
        Icon icon = imageLabel.getIcon();
        ImageIcon imageIcon = (ImageIcon) icon;
        Image image = imageIcon.getImage();

        BufferedImage bi = convertToBufferedImage(image);

        bi = resizeImage(bi, getIntFromTextField(pctField, 100));
        bi = rotateImageBetter(bi, getIntFromTextField(pctField, 0));

        imageLabel.setIcon(new ImageIcon(bi));
    }

    private BufferedImage convertToBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null),
                                             BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bi;
    }

    private int getIntFromTextField(JTextField pctField, int defaultValue) {
        try {
            int num = Integer.parseInt(pctField.getText());
            return num;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private BufferedImage resizeImage(BufferedImage bi, int percent) {
        double scale = percent / 100.0;
        AffineTransform resize = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp op = new AffineTransformOp(
                resize,
                AffineTransformOp.TYPE_BICUBIC);
        return op.filter(bi, null);
    }

    private BufferedImage rotateImage(BufferedImage bi, int degrees) {
        double radians = Math.toRadians(degrees);
        AffineTransform rotate = AffineTransform.getRotateInstance(radians);
        AffineTransformOp op = new AffineTransformOp(
                rotate,
                AffineTransformOp.TYPE_BILINEAR);
        return op.filter(bi, null);
    }

    private BufferedImage rotateImageBetter(BufferedImage bi, int degrees) {
        double radians = Math.toRadians(degrees);
        AffineTransform rotate = AffineTransform.getRotateInstance(radians);

        int height = bi.getHeight();
        int width = bi.getWidth();
        Rectangle newSize = rotate.createTransformedShape(
                new Rectangle(width, height)).getBounds();

        rotate = new AffineTransform();
        rotate.translate(newSize.width * 0.5, newSize.height * 0.5);
        rotate.rotate(radians);
        rotate.translate(-width * 0.5, -height * 0.5);

        AffineTransformOp op = new AffineTransformOp(
                rotate,
                AffineTransformOp.TYPE_BILINEAR);
        return op.filter(bi, null);
    }

    private void saveImage(Image image, File file) throws IOException {
        BufferedImage bi = convertToBufferedImage(image);
        ImageIO.write(bi, "png", file);
    }

    private void saveJpgImage(Image image, File file) throws IOException {
        BufferedImage bi = convertToBufferedImage(image);

        BufferedImage rgbImage = new BufferedImage(
                bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        ColorConvertOp toRGB = new ColorConvertOp(null);
        toRGB.filter(bi, rgbImage);

        ImageIO.write(rgbImage, "jpg", file);
    }

    private boolean warnAboutReload = true;

    private void reloadImage(JLabel imageLabel) {
        if (warnAboutReload) {
            JLabel message = new JLabel("Warning, this will reload the image!");
            JCheckBox noReloadWarn = new JCheckBox("Don't tell me that again");
            JComponent[] controls = new JComponent[]{
                    message,
                    noReloadWarn
            };
            JOptionPane.showMessageDialog(
                    imageLabel.getParent(),
                    controls,
                    "Image Will Reload",
                    JOptionPane.PLAIN_MESSAGE);
            warnAboutReload = !noReloadWarn.isSelected();
        }

        imageLabel.setIcon(new ImageIcon(IMAGE_FILENAME));
    }

    private void saveImageToFile(JLabel imageLabel) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(imageLabel.getParent());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try {
                ImageIcon icon = (ImageIcon) imageLabel.getIcon();
                saveImage(icon.getImage(), file);

                JOptionPane.showMessageDialog(
                        imageLabel.getParent(),
                        "File saved to: " + file,
                        "File Saved Successfully",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        imageLabel.getParent(),
                        "Error saving file: " + e.getMessage(),
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
