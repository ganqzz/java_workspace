package demo.concurrent.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class VisualizeSynchronization extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static final int LINES = 10;
    private final Map<String, DemoPanel> demos = new LinkedHashMap<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(LINES);
    private String current; // command
    private DemoPanel currentDemoPanel;
    private List<Future<?>> futures;
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton stopButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VisualizeSynchronization::new);
    }

    VisualizeSynchronization() {
        setTitle("Synchronization demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 360) / 2, (screenSize.height - 360) / 2, 360, 360);
        setResizable(false);

        createDemo();
        createMenu();
        createButtonPanel();

        add(new Label("Select a Demo", Label.CENTER), BorderLayout.CENTER);
        setVisible(true);
    }

    private void createDemo() {
        demos.put("Lock", new LockDemoPanel());
        demos.put("ReadWriteLock", new ReadWriteLockDemoPanel());
        demos.put("Semaphore", new SemaphoreDemoPanel());
        demos.put("CyclicBarrier", new CyclicBarrierDemoPanel());
        demos.put("CountDownLatch", new CountDownLatchDemoPanel());
    }

    private void createMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Demo");
        menubar.add(menu);
        for (String command : demos.keySet()) {
            menu.add(createMenuItem(command));
        }
        setJMenuBar(menubar);
    }

    private JMenuItem createMenuItem(String command) {
        JMenuItem item = new JMenuItem(command);
        item.addActionListener(this);
        return item;
    }

    private void createButtonPanel() {
        buttonPanel = new JPanel();
        startButton = new JButton("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(e -> startTasks());
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            if (stopButton.getText().equals("Stop")) stopTasks();
            else if (stopButton.getText().equals("Reset")) resetTasks();
        });
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    private void startTasks() {
        startButton.setEnabled(false);
        //stopButton.setEnabled(true);
        stopButton.setText("Stop");
        futures = currentDemoPanel.tasks
                .stream()
                .map(task -> pool.submit(task))
                .collect(Collectors.toList());

        // no blocking the UI thread
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // 全完了まで待つ
                futures.forEach(future -> {
                    try {
                        future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }

            @Override
            protected void done() {
                //startButton.setEnabled(true);
                futures = null;
                stopButton.setText("Reset");
            }
        }.execute();
    }

    private void stopTasks() {
        if (futures != null) {
            futures.forEach(future -> future.cancel(true));
            futures = null;
        }
        stopButton.setText("Reset");
    }

    private void resetTasks() {
        currentDemoPanel.refresh();
        startButton.setEnabled(true);
        stopButton.setEnabled(true);
        stopButton.setText("Reset");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selected = e.getActionCommand();
        System.out.println(selected);
        if (!selected.equals(current)) {
            current = selected;
            currentDemoPanel = demos.get(current);
            stopTasks();
            resetTasks();
            setTitle(current);
            getContentPane().removeAll(); // getContentPane()は必要
            add(currentDemoPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.PAGE_END);
            validate(); // important
        }
    }

    private static abstract class DemoPanel extends JPanel {
        String command;
        protected final List<DemoTask> tasks = new ArrayList<>(LINES);

        DemoPanel() {
            setLayout(new GridLayout(0, 1));
        }

        // Taskが子クラスのインナークラスなので、
        // このクラスのコンストラクタ内でTaskインスタンスを直に作成することはできない
        protected void createTasks(Supplier<DemoTask> demoFactory) {
            for (int i = 0; i < LINES; ++i) {
                DemoTask task = demoFactory.get();
                add(task);
                tasks.add(task);
            }
        }

        protected void refresh() {
            tasks.forEach(DemoTask::init);
        }
    }

    private static abstract class DemoTask extends JTextField implements Runnable {
        int speed;

        DemoTask() {
            init();
        }

        protected abstract void init();

        protected void denGo() throws InterruptedException {
            setBackground(Color.PINK);
            for (int i = 0; i < 50; ++i) {
                Thread.sleep(speed);
                setText(" " + getText());
            }
            setBackground(Color.WHITE);
        }
    }

    private static class LockDemoPanel extends DemoPanel {
        Lock lock;

        LockDemoPanel() {
            lock = new ReentrantLock(true); // 先着順
            createTasks(LockTask::new);
        }

        @Override
        protected void refresh() {
            lock = new ReentrantLock(true); // 先着順
            super.refresh();
        }

        private class LockTask extends DemoTask {

            @Override
            public void init() {
                speed = (int) (Math.random() * 15 + 15);
                setText("電車>");
                setBackground(Color.WHITE);
            }

            @Override
            public void run() {
                try {
                    denGo();
                    lock.lock(); // ここから先は1つしか同時に進めない
                    denGo();
                } catch (InterruptedException e) {
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class ReadWriteLockDemoPanel extends DemoPanel {
        ReadWriteLock rwlock;

        ReadWriteLockDemoPanel() {
            rwlock = new ReentrantReadWriteLock(true); // 先着順
            createTasks(ReadWriteLockTask::new);
        }

        @Override
        protected void refresh() {
            rwlock = new ReentrantReadWriteLock(true); // 先着順
            super.refresh();
        }

        private class ReadWriteLockTask extends DemoTask {
            Lock lock; // read or write

            @Override
            public void init() {
                speed = (int) (Math.random() * 20 + 20);
                if (Math.random() < .25) {
                    setText("か電車>");
                    setForeground(Color.RED);
                    lock = rwlock.writeLock();
                } else {
                    setText("電車>");
                    setForeground(Color.BLACK);
                    lock = rwlock.readLock();
                }
                setBackground(Color.WHITE);
            }

            @Override
            public void run() {
                try {
                    denGo();
                    lock.lock();
                    denGo();
                } catch (InterruptedException e) {
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class SemaphoreDemoPanel extends DemoPanel {
        Semaphore semaphore;

        SemaphoreDemoPanel() {
            semaphore = new Semaphore(3, true); // 先着順
            createTasks(SemaphoreTask::new);
        }

        @Override
        protected void refresh() {
            semaphore = new Semaphore(3, true); // 先着順
            super.refresh();
        }

        private class SemaphoreTask extends DemoTask {

            @Override
            public void init() {
                speed = (int) (Math.random() * 20 + 20);
                setText("電車>");
                setBackground(Color.WHITE);
            }

            @Override
            public void run() {
                try {
                    denGo();
                    semaphore.acquire(); // ここから先は同時に3つまで
                    denGo();
                } catch (InterruptedException e) {
                } finally {
                    semaphore.release();
                }
            }
        }
    }

    private static class CyclicBarrierDemoPanel extends DemoPanel {
        CyclicBarrier barrier;

        CyclicBarrierDemoPanel() {
            barrier = new CyclicBarrier(LINES);
            createTasks(CyclicBarrierTask::new);
        }

        @Override
        protected void refresh() {
            barrier = new CyclicBarrier(LINES);
            super.refresh();
        }

        private class CyclicBarrierTask extends DemoTask {

            @Override
            public void init() {
                speed = (int) (Math.random() * 20 + 20);
                setText("電車>");
                setBackground(Color.WHITE);
            }

            @Override
            public void run() {
                try {
                    denGo();
                    barrier.await(); // みんなそろうまで進めない
                    Thread.sleep(500);
                    denGo();
                } catch (InterruptedException | BrokenBarrierException e) {
                }
            }
        }
    }

    private static class CountDownLatchDemoPanel extends DemoPanel {
        int expressCount;
        CountDownLatch latch;

        CountDownLatchDemoPanel() {
            createTasks(CountDownLatchTask::new);
            latch = new CountDownLatch(expressCount);
        }

        @Override
        protected void refresh() {
            expressCount = 0;
            super.refresh();
            latch = new CountDownLatch(expressCount);
        }

        private class CountDownLatchTask extends DemoTask {
            boolean express;

            @Override
            public void init() {
                express = Math.random() < .25;
                if (express) {
                    speed = (int) (Math.random() * 10 + 10);
                    setText("特急>");
                    setForeground(Color.RED);
                    expressCount++;
                } else {
                    speed = (int) (Math.random() * 20 + 20);
                    setText("普通>");
                    setForeground(Color.BLACK);
                }
                setBackground(Color.WHITE);
            }

            @Override
            public void run() {
                try {
                    if (express) {
                        Thread.sleep((int) (Math.random() * 500 + 1300));
                        denGo();
                        denGo();
                        latch.countDown(); // 全特急が終点に到達したら、鈍行が動き出す
                    } else {
                        denGo();
                        latch.await(); // 特急通過待ち
                        denGo();
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
