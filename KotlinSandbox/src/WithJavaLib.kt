import java.text.NumberFormat
import java.util.*

fun main(args: Array<String>) {

    Locale.setDefault(Locale.FRANCE)
    val formatter = NumberFormat.getCurrencyInstance()
    println("Item price = ${formatter.format(9.98)}")
}
