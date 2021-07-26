package collection

import kotlin.reflect.full.declaredMemberProperties

data class Person(val name: String, val age: Int)

val people = listOf(
        Person("John Doe", 42),
        Person("Jane Doe", 24),
        Person("Bob Builder", 20)
)

val emptyPeople = emptyList<Person>()

// these operators are Iterable members
object Result {
    val ages = people.map { it.age }
    val firstNames = people.map { it.name }.map { it.substringBefore(" ") }
    val totalAge = people.map { it.age }.reduce { acc, i -> acc + i }
    val sumAge = people.map { it.age }.sum()
    val sumByAge = people.sumBy { it.age }
    val maxAge = people.map { it.age }.reduce(Math::max)
    val emptySafeTotalAge = emptyPeople.map { it.age }.fold(0) { acc, i -> acc + i }
    val singleSafeMaxAge = emptyPeople.map { it.age }.fold(0, Math::max)
    val sortedAges = people.map { it.age }.sorted().reversed()
    val peopleSortedByAge = people.sortedBy { it.age }
    val namesSortedByAge = people.sortedBy { it.age }.map { it.name }
}

fun main(args: Array<String>) {
    Result::class.declaredMemberProperties.forEach {
        println("${it.name}: ${it.get(Result)}")
    }
}
