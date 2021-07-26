class NestedClassDemo() {
    private val num: Int = 1
    lateinit var subject: String
    val fuga: Fuga = Fuga()

    class Hoge { // "static class" in Java
        fun foo(): Any {
            val fefe: NestedClassDemo = NestedClassDemo()
            return fefe.num
        }
    }

    inner class Fuga { // "class" in Java
        val fefe: NestedClassDemo = this@NestedClassDemo // enclosing class instance
        val i = num
        val s = subject
        fun foo() = num * 2
        fun bar() {
            subject += "Fuga~"
        }
    }
}

fun main(args: Array<String>) {
    val nc = NestedClassDemo()
    println(nc.fuga.s)
    println(nc.fuga.s.length) // UninitializedPropertyAccessException
}
