class ClothingItem(
    type: String?, // not property, only on initializing
    val size: String, // read (getter) only property
    price: Double
) {

    // initialize block
    init {
        //
    }

    var type = type
        get() = field ?: "Unknown"

    var price = price
        set(value) {
            field = value * .9
        }

    // secondary constructor
    constructor(size: String, price: Double) : this(null, size, price)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClothingItem

        if (type != other.type) return false
        if (size != other.size) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type?.hashCode() ?: 0
        result = 31 * result + size.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }

    override fun toString(): String {
        return "ClothingItem(type=$type, size='$size', price=$price)"
    }
}
