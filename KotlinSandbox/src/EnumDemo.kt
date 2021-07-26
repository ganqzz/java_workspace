import java.lang.Double.NaN

// Simple Enum
enum class Operation(val operator: String) {
    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), NOOP("")
}

class MathLib {
    // Kotlin "static" way
    companion object {
        fun addValues(number1: Double, number2: Double) = number1 + number2
        fun subtractValues(number1: Double, number2: Double) = number1 - number2
        fun multiplyValues(number1: Double, number2: Double) = number1 * number2
        fun divideValues(number1: Double, number2: Double) = number1 / number2
    }
}

// Polymorphic Enum
enum class Operation2(val operator: String) {
    ADD("+") {
        override fun apply(number1: Double, number2: Double) = number1 + number2
    },
    SUBTRACT("-") {
        override fun apply(number1: Double, number2: Double) = number1 - number2
    },
    MULTIPLY("*") {
        override fun apply(number1: Double, number2: Double) = number1 * number2
    },
    DIVIDE("/") {
        override fun apply(number1: Double, number2: Double) = number1 / number2
    },
    NOOP("") {
        override fun apply(number1: Double, number2: Double) = NaN
    };

    abstract fun apply(number1: Double, number2: Double): Double

    companion object {
        fun get(operator: String): Operation2 {
            return when (operator) {
                ADD.operator -> ADD
                SUBTRACT.operator -> SUBTRACT
                MULTIPLY.operator -> MULTIPLY
                DIVIDE.operator -> DIVIDE
                else -> throw Exception("Unknown operation")
            }
        }
    }
}


const val OPERATIONS = "+ - * /"

fun getInput(prompt: String): Double {
    print(prompt)
    val string: String? = readLine()
    val number = string!!.toBigDecimal()
    return number.toDouble()
}

fun main2(args: Array<String>) {
    try {
        val number1 = getInput("Number 1: ")
        val number2 = getInput("Number 2: ")

        print("Select an operation ($OPERATIONS): ")
        val operation = readLine()

        val result: Double? =
                when (operation) {
                    Operation.ADD.operator -> MathLib.addValues(number1, number2)
                    Operation.SUBTRACT.operator -> MathLib.subtractValues(number1, number2)
                    Operation.MULTIPLY.operator -> MathLib.multiplyValues(number1, number2)
                    Operation.DIVIDE.operator -> MathLib.divideValues(number1, number2)
                    else -> throw Exception("Unknown operation")
                }
        println("The answer is $result")
    } catch (e: NumberFormatException) {
        println("${e.message} is not a number")
    } catch (e: Exception) {
        println(e.message)
    }
}

fun main(args: Array<String>) {
    try {
        val number1 = getInput("Number 1: ")
        val number2 = getInput("Number 2: ")

        print("Select an operation ($OPERATIONS): ")
        val operation = readLine()!!

        val result: Double = Operation2.get(operation).apply(number1, number2)
        println("The answer is $result")
    } catch (e: NumberFormatException) {
        println("${e.message} is not a number")
    } catch (e: Exception) {
        println(e.message)
    }
}
