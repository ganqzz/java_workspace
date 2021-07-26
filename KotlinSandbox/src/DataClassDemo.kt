fun main(args: Array<String>) {

    // データクラス
    data class Person(var firstName: String = "Nanashi", var lastName: String = "Gonbee")

    // データクラスを定義
    val person1 = Person()
    // データクラスには自動でtoStringメソッドが定義される
    println("person1: $person1")

    // データクラスはインスタンスをコピーするcopyメソッドを持つ
    val person2 = person1.copy()
    println("person2: $person2")
    println("id: ${person1 === person2}")
    println("equals: ${person1 == person2}")
    println("person1.hashCode: ${person1.hashCode()}")
    println("person2.hashCode: ${person2.hashCode()}")
    person2.firstName = "Hoge"
    person2.lastName = "Fuga"
    println("person2: $person2")
    println("equals: ${person1 == person2}")
    println("person2.hashCode: ${person2.hashCode()}")

    // データクラスはN番目に定義されたプロパティを返すcomponentNメソッドを持つ
    println(person2.component1()) // firstName
    println(person2.component2()) // lastName

    // destructuring
    listOf(person1, person2)
            .forEach { (firstName, lastName) -> println("$firstName, $lastName") }
}
