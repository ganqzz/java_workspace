package collection

fun main(args: Array<String>) {

    val colorMap = mapOf<String, Long>(
            Pair("Red", 0xFF0000),
            Pair("Green", 0x00FF00),
            "Blue" to 0x0000FF // Pair's infix fun
    )
    println(colorMap)
    println(colorMap::class)

    val stateMap = mutableMapOf<String, String>()
    stateMap["CA"] = "Sacramento"
    stateMap.put("OR", "Salem")
    stateMap["WA"] = "Olympia"
    println(stateMap)
    println(stateMap::class)

    for (state in stateMap.keys) {
        println("The capital of $state is ${stateMap[state]}")
    }
    for (pair in stateMap) {
        println("The capital of ${pair.key} is ${pair.value}")
    }
    for ((state, capital) in stateMap) { // destructuring
        println("The capital of $state is $capital")
    }
}
