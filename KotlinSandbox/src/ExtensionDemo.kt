class Hoge(var name: String) {
    var age = 10
}

// 拡張関数
fun Hoge.run() {
    // 拡張関数の中では、プロパティの参照は可能
    println("${this.name} is Running!")
    // エラー。privateプロパティは参照できない
    //println("${this.name}'s age is ${this.age}")
}

// Extension property
var Hoge.hoge: String
    get() = name
    set(value) {
        name += ":hoge"
    }

fun main(args: Array<String>) {

    // 拡張関数 関連サンプル
    val cat = Hoge("Tama")
    //　拡張関数の呼び出し例。
    cat.run() //「Tama is Running!」

    // 拡張関数。Stringクラスにdumpというメソッドを追加する（ように見せる）
    fun String.dump() {
        println("Length: ${this.length}, Content: $this")
    }

    // Stringクラスに拡張関数として追加したdumpメソッドを呼ぶ
    "Hello".dump() //「Length: 5, Content: Hello」
}
