package demo.gui.misc;

import java.awt.BorderLayout;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class SwingWorkerDemo {

    private static JTextArea taOutput;
    private static JProgressBar progressBar;
    private static JButton button;

    public static void main(String[] args) {
        // ウィンドウ
        final JFrame f = new JFrame("SwingWorker Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // テキストエリア
        taOutput = new JTextArea(15, 20);
        JScrollPane sp = new JScrollPane(taOutput,
                                         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        f.add(sp, BorderLayout.CENTER);

        // プログレスバー
        progressBar = new JProgressBar();
        f.add(BorderLayout.NORTH, progressBar);

        // ボタン
        button = new JButton("開始");
        button.addActionListener(e -> {
            button.setEnabled(false);
            newWorker().execute(); // SwingWorkerは再利用できない
        });
        f.add(BorderLayout.SOUTH, button);

        // ウィンドウ表示
        f.setLocation(200, 100);
        f.pack();
        f.setVisible(true);
    }

    // 実行結果Integer, 処理経過データint[]のSwingWorker
    private static SwingWorker<Integer, int[]> newWorker() {
        SwingWorker<Integer, int[]> worker = new SwingWorker<Integer, int[]>() {
            /**
             * バックグラウンド処理
             * Do not touch the UI
             */
            @Override
            protected Integer doInBackground() throws Exception {
                int sum = 0;
                for (int i = 1; i <= 10; ++i) {
                    sum += i;
                    publish(new int[]{i, sum}); // 結果出力
                    setProgress(i * 10); // 進捗
                    Thread.sleep(200);
                }
                return sum;
            }

            /** 途中経過の表示 */
            @Override
            protected void process(List<int[]> chunks) {
                chunks.forEach(values -> taOutput
                        .append(String.format("%dを足して%d%n", values[0], values[1])));
            }

            /** 処理終了 */
            @Override
            protected void done() {
                try {
                    int result = get(); // 結果
                    taOutput.append("終了。" + result + "でした\n");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                button.setEnabled(true);
            }
        };

        // プログレスバーの処理
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        return worker;
    }
}
