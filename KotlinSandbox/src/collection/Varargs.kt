package collection

fun printNames(vararg names: String) {
    for (name in names) println(name)
}

fun greetNames(vararg names: String, greeting: String) {
    for (name in names) println("$greeting $name")
}

fun greetNamesWithHeading(vararg names: String, heading: String) {
    println("=== $heading ===")
    printNames(*names) // spread(unpack)
}

fun greetNamesWithHeading(names: List<String>, heading: String) {
    println("=== $heading ===")
    printNames(*names.toTypedArray())
}

fun main(args: Array<String>) {
    printNames("John", "Jill", "Bob")
    printNames(*arrayOf("John", "Jill", "Bob"))
    greetNames("John", "Jill", "Bob", greeting = "Hello")
    greetNamesWithHeading("John", "Jill", "Bob", heading = "List of names:")
    greetNamesWithHeading(listOf("John", "Jill", "Bob"), heading = "List of names:")
}
