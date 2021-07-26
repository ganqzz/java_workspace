fun equalsDemo() {
    val num1 = 10
    val num2 = 15

    val match = (num1 == num2)
    println("Match = $match")

    val match2 = num1.equals(num2)
    println("Match2 = $match2")

    println("Comparison result = ${num1.compareTo(num2)}")

    val x: Any? = null
    println(x == null) // optimized to ===
}

fun operator() {
    var num1 = 15
    val num2 = 10

    println("num1: $num1, num2: $num2")

    val sum = num1 + num2
    println("sum: $sum")
    val sum2 = num1.plus(num2)
    println("sum2: $sum2")

    val diff = num1 - num2
    println("diff: $diff")

    println("num1++: ${num1++}")
    println("num1: $num1")

    println("num1.inc(): ${num1.inc()}") // not mutate
    println("num1: $num1")

    println("++num1: ${++num1}")
    println("num1: $num1")

    println("num1--: ${num1--}")
    println("num1: $num1")

    println("num1.dec(): ${num1.dec()}") // not mutate
    println("num1: $num1")

    println("--num1: ${--num1}")
    println("num1: $num1")
}

fun conditional() {
    val a = 10
    val ret = if (a >= 10) "Big" else "Small"
    println(ret)
    // ifを式として使う場合、elseは必須

    val value = 100
    when (value) {
        100 -> println("100!")
        200 -> println("200!!")
        300 -> println("300!!!")
        else -> println("ELSE")
    }

    val str = "aaa"
    when (str) {
        "aaa" -> println("aaa!")
        "bbb" -> println("bbb!!")
        else -> println("ELSE")
    }

    val value2 = 13
    when (value2) {
        in 1..10 -> println("1..10")   // 1以上10以下。inと..を使って範囲指定
        11, 12 -> println("11 or 12")  // 11か12。カンマで複数指定
        in 13..20 -> println("13..20") // 13以上20以下
        !in 1..20 -> println("ELSE")   // 「1以上20以下」以外。!を使って条件反転
    }

    fun isEven(value: Int) = value % 2 == 0

    val value3 = 12
    val value4 = 5
    when { // 引数無しのwhen
        value3 < 20 -> println("value3 < 20") // 任意の条件式を書ける
        isEven(value3) -> println("Even")     // 条件には関数なども書ける
        value4 > 0 -> println("value4 > 0")
        else -> println("ELSE")
    }

    val value5 = "Doi"
    val value6 = when (value5) {
        "Doi" -> "土井"
        "Yamada" -> "山田"
        else -> "それ以外の人" // elseがないとコンパイル時にエラー
    }
    println(value6)
}


fun main(args: Array<String>) {
    println(BLUE) // package level
    println(Constants.Companion.RED)
    println(Constants.RED)

    println("---")
    equalsDemo()

    println("---")
    operator()

    println("---")
    conditional()
}
