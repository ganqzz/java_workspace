fun main(args: Array<String>) {
    val item = ClothingItem("Shirt", "L", 19.99)
    println(item)

    item.price = 15.99
    item.type = "Pants"
    println(item)
    println(item.price)
    println(item.type)
    item.type = null
    println(item)
    println("Item type = ${item.type}")

    val item2 = ClothingItem("M", 14.99)
    println(item2)
    println("Item type = ${item2.type}")

    item2.price = 10.0
    val f = "%.2f"
    println("Item price = ${f.format(item2.price)}")

    val item3 = ClothingItem("Shirt", "L", 10.0)
    item3.price = 19.99
    println(item3)
    println(item == item3)
}
