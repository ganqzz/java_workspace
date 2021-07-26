fun main(args: Array<String>) {
    // 1～10の範囲
    for (i in 1..10) {
        print(i) // 12345678910
    }
    println()

    // Range/Progression
    val numbers = 1..100

    val colors = arrayOf("Red", "Green", "Blue")
    val values = intArrayOf(1, 3, 5, 7, 9)

    printHeader("For each loop")
    for (color in colors) {
        println(color)
    }
    for (value in values) {
        println(value)
    }

    printHeader("For loop with indices")
    for (i in values.indices step 2) {
        println(values[i])
    }

    printHeader("For loop with range")
    for (i in 0..colors.size - 1) {
        println(colors[i])
    }
    for (i in 0 until colors.size) { // 同上
        println(colors[i])
    }

    // Break to labels
    outer@ for (i in 1..100) {
        for (j in 1..10) {
            if (i == 6 && j == 5)
                break@outer
            println("i=$i, j=$j")
        }
    }

    val states = arrayOf("CA", "OR", "WA")

    printHeader("While loop")
    var counter = 0
    while (counter < states.size) {
        println("test04.Counter = $counter")
        println("State = ${states[counter]}")
        counter++
    }

    counter = 0
    printHeader("Do / While loop")
    do {
        println("State = ${states[counter]}")
        counter++
    } while (counter < states.size)
}

private fun printHeader(label: String) {
    println("***************")
    println(label)
    println("***************")
}
