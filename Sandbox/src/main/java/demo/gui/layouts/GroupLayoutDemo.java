package demo.gui.layouts;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GroupLayoutDemo extends JFrame {

    GroupLayoutDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ButtonModel");

        //　各コンポーネントは垂直グループ、水平グループの両方に属していなければならない
        JLabel lblName = new JLabel("Name");
        JTextField txtName = new JTextField(20);
        JButton button1 = new JButton("Button1");

        JLabel lblAddress = new JLabel("Address");
        JTextField txtAddress = new JTextField(20);
        JButton button2 = new JButton("Button2");

        // GroupLayout の生成
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // 水平グループの定義
        //   列の方向に縦に平行に並んでいる要素を定義していく。
        //+- SequentialGroup ---------------------------------+
        //|+---------------+ +--------------+ +--------------+|
        //||ParallelGroup  | |ParallelGroup | |ParallelGroup ||
        //||+-------------+| |+------------+| |+------------+||
        //|||lblName      || ||txtName     || ||button1     |||
        //|||lblAddress   || ||txtAddress  || ||button2     |||
        //||+-------------+| |+------------+| |+------------+||
        //|+---------------+ +--------------+ +--------------+|
        //+---------------------------------------------------+
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                      .addGroup(layout.createParallelGroup()
                                      .addComponent(lblName)
                                      .addComponent(lblAddress))
                      .addGroup(layout.createParallelGroup()
                                      .addComponent(txtName)
                                      .addComponent(txtAddress))
                      .addGroup(layout.createParallelGroup()
                                      .addComponent(button1)
                                      .addComponent(button2)));

        // 垂直グループの定義
        //   行の方向に、横に平行に並んでいる要素を定義していく。
        //+-SequentialGroup ------------------------------+
        //|+---------------------------------------------+|
        //||              +-----------------------------+||
        //||ParallelGroup |lblName    txtName    button1|||
        //||              +-----------------------------+||
        //|+---------------------------------------------+|
        //|+---------------------------------------------+|
        //||              +-----------------------------+||
        //||ParallelGroup |lblAddress txtAddress button2|||
        //||              +-----------------------------+||
        //|+---------------------------------------------+|
        //+-----------------------------------------------+
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                      .addComponent(lblName)
                                      .addComponent(txtName)
                                      .addComponent(button1))
                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                      .addComponent(lblAddress)
                                      .addComponent(txtAddress)
                                      .addComponent(button2)));

        setLocation(100, 100);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GroupLayoutDemo::new);
    }
}
