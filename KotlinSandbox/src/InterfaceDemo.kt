interface Animal {
    var fur: String
    fun speak()
}

interface Dog : Animal {
    override fun speak() { // default implementation
        println("Woof!")
    }
}

interface Cat : Animal {
    override fun speak() { // default implementation
        println("Meow!")
    }
}

// resolve multiple inheritance
class Retriever : Dog, Cat {
    override var fur: String
        get() = "golden"
        set(_) {} // erase unused warning

    override fun speak() {
        super<Dog>.speak()
        super<Cat>.speak()
    }
}

fun main(args: Array<String>) {
    val buster = Retriever()
    //buster.speak()
    makeItTalk(buster)
    reportBreed("Buster", buster)
}

fun makeItTalk(dog: Retriever) {
    dog.speak()
}

fun reportBreed(name: String, dog: Dog) {
    println("$name is a ${dog::class.simpleName}")
    println("This dog's fur is ${dog.fur}")
}
