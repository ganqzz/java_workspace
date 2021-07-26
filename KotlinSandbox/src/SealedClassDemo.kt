// 同じファイル内に派生クラスがすべて存在すること
sealed class ClothingItemS(val type: String) {
    abstract val size: String
    abstract val price: Double
}

data class Shirt(override var size: String,
                 override var price: Double) : ClothingItemS("Shirt")

data class Pants(override var size: String,
                 override var price: Double) : ClothingItemS("Pants")


sealed class Exercise {
  class Run(val numLaps : Int) : Exercise()
  class Swim(val numLaps : Int) : Exercise()
  class Yoga(val numMinutes: Int) : Exercise() 
}

fun main(args: Array<String>) {
    val item1 = Shirt("XL", 19.99)
    val item2 = Pants("32", 24.99)

    val mostExpensive =
            if (item1.price > item2.price) item1 else item2
    println(mostExpensive)

    val instructions = when (mostExpensive) {
        is Shirt -> "Button it!"  // is: instanceof
        is Pants -> "Buckle it!"
    } // no else
    println(instructions)

    println()
    val exercise = Exercise.Run(30)
    println(needMat(exercise))
}

fun needMat(exercise: Exercise) : Boolean {
    return when (exercise) {
        is Exercise.Run -> false
        is Exercise.Swim -> false
        is Exercise.Yoga -> true
    }
}
