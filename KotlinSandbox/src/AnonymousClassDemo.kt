// object expression

class ClickEvent(val x: Int, val y: Int)

interface ClickListener {
    fun onClick(event: ClickEvent)
}

// using Kotlin interface
class StatefulClass(var listener: ClickListener? = null) {
    fun clickMe(x: Int, y: Int) {
        listener?.onClick(ClickEvent(x, y))
    }
}

// using lambda
class StatefulClass2(var listener: ((ClickEvent) -> Unit)? = null) {
    fun clickMe(x: Int, y: Int) {
        listener?.invoke(ClickEvent(x, y))
    }
}

// using Java interface
class StatefulClass3(var listener: ClickListenerJ? = null) {
    fun clickMe(x: Int, y: Int) {
        listener?.onClick(ClickEvent(x, y))
    }
}

fun main(args: Array<String>) {
    // anonymous class of Kotlin interface
    // SAMの場合は、interfaceではなくlambdaで定義することを考慮する。
    val stateful = StatefulClass(object : ClickListener {
        override fun onClick(event: ClickEvent) {
            println("Click at ${event.x}, ${event.y}")
        }
    })
    println("Listener initialized")
    stateful.clickMe(5, 18)
    stateful.clickMe(45, 57)
    stateful.listener = null
    println("Listener unsubscribed")
    stateful.clickMe(69, 111)
    println("End")

    println()

    // lambda
    val stateful2 = StatefulClass2 { event ->
        println("Click at ${event.x}, ${event.y}")
    }
    println("Listener initialized")
    stateful2.clickMe(5, 18)
    stateful2.clickMe(45, 57)
    stateful2.listener = null
    println("Listener unsubscribed")
    stateful2.clickMe(69, 111)
    println("End")

    println()

    // anonymous class of Java interface
    // SAMの場合は、Lambdaでも記述できる。
    val stateful3 = StatefulClass3(ClickListenerJ { event ->
        println("Click at ${event.x}, ${event.y}")
    })
    println("Listener initialized")
    stateful3.clickMe(5, 18)
    stateful3.clickMe(45, 57)
    stateful3.listener = null
    println("Listener unsubscribed")
    stateful3.clickMe(69, 111)
    println("End")
}
