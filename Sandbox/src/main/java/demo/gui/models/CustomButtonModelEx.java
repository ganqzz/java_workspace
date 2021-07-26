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

public class CustomButtonModelEx extends JFrame {

    private JButton okBtn;
    private JLabel enabledLbl;
    private JLabel pressedLbl;
    private JLabel armedLbl;
    private JCheckBox checkBox;

    public CustomButtonModelEx() {
        okBtn = new JButton("OK");
        checkBox = new JCheckBox();
        checkBox.setAction(new CheckBoxAction());

        enabledLbl = new JLabel("Enabled: true");
        pressedLbl = new JLabel("Pressed: false");
        armedLbl = new JLabel("Armed: false");

        OkButtonModel model = new OkButtonModel();
        okBtn.setModel(model);

        createLayout(okBtn, checkBox, enabledLbl, pressedLbl, armedLbl);

        setTitle("Custom button model");
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

    // hookして、副作用的な動作を追加
    private class OkButtonModel extends DefaultButtonModel {

        @Override
        public void setEnabled(boolean b) {
            enabledLbl.setText("Enabled: " + b);
            super.setEnabled(b);
        }

        @Override
        public void setArmed(boolean b) {
            armedLbl.setText("Armed: " + b);
            super.setArmed(b);
        }

        @Override
        public void setPressed(boolean b) {
            pressedLbl.setText("Pressed: " + b);
            super.setPressed(b);
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
        EventQueue.invokeLater(CustomButtonModelEx::new);
    }
}
