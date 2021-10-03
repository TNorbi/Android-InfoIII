package extensions

//Extension Functions:

//igy megirok a Stringnek egy kiterjesztett fuggvenyt, kiegeszitek egy uj fuggvennyel!
fun String.monogram() = split(" ").map { it[0] }.joinToString ("")

fun List<String>.joinBySeparator(separator: Char) = map { it }.joinToString("$separator")

fun List<String>.longestString() = maxBy { it.length }

fun main(){
    println("------------Problem 2----------------------")
    print("a) alpont: ")
    val name = "John Smith"
    println("$name -> ${name.monogram()}")
    /**-------------------------------------------------**/
    print("b) alpont: List with separator: ")
    val list = listOf("apple","pear","melon");
    println(list.joinBySeparator('#'))

    /**-------------------------------------------------**/
    print("c) alpont: Longest string in the list is: ")
    val list2 = listOf("apple", "pear", "strawberry", "melon")
    println(list2.longestString())
}