fun main(args: Array<String>) {
    // 1～10の範囲
    for (i in 1..10) {
        print(i) //「12345678910」
    }

    println()

    // 2～10の範囲で2刻み
    for (i in 2..10 step 2) {
        print(i) //「246810」
    }

    println()

    // 9～1の範囲（逆順）で2刻み
    for (i in 9 downTo 1 step 2) {
        print(i) //「97531」
    }

    println()

    // 1～10の範囲。ただし終わりの値（10）は含まない
    for (i in 1 until 10) { // Python range(1, 10)
        print(i) //「123456789」
    }

    println()

    // A～zの範囲（Charは整数型）
    for (i in 'A'..'z') {
        print(i)
    }

    println()

    // a～zの範囲で2刻み
    for (i in 'a' until 'z' step 2) {
        print(i)
    }
}
