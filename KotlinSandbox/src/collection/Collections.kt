package collection

import java.math.BigDecimal

fun main(args: Array<String>) {
    val people = mapOf(Pair("John", 42), "Jill" to 24)
    val mpeople = mutableMapOf(Pair("John", 42), "Jill" to 24)

    mpeople["Bob"] = 20
    println(mpeople["Bob"])

    val jillsAge = people["Jill"]

    // immutable - covariant
    val ints = listOf(1,2)
    val numbers: List<Number> = ints

    // mutable - invariant
    val mints = mutableListOf(1, 2)
    //val numbers: MutableList<Number> = mints // NG
    val numbers2: List<Number> = mints // OK, it's immutable
    val mnumbers: MutableList<Number> = mutableListOf(1, 0.0, 10f)

    // もしcovariantであると、実体がMutableList<Int>なListにInt以外を追加できてしまうことになる
    // Java array 参照
    mnumbers.add(BigDecimal(10))
}
