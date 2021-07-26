// 自作の複素数クラス（整数のみ対応）
data class Complex(val real: Int, val image: Int) {
    // operatorキーワードを使って+演算子をオーバーロード
    operator fun plus(value: Complex): Complex {
        return Complex(real + value.real, image + value.image)
    }

    // -演算子（2項）
    operator fun minus(value: Complex): Complex {
        return Complex(real - value.real, image - value.image)
    }

    // -演算子（単項）
    operator fun unaryMinus(): Complex {
        return Complex(-real, -image)
    }

    // 複素数的な表記を返すように文字列化
    override fun toString(): String {
        if (image > 0)
            return "$real + ${image}i"
        else
            return "$real ${image}i"
    }
}

fun main(args: Array<String>) {

    // 複素数クラスを使ってみる
    val c1 = Complex(2, 2)  // 2 + 2i
    val c2 = Complex(-1, 5)  // -1 + 5i
    val c3 = c1 + c2 //普通に+演算子が使える

    println(c3)      //加算結果     「1 + 7i」
    println(c1 - c2) //減算結果     「3 -3i」
    println(-c1)     //単項の-演算子「-2 -2i」
}
