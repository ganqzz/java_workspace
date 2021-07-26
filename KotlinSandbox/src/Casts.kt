val x: Any? = "Hello World"

class Obj {
    var x: Any? = "Hello World"
}

fun main(args: Array<String>) {
    if (x is String) {
        println(x.length)
    }

    if (x !is String) {
        println("Not a String")
    } else {
        println(x.length)
    }

    when (x) {
        is String -> println(x.length)
        is Int -> println(x + 1)
    }

    val a = x as String
    val b = x as? String
    val c = x as? String ?: return

    println(c.length)

    // safe cast
    val o = Obj()
    //if (o.x is String) {
    //    println(o.x.length) // mutable property may be changed from another threads
    //}
    (o.x as? String)?.apply {
        println(length)
    }
}
