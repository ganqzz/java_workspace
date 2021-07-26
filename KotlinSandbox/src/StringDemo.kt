fun main(args: Array<String>) {
    var aString = "Hello!"
    println(aString)
    println(aString[3])

    val empty = String()
    println("'$empty'")

    val charArray = aString.toCharArray()
    println(charArray.toList())
    val byteArray = aString.toByteArray()
    println(byteArray.toList())

    val len = aString.length
    for (i in 0 until len) {
        print(aString.get(i))
        println(aString[i]) // operator overload
    }
    for (c in aString) {
        println(c)
    }

    aString += " and Welcome!"
    println(aString)

    val p = aString.indexOf("W")
    val sub = aString.substring(p)
    println(sub)

    val string2 = aString.toUpperCase()
    val match = aString.equals(string2, ignoreCase = true)
    println("Do they match? $match")

    // StringBuilder
    val builder = StringBuilder("To be or not to be\n")
            .append("that is the question\n")
            .append("Whether 'tis nobler in the mind\n")
            .append("to suffer the slings and arrows")

    val result = builder.toString()
    println(result)

    // 文字列テンプレートの例
    val ar1: Array<String> = arrayOf("abc", "def", "ghi")
    val name = "田中"
    val age = 32;
    println("Name: $name, Age: $age") //「$変数名」で文字列に埋め込める

    // どこまでが変数名か分からないケースは{}で囲む
    println("${name}さんの年齢は${age}歳です")
    // 変数だけでなく、配列やプロパティも表示可能。その場合は必ず{}で囲む
    println("配列の長さ: ${ar1.size}, 配列の値: ${ar1[2]}")


    // 以下は複数行リテラル文字列（raw string）の例（あえてインデントを外している）
    val sql = """
SELECT *
FROM MyTable
WHERE cond > 1
"""
    println(sql)

    // 文字列リテラルもインデントする例
    val sql2 = """
    |SELECT *
    |  FROM MyTable
    |    WHERE cond > 1
    """.trimMargin()
    println(sql2)

    // 「|」の代わりに「#」を使った例
    val sql3 = """
    #SELECT *
    #  FROM MyTable
    #    WHERE cond > 1
    """.trimMargin("#")
    println(sql3)

    println()
    println("""${if (true) "$" else "\\" }""")
}
