// Singleton
object Config {
    // 設定ファイル読み書きダミー実装用Map
    val map = mutableMapOf<String, String>()

    fun read(key: String): String {
        return map[key] ?: ""
    }

    fun write(key: String, value: String) {
        map[key] = value
    }
}

fun main(args: Array<String>) {
    // シングルトンオブジェクトの使用例
    // Configはクラスではなくオブジェクトなので、そのままメソッドを呼び出せる
    Config.write("test2", "value2") // 設定書き込み
    val value = Config.read("test2") // 設定読み込み
    println(value)
}
