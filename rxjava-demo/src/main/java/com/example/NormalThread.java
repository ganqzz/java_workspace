package com.example;

// あいさつの言葉を標準出力するサンプル
// No RxJava
public class NormalThread {

	public static void main(String[] args) {
		// 実行しているThread名の取得
		String threadName = Thread.currentThread().getName();
		// データの出力
		System.out.println(threadName + ": Hello, World!");
		System.out.println(threadName + ": こんにちは、世界！");
		// 完了時の出力
		System.out.println(threadName + ": 完了しました");
	}
}
