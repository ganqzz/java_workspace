package collection

fun main(args: Array<String>) {
    val numbers = 1..100
    val numbers99 = 1 until 100
    val backwards = 100 downTo 1
    val everyOther = numbers step 2
    val alphabet = 'a'..'z'
    fun contains42(range: Iterable<Any>) = 42 in range

    val ranges = listOf(numbers, numbers99, backwards, everyOther, alphabet)
    ranges.forEach(::printRange)
    println(contains42(numbers))
    println(contains42(everyOther))
}

private fun printRange(range: Iterable<Any>) {
    println(range.joinToString(""))
}
