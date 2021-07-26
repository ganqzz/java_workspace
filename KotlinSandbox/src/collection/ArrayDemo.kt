package collection

fun main(args: Array<String>) {

    val array1 = arrayOf("Red", "Green", "Blue")
    for (element in array1) {
        println(element)
    }

    val mixed = arrayOf("A String", 12)
    for (element in mixed) {
        println(element)
    }

    val mixed2: Array<Number> = arrayOf(1.0, 1.0f, 12, 0L) // 指定しないとArray<Any>
    for (element in mixed2) {
        println(element)
    }

    val nulls = arrayOfNulls<String>(3)
    for (element in nulls) {
        println(element)
    }
    nulls[0] = "Red"
    nulls.set(1, "Blue")
    nulls[2] = "Green"
    for (element in nulls) {
        println(element)
    }

    // Primitive Array : Java int[]
    val intArray = intArrayOf(3, 4, 5)
    for (element in intArray) {
        println(element)
    }

    array1.sort()
    for (element in array1) {
        println(element)
    }

    val array4 = array1.sortedArrayDescending()
    for (element in array4) {
        println(element)
    }

    val names = arrayOf("Doi", "Yamada", "Sakurai")
    // 配列からインデックスと要素の両方を取得する: Python enumerate()
    for ((index, elem) in names.withIndex()) {
        println("$index: $elem")
    }

    // lambda
    names.forEachIndexed { index, elem -> println("$index: $elem") }
}
