package demo.gui.examples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;

public class TextEditor extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JToolBar toolBar;
    private JLabel statusBar;
    private JTextArea textArea;
    private JFileChooser fileChooser = new JFileChooser();

    /*
     * 初期化
     */
    TextEditor() {

        // Look&FeelをWindowsモードにする
        onLookAndFeel("Windows");

        // タイトルバーを設定する
        setTitle("新規文書");

        // メニューバーを作成する
        initMenuBar();

        // ツールバーを作成する
        initToolBar();

        // ステータスバーを作成する
        initStatusBar();

        // テキストエリアを作成する
        initTextArea(400, 500);

        // ウィンドウを閉じたときにアプリケーションを終了する
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ウィンドウを適切な大きさにする
        pack();

        // テキストエリアにフォーカスを当てる
        textArea.grabFocus();

        // ウィンドウを可視化する
        setVisible(true);
    }

    // メニューバーを作成する
    private void initMenuBar() {

        // メニューバー
        JMenuBar menuBar = new JMenuBar();
        getRootPane().setJMenuBar(menuBar);

        // [ファイル]
        JMenu menuFile = new JMenu("ファイル(F)");
        menuFile.setMnemonic('F');
        menuBar.add(menuFile);

        // [ファイル]-[新規作成]
        JMenuItem menuNew = new JMenuItem("新規作成(N)");
        menuNew.setMnemonic('N');
        menuNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menuNew.setActionCommand("New");
        menuNew.addActionListener(this);
        menuFile.add(menuNew);

        // [ファイル]-[開く]
        JMenuItem menuOpen = new JMenuItem("開く(O)...");
        menuOpen.setMnemonic('O');
        menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuOpen.setActionCommand("Open");
        menuOpen.addActionListener(this);
        menuFile.add(menuOpen);

        // [ファイル]-[上書き保存]
        JMenuItem menuSave = new JMenuItem("上書き保存(S)");
        menuSave.setMnemonic('S');
        menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuSave.setActionCommand("Save");
        menuSave.addActionListener(this);
        menuFile.add(menuSave);

        // [ファイル]-[名前をつけて保存]
        JMenuItem menuSaveAs = new JMenuItem("名前を付けて保存(A)...");
        menuSaveAs.setMnemonic('A');
        menuSaveAs.setActionCommand("SaveAs");
        menuSaveAs.addActionListener(this);
        menuFile.add(menuSaveAs);

        // [ファイル]-[印刷]
        JMenuItem menuPrint = new JMenuItem("印刷(P)...");
        menuPrint.setMnemonic('P');
        menuPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        menuPrint.setActionCommand("Print");
        menuPrint.addActionListener(this);
        menuFile.add(menuPrint);

        // [ファイル]-[終了]
        JMenuItem menuExit = new JMenuItem("終了(X)");
        menuExit.setMnemonic('X');
        menuExit.setActionCommand("Exit");
        menuExit.addActionListener(this);
        menuFile.add(menuExit);

        // [編集]
        JMenu menuEdit = new JMenu("編集(E)");
        menuEdit.setMnemonic('E');
        menuBar.add(menuEdit);

        // [編集]-[切り取り]
        JMenuItem menuCut = new JMenuItem("切り取り(T)");
        menuCut.setMnemonic('T');
        menuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        menuCut.setActionCommand("Cut");
        menuCut.addActionListener(this);
        menuEdit.add(menuCut);

        // [編集]-[コピー]
        JMenuItem menuCopy = new JMenuItem("コピー(C)");
        menuCopy.setMnemonic('C');
        menuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuCopy.setActionCommand("Copy");
        menuCopy.addActionListener(this);
        menuEdit.add(menuCopy);

        // [編集]-[貼り付け]
        JMenuItem menuPaste = new JMenuItem("貼り付け(P)");
        menuPaste.setMnemonic('P');
        menuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        menuPaste.setActionCommand("Paste");
        menuPaste.addActionListener(this);
        menuEdit.add(menuPaste);

        // [表示]
        JMenu menuView = new JMenu("表示(V)");
        menuView.setMnemonic('V');
        menuBar.add(menuView);

        // [表示]-[ツールバー]
        JCheckBoxMenuItem menuToolBar = new JCheckBoxMenuItem("ツールバー(T)", true);
        menuToolBar.setMnemonic('T');
        menuToolBar.setActionCommand("ToolBar");
        menuToolBar.addActionListener(this);
        menuView.add(menuToolBar);

        // [表示]-[ステータスバー]
        JCheckBoxMenuItem menuStatusBar = new JCheckBoxMenuItem("ステータスバー(S)", true);
        menuStatusBar.setMnemonic('S');
        menuStatusBar.setActionCommand("StatusBar");
        menuStatusBar.addActionListener(this);
        menuView.add(menuStatusBar);

        // [表示]-[見栄え]
        JMenu menuLookFeel = new JMenu("見栄え(L)");
        menuLookFeel.setMnemonic('L');
        menuView.add(menuLookFeel);

        // [表示]-[見栄え]-[Metal]
        JMenuItem menuMetal = new JMenuItem("Metal(M)");
        menuMetal.setMnemonic('M');
        menuMetal.setActionCommand("Metal");
        menuMetal.addActionListener(this);
        menuLookFeel.add(menuMetal);

        // [表示]-[見栄え]-[Windows]
        JMenuItem menuWindows = new JMenuItem("Windows(W)");
        menuWindows.setMnemonic('W');
        menuWindows.setActionCommand("Windows");
        menuWindows.addActionListener(this);
        menuLookFeel.add(menuWindows);

        // [表示]-[見栄え]-[Motif]
        JMenuItem menuMotif = new JMenuItem("Motif(T)");
        menuMotif.setMnemonic('T');
        menuMotif.setActionCommand("Motif");
        menuMotif.addActionListener(this);
        menuLookFeel.add(menuMotif);

        // [ヘルプ]
        JMenu menuHelp = new JMenu("ヘルプ(H)");
        menuHelp.setMnemonic('H');
        menuBar.add(menuHelp);

        // [ヘルプ]-[バージョン情報]
        JMenuItem menuAbout = new JMenuItem("バージョン情報(A)...");
        menuAbout.setMnemonic('A');
        menuAbout.setActionCommand("About");
        menuAbout.addActionListener(this);
        menuHelp.add(menuAbout);
    }

    // ツールバーを作成する
    private void initToolBar() {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        //toolBar.add(new JButton(new ImageIcon("new.gif")));

        JButton btnNew = new JButton("新規");
        JButton btnOpen = new JButton("開く");
        JButton btnSave = new JButton("保存");
        JButton btnAbout = new JButton("？");

        btnNew.setActionCommand("New");
        btnOpen.setActionCommand("Open");
        btnSave.setActionCommand("Save");
        btnAbout.setActionCommand("About");

        btnNew.addActionListener(this);
        btnOpen.addActionListener(this);
        btnSave.addActionListener(this);
        btnAbout.addActionListener(this);

        toolBar.add(btnNew);
        toolBar.add(btnOpen);
        toolBar.add(btnSave);
        toolBar.addSeparator();
        toolBar.add(btnAbout);

        getContentPane().add(toolBar, BorderLayout.NORTH);
    }

    // ステータスバーを作成する
    private void initStatusBar() {
        statusBar = new JLabel("　");
        getContentPane().add(statusBar, BorderLayout.SOUTH);
    }

    // テキストエリアを作成する
    private void initTextArea(int width, int height) {
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(width, height));
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    // イベント処理
    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();
        if (cmd.equals("New")) {
            onNew();
        } else if (cmd.equals("Open")) {
            onOpen();
        } else if (cmd.equals("Save")) {
            onSave();
        } else if (cmd.equals("SaveAs")) {
            onSaveAs();
        } else if (cmd.equals("Print")) {
            // 未実装
        } else if (cmd.equals("Exit")) {
            onExit();
        } else if (cmd.equals("Cut")) {
            onCut();
        } else if (cmd.equals("Copy")) {
            onCopy();
        } else if (cmd.equals("Paste")) {
            onPaste();
        } else if (cmd.equals("ToolBar")) {
            onToolBar(((JCheckBoxMenuItem) ae.getSource()).getState());
        } else if (cmd.equals("StatusBar")) {
            onStatusBar(((JCheckBoxMenuItem) ae.getSource()).getState());
        } else if (cmd.equals("Metal")) {
            onLookAndFeel(cmd);
        } else if (cmd.equals("Windows")) {
            onLookAndFeel(cmd);
        } else if (cmd.equals("Motif")) {
            onLookAndFeel(cmd);
        } else if (cmd.equals("About")) {
            onAbout();
        }
    }

    // [ファイル]-[新規作成]
    private void onNew() {
        fileChooser.setSelectedFile(null);
        setTitle("新規文書");
        textArea.setText("");
    }

    // [ファイル]-[開く]
    private void onOpen() {
        int returnVal = fileChooser.showOpenDialog(this);
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                FileReader fr = new FileReader(file);
                textArea.read(fr, null);
                setTitle(file.getAbsolutePath());
                fr.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // [ファイル]-[上書き保存]
    private void onSave() {
        if (fileChooser.getSelectedFile() == null) {
            onSaveAs();
        } else {
            try {
                FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
                fw.write(textArea.getText());
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // [ファイル]-[名前を付けて保存]
    private void onSaveAs() {
        int returnVal = fileChooser.showSaveDialog(this);
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                FileWriter fw = new FileWriter(fileChooser.getSelectedFile());
                fw.write(textArea.getText());
                fw.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // [ファイル]-[終了]
    private void onExit() {
        System.exit(0);
    }

    // [編集]-[切り取り]
    private void onCut() {
        onCopy();
        textArea.replaceRange(null, textArea.getSelectionStart(), textArea.getSelectionEnd());
    }

    // [編集]-[コピー]
    private void onCopy() {
        Clipboard cb = getToolkit().getSystemClipboard();
        StringSelection strSel = new StringSelection(textArea.getSelectedText());
        cb.setContents(strSel, strSel);
    }

    // [編集]-[貼り付け]
    private void onPaste() {
        Clipboard cb = getToolkit().getSystemClipboard();
        Transferable data = cb.getContents(this);
        if ((data != null) && data.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String str = (String) data.getTransferData(DataFlavor.stringFlavor);
                textArea.replaceRange(str, textArea.getSelectionStart(),
                                      textArea.getSelectionEnd());
            } catch (Exception e) {}
        }
    }

    // [表示]-[ツールバー]
    private void onToolBar(boolean b) {
        toolBar.setVisible(b);
    }

    // [表示]-[ステータスバー]
    private void onStatusBar(boolean b) {
        statusBar.setVisible(b);
    }

    // [表示]-[ルック＆フィール]
    private void onLookAndFeel(String type) {
        String lookAndFeel = "";
        if (type.equals("Metal")) {
            lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
        } else if (type.equals("Windows")) {
            lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        } else if (type.equals("Motif")) {
            lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        }
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    // [ヘルプ]-[バージョン情報]
    private void onAbout() {
        JOptionPane.showMessageDialog(this, "TextEditor 0.1", "TextEditor",
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    // ////////////////////////////////////////////////////////
    // メインルーチン
    // ////////////////////////////////////////////////////////

    // メインルーチン
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextEditor::new);
    }
}
