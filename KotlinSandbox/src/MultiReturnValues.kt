// 2つの数の和と積を返す関数。戻り値にPair<>を使うのがポイント
fun addAndMul(a: Int, b: Int): Pair<Int, Int> {
    // 戻り値はPairオブジェクト
    return Pair(a + b, a * b)
}

fun main(args: Array<String>) {

    // 分解宣言と多重戻り値 関連サンプル
    // 複数の戻り値を返す（ように見える関数）の呼び出し
    val (wa, seki) = addAndMul(2, 5)
    println("和: $wa, 積: $seki") //「和: 7, 積: 10」

    // 実際には以下のような挙動
    val result = addAndMul(2, 5)
    val wa2 = result.component1()
    val seki2 = result.component2()
    println("和: $wa2, 積: $seki2") //「和: 7, 積: 10」

    // データクラス
    data class Person(val firstName: String, val lastName: String, val age: Int)

    val tanaka = Person("一郎", "田中", 25)
    val (mei, sei, age) = tanaka
    println("姓: $sei, 名: $mei, 年齢: $age") //「姓: 田中, 名: 一郎, 年齢: 25」

    // 順番で制御されている
    val (lastName, firstName, age2) = tanaka
    println("姓: $lastName, 名: $firstName, 年齢: $age2") //「姓: 一郎, 名: 田中, 年齢: 25」

    // 受け側で足りないものは代入されない
    val (mei2, sei2) = tanaka
    println("姓: $sei2, 名: $mei2") //「姓: 田中, 名: 一郎」

    // 受け取る変数の個数が多いとエラーになる
    // val (mei3, sei3, age3, other) = tanaka
}
