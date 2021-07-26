package collection

fun main(args: Array<String>) {

    // immutable
    val anyList = listOf("Red", "Green", "Blue", 5, 2.3, true)
    println(anyList::class)

    val colorList = listOf("Red", "Green", "Blue")
    println(colorList)

    println("Number of colors: ${colorList.size}")
    println(colorList::class.simpleName)

    val colorList2 = mutableListOf<String>()
    colorList2.add("Red")
    colorList2.add("Green")
    colorList2.add("Blue")
    println(colorList2)

    colorList2.sort()
    println(colorList2)

    val sortedList = colorList2.sortedDescending()  // 元はそのまま
    println(sortedList)
    println(colorList2)

    colorList2.removeAt(0)
    println(colorList2)

    colorList2.remove("Red")
    println(colorList2)
}
