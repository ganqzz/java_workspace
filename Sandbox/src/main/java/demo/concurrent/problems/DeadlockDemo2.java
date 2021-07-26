package demo.concurrent.problems;

public class DeadlockDemo2 {

    private static class Res {
        private Res that;
        private String n;

        Res(String n) {
            this.n = n;
        }

        // 自身に対してロック
        synchronized void method1() {
            try { // 最近のマシンのためのウェイトを入れる
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ": " + n + ".method1()");
            that.method2();
        }

        // 自身に対してロック
        synchronized void method2() {
            System.out.println(Thread.currentThread().getName() + ": " + n + ".method2()");
        }
    }

    public static void main(String[] args) {
        // オブジェクトの作成
        Res a = new Res("a");
        Res b = new Res("b");
        a.that = b;
        b.that = a;

        // スレッドの作成
        Thread t1 = new Thread(a::method1, "t1");
        Thread t2 = new Thread(b::method1, "t2");

        t1.start();
        t2.start();

        // スレッドの完了を待つ
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // メッセージの表示
        System.out.println("Done!");
    }
}
