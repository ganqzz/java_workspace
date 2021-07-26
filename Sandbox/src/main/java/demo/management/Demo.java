package demo.management;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Run and use jconsole at runtime
 */
public class Demo implements ActionListener, DemoMBean, Runnable {

    private long startTime = System.currentTimeMillis();
    private JTextField textField;
    private JTextArea textArea;

    public static void main(String[] args) {

        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName demoName = new ObjectName("demo.management:type=Demo");
            Demo demoMBean = new Demo();
            mbs.registerMBean(demoMBean, demoName);

            javax.swing.SwingUtilities.invokeLater(demoMBean);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        runWindow();
    }

    @Override
    public void resetTimer() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void clearScreen() {
        textArea.setText("");
        textField.setText("");
    }

    @Override
    public String getTextAreaData() {
        return textArea.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - startTime;
        textField.setText(Long.toString(timeDiff));
        textArea.setText(textArea.getText() + " adding a small amount of text.");
    }

    private void runWindow() {
        JFrame mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.2;

        JLabel label = new JLabel(" ");
        mainWindow.add(label, constraints);

        constraints.weightx = 0.5;
        textField = new JTextField("This is a text field", 20);
        textField.setBackground(Color.YELLOW);
        textField.setToolTipText("Text Field");
        mainWindow.add(textField, constraints);

        constraints.weightx = 0.2;
        label = new JLabel(" ");
        mainWindow.add(label, constraints);

        constraints.weightx = 0.5;
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.5;
        textArea = new JTextArea("This is a text area.\nIt occupies multiple lines.", 20, 30);
        textArea.append(" Like all text components, you can programmatically change the");
        textArea.append(" colors, the font, size and the text.... as well as many others.");
        textArea.append(
                " You can even set whether or not the control should wrap text automatically.");
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBackground(Color.CYAN);
        textArea.setSelectedTextColor(Color.WHITE);
        textArea.setSelectionColor(Color.DARK_GRAY);
        textArea.setToolTipText("Text Area");
        mainWindow.add(textArea, constraints);

        constraints.weightx = 0.2;
        constraints.weighty = 0.2;
        constraints.gridy = 2;
        label = new JLabel(" ");
        mainWindow.add(label, constraints);

        constraints.gridy = 3;
        constraints.gridx = 2;
        constraints.fill = GridBagConstraints.NONE;
        JButton button = new JButton("OK");
        button.addActionListener(this);
        button.setForeground(Color.BLUE);
        button.setBackground(Color.WHITE);
        button.setToolTipText("OK Button");
        mainWindow.add(button, constraints);

        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
