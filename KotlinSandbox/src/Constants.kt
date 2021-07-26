class Constants {
    // Singleton object
    // Java staticの代わり
    companion object { // "Companion"
        const val RED = "Red"
        fun func() = "func"
        @JvmStatic
        fun func2() = "func2"
    }
}

// package level
const val BLUE = "Blue"

// Singleton object
object Abc {
    const val a: Int = 1
    var b: String? = null
}
