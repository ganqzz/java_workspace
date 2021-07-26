package demo.gui.models;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultButtonModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonModelEx extends JFrame {

    private JButton okBtn;
    private JLabel enabledLbl;
    private JLabel pressedLbl;
    private JLabel armedLbl;
    private JCheckBox checkBox;

    public ButtonModelEx() {
        okBtn = new JButton("OK");
        okBtn.addChangeListener(new DisabledChangeListener());
        checkBox = new JCheckBox();
        checkBox.setAction(new CheckBoxAction());

        enabledLbl = new JLabel("Enabled: true");
        pressedLbl = new JLabel("Pressed: false");
        armedLbl = new JLabel("Armed: false");

        createLayout(okBtn, checkBox, enabledLbl, pressedLbl, armedLbl);

        setTitle("ButtonModel");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createLayout(JComponent... arg) {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                                .addGroup(gl.createSequentialGroup()
                                            .addComponent(arg[0])
                                            .addGap(80)
                                            .addComponent(arg[1]))
                                .addGroup(gl.createParallelGroup()
                                            .addComponent(arg[2])
                                            .addComponent(arg[3])
                                            .addComponent(arg[4]))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                              .addGroup(gl.createParallelGroup()
                                          .addComponent(arg[0])
                                          .addComponent(arg[1]))
                              .addGap(40)
                              .addGroup(gl.createSequentialGroup()
                                          .addComponent(arg[2])
                                          .addComponent(arg[3])
                                          .addComponent(arg[4]))
        );

        pack();
    }

    private class DisabledChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            // modelを通してstate変更を読み出す
            DefaultButtonModel model = (DefaultButtonModel) okBtn.getModel();

            enabledLbl.setText("Enabled: " + model.isEnabled());
            armedLbl.setText("Armed: " + model.isArmed());
            pressedLbl.setText("Pressed: " + model.isPressed());
        }
    }

    private class CheckBoxAction extends AbstractAction {

        public CheckBoxAction() {
            super("Disabled");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            okBtn.setEnabled(!okBtn.isEnabled());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(ButtonModelEx::new);
    }
}
