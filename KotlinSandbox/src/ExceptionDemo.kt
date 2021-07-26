import java.net.InetAddress
import java.net.UnknownHostException

fun main(args: Array<String>) {
    try {
        print("Value 1: ")
        val value1 = readLine()
        val d1 = value1!!.toDouble()

        print("Value 2: ")
        val value2 = readLine()
        val d2 = value2!!.toDouble()

        val sum = d1 + d2
        println("Answer: $sum")
    } catch (e: KotlinNullPointerException) {
        println("Value was null")
    } catch (e: NumberFormatException) {
        println("${e.message} is not a number")
    }

    // expression
    fun isValidIP(host: String) = try {
        InetAddress.getByName(host)
        true
    } catch (ex: UnknownHostException) {
        false
    }

    println(isValidIP("localhost"))
    println(isValidIP("hogehoge"))
}
