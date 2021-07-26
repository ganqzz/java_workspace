package collection

val numbers: List<Number> = listOf(1, 2, -.0, 2.4f)

inline fun <reified T : Number> numbersOfType() = numbers.mapNotNull { it as? T } // instanceof

fun main(args: Array<String>) {
    val ints = numbersOfType<Int>()
    val doubles: List<Double> = numbersOfType()
    val floats = numbersOfType<Float>()
    val bytes = numbersOfType<Byte>()

    println(ints)
    println(doubles)
    println(floats)
    println(bytes)
}
