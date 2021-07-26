// object expression

fun main(args: Array<String>) {
    // local object
    val point = object {
        var x = 100
        var y = 200
    }
    println("${point.x}, ${point.y}")

    // private object
    val point2 = getPoint()
    println("${point2.x}, ${point2.y}") // must be private to access members
}

private fun getPoint() = object {
    var x = 1
    var y = 2
}
