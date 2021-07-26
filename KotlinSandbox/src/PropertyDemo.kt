abstract class PropertyDemoBase {
    abstract var x: String
}

class Fuga : PropertyDemoBase() {
    override var x: String = "fuga"
        get() = field.toUpperCase()
        set(value) {
            field = value.trim()
        }

    var a: Int = 4
        private set
}

// 時計クラスの定義
class Clock(var hour: Int = 0,
            var minute: Int = 0,
            var second: Int = 0) { // 時・分・秒のプロパティ

    // 00:00:00から何秒経過したかを取得・設定するプロパティ
    var pastSeconds: Int
        get() { // getキーワードで取得の際のロジックを記述
            return (hour * 60 + minute) * 60 + second
        }
        set(value) {
            hour = value / 3600
            minute = (value % 3600) / 60
            second = value % 60
        }

    fun print() { // 現在のプロパティの値を表示
        // pastSecondsプロパティはgetterロジックを実行
        println("${hour}時${minute}分${second}秒, 0時から${pastSeconds}秒経過")
    }
}

fun main(args: Array<String>) {
    val clock = Clock()
    // 普通にプロパティに値を設定。ここはフィールドと同様
    clock.hour = 1
    clock.minute = 10
    clock.second = 5
    clock.print() // 「1時10分5秒, 0時から4205秒経過」が出力される
    // pastSecondsプロパティに値を設定。setterロジックを実行
    clock.pastSeconds = 4101
    clock.print() // 「1時8分21秒, 0時から4101秒経過」が出力される

    // data class
    data class Point(var x: Int, var y: Int)

    val point = Point(x = 1, y = 2)
    println(point)
    point.x *= 10
    point.y *= 10
    println(point)
}
