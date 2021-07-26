fun main(args: Array<String>) {

    val helloWorld = {
        println("Hello world!")
    }
    helloWorld.invoke()
    helloWorld() // python, javascript like

    val sayHello = { user: String, age: Int ->
        println("Hello, $user, you're $age years old!")
    }
    sayHello("David", 39)

    fun repeat(times: Int, fn: () -> Unit) {
        (1..times).forEach { fn() }
    }

    fun repeatIndexed(times: Int, fn: (Int) -> Unit) {
        (1..times).forEach { index -> fn(index) }
    }

    repeat(4) { println("Hello Kotlin") }
    //repeatIndexed(4) { index -> println("$index. Hello Kotlin") }
    repeatIndexed(4) { println("$it. Hello Kotlin") }

    val states = arrayOf(
        "New York", "New Hampshire", "Vermont",
        "Rhode Island", "Nebraska"
    )
    //val sorted = states.sortedBy { state -> state.length }
    val sorted = states.sortedBy { it.length }
        //val sorted = states.sortedBy(String::length) // method(property) reference
        .filter { it.startsWith("n", true) }

    //for (state in sorted) {
    //    println(state)
    //}
    sorted.forEach(::println) // function reference

    // lambda with receiver
    println(acceptLambdaWithReceiver { toUpperCase().decapitalize() }) // this: String
    println(acceptLambda { it.toUpperCase().decapitalize() })
}

private fun acceptLambdaWithReceiver(fn: String.() -> String): String {
    val s = "sss"
    return fn(s)
}

private fun acceptLambda(fn: (String) -> String): String {
    val s = "sss"
    return fn(s)
}
