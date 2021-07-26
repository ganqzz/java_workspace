package demo.gui.models;

import demo.gui.Constants;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DocumentModelEx extends JFrame {

    private StyledDocument sdoc;
    private JTextPane textPane;

    public DocumentModelEx() {
        createToolbar();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        textPane = new JTextPane();
        sdoc = textPane.getStyledDocument();
        initStyles(textPane);

        panel.add(new JScrollPane(textPane));
        add(panel);
        pack();

        setTitle("Document Model");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createToolbar() {
        JToolBar toolbar = new JToolBar();

        ImageIcon bold = new ImageIcon(Constants.RESOURCE_DIR + "icon/bold.png");
        ImageIcon italic = new ImageIcon(Constants.RESOURCE_DIR + "icon/italic.png");
        ImageIcon strike = new ImageIcon(Constants.RESOURCE_DIR + "icon/strike.png");
        ImageIcon underline = new ImageIcon(Constants.RESOURCE_DIR + "icon/underline.png");

        JButton boldBtn = new JButton(bold);
        JButton italBtn = new JButton(italic);
        JButton striBtn = new JButton(strike);
        JButton undeBtn = new JButton(underline);

        toolbar.add(boldBtn);
        toolbar.add(italBtn);
        toolbar.add(striBtn);
        toolbar.add(undeBtn);

        add(toolbar, BorderLayout.NORTH);

        boldBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(),
                textPane.getSelectionEnd() - textPane.getSelectionStart(),
                textPane.getStyle("Bold"), false));

        italBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(),
                textPane.getSelectionEnd() - textPane.getSelectionStart(),
                textPane.getStyle("Italic"), false));

        striBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(),
                textPane.getSelectionEnd() - textPane.getSelectionStart(),
                textPane.getStyle("Strike"), false));

        undeBtn.addActionListener(e -> sdoc.setCharacterAttributes(
                textPane.getSelectionStart(),
                textPane.getSelectionEnd() - textPane.getSelectionStart(),
                textPane.getStyle("Underline"), false));
    }

    private void initStyles(JTextPane textPane) {
        Style style = textPane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);

        style = textPane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);

        style = textPane.addStyle("Underline", null);
        StyleConstants.setUnderline(style, true);

        style = textPane.addStyle("Strike", null);
        StyleConstants.setStrikeThrough(style, true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(DocumentModelEx::new);
    }
}
