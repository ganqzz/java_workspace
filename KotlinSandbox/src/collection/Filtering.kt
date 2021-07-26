package collection

fun main(args: Array<String>) {
    val numbers = (1..100).toList()

    // predicate function
    val mod9 = numbers.filter { it % 9 == 0 }
    val notMod9 = numbers.filterNot { it % 9 == 0 }
    val lastMod9 = numbers.last { it % 9 == 0 }
    val firstMod9 = numbers.first { it % 9 == 0 }

    val firstOver100 = numbers.find { it > 100 }
    val lastUnder50 = numbers.findLast { it < 50 }
    val noNegatives = numbers.none { it < 0 }

    val tmp = numbers.asSequence()
            .filter { it % 9 == 0 }
            .filterNot { it == 36 }

    for (n in tmp) println(n)
}
