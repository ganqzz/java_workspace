fun addValues(param1: Double, param2: Double): Double = param1 + param2

fun calcValues(param1: Double, param2: Double, op: String = "+"): Double = when (op) {
    "+" -> param1 + param2
    "-" -> param1 - param2
    else -> -1.0
}

fun returnLastThing() { // return String
    "Hoge"
}

fun changeSomething(param: Double) { // return Unit
//    param ++
    var copy = param
    copy++
    println("Copy is $copy") // Unit
}

fun addValues(vararg numbers: Int): Int {
    var result = 0
    for (i in numbers) {
        result += i
    }
    return result
}

data class Point(val x: Double = 0.0, val y: Double = 0.0)

private infix fun Number.by(n: Number) = Point(this.toDouble(), n.toDouble())


fun main(args: Array<String>) {
    val num1 = 4.0
    val num2 = 3.5

    val result = addValues(param2 = num2, param1 = num1)
    println("the result is $result")

    val result2 = calcValues(num1, num2, "-")
    println("Result 2 is $result2")

    changeSomething(5.0)

    val sum: Int = addValues(1, 3, 5, 7)
    println("Sum=$sum")

    // Single-Expression function
    fun getName(): String? = "hoge"
    println(getName())

    // infix
    val c = 2 by 4
    val d = 2.54 by 4
    val e = 2.54.by(4)
    println(c)
}
