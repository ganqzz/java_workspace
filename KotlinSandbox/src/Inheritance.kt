// open: extendable
open class SuperClass(protected val anInt: Int) {

    open fun multiply(factor: Int): Int {
        return anInt * factor
    }

    override fun toString(): String {
        return "${this::class.simpleName} {antInt: $anInt}"
    }
}

class SubClass(anInt: Int) : SuperClass(anInt) {
    override fun multiply(factor: Int): Int {
        return super.multiply(factor) * factor
    }
}

abstract class Base(var x: Int) {
    abstract var p: String
    var y: Int? = null

    constructor(a: Any) : this(a as Int)
    constructor(a: Any, b: Any) : this(b as Int) {
        y = a as Int?
    }

    fun print() {
        println("${x}, ${y}")
    }
}

// no primary constructor
class Derived : Base {
    override var p
        get() = ""
        set(_) {}

    // セカンダリコンストラクタはsuperキーワードで直接基底クラスのコンストラクタを呼び出す
    constructor(a: Any) : super(a) {}

    // 同上
    constructor(a: Any, b: Any) : super(a, b) {}
}

fun main(args: Array<String>) {

    val sup = SuperClass(42)
    println(sup)

    val sub = SubClass(53)
    println(sub)

    println(sup.multiply(100))
    println(sub.multiply(100))

    val der1 = Derived(1)
    println(der1)
    der1.print()

    val der2 = Derived(2, 4)
    println(der2)
    der2.print()
}
