package extensions

//Extension Functions:

//igy megirok a Stringnek egy kiterjesztett fuggvenyt, kiegeszitek egy uj fuggvennyel!
fun String.monogram() = split(" ").map { it[0] }.joinToString ("")

fun List<String>.longestString() = forEach{ }

fun main(){
    val name = "John Smith"
    println("$name -> ${name.monogram()}")
}