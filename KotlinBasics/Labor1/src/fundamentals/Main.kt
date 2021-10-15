package fundamentals

fun feladat1(){
    println("Hello world");
    //val name : String = "Tasnadi Norbi"
    //name = "Pity"
    //var age: Int = 24
    //var age1 = 25

    val num1 = 2
    val num2 = 3
    println("$num1 + $num2 = ${num1+num2}") //kiiratas egyszeru formaban

}

fun feladat2(){
    /** Exercise 2 - List **/
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")

    /**---------------------a) alpont-------------------------**/
    print("a) alpont: ")
    daysOfWeek.forEach{print("$it ")}
    println()

    for(item in daysOfWeek){
        print("$item ")
    }
    println("\n")

    /**------b) alpont------------------------------------------------**/
    print("b) alpont: ")
    daysOfWeek.filter { it.startsWith("T") }.forEach{print("$it ")}
    println("\n")

    /**--------------c) alpont----------------------------------------**/
    print("c) alpont: ")
    daysOfWeek.filter { it.contains('e') }.forEach{print("$it ")}
    println("\n")

    /**------------------d) alpont-----------------------------------------------------**/
    print("d) alpont: ")
    daysOfWeek.filter{it.length==6}.forEach{print("$it ")}
    println("\n")
}

fun feladat3(){
    /**For masik hasznalata(range modszer)**/
    for(i in 2..100){
        //2-tol 500-ig
        if(isPrime(i)){
            println("$i is prime")
        }
        else{
            println("$i is not prime")
        }
    }
}

fun feladat5(){
    val list=ArrayList<Int>()
    for(i in 1..100){
        list.add(i)
    }

    printEven(list)
    println("\n")
}
fun printEven(list : List<Int>)= list.filter{it % 2 == 0}.forEach{print("$it ")}

fun isPrime(number : Int): Boolean{
    if (number == 0 || number == 1) {
        return false
    }

    var i = 2
    while (i <= number / 2) {
        if (number % i == 0) {
            return false
        }
        ++i
    }

    return true
}


fun feladat6(){
    /**----------a) alpont------------------**/
    print("a) alpont: ")
    val listOfNumbers = listOf(1,2,3,4,5)
    listOfNumbers.map { it*2 }.forEach{print("$it ")}
    println("\n")

    /**---------b) alpont--------------------**/
    print("b) alpont: ")
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    daysOfWeek.map { it.toUpperCase() }.forEach{print("$it ")}
    println("\n")

    /**------c) alpont---------------------------------**/
    println("c) alpont: ")
    daysOfWeek.map { it.capitalize() }.forEach{println("$it---->${it.decapitalize()[0]}")}
    println("\n")

    /**------------------d) alpont-----------------------**/
    println("d) alpont: ")
    daysOfWeek.map{it.capitalize()}.forEach{println("$it : ${it.length}")}
    println("\n")

    /**-------------------e) alpont-------------------------**/
    print("e) alpont: ")
    var osszeg = 0.00
    daysOfWeek.map{it.length}.forEach{osszeg+=it}
    println("The average length of days (in number of characters) is : ${osszeg/daysOfWeek.size}")
    println("\n")
}

fun feladat7(){
    /**-------------a) alpont--------------------**/
    print("a) alpont: ")
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    val mutableList = daysOfWeek.toMutableList()
    for(elem in daysOfWeek){
        if(elem.contains('n')){
            mutableList.remove(elem)
        }
    }

    mutableList.forEach{print("$it ")}
    println("\n")

    /**-------------b) alpont--------------------**/
    println("b) alpont: ")
    val listWithIndex = mutableList.withIndex()
    listWithIndex.forEach{println("Item at ${it.index} is ${it.value}")}
    println("\n")

    /**-------------c) alpont---------------------**/
    print("c) alpont: ")
    mutableList.sort()
    mutableList.forEach{print("$it ")}
    println("\n")
}

fun feladat8(){
    /**-----------a) alpont-----------------------**/
    println("a) alpont: ")
    val tomb = ArrayList<Int>()
    var segedValtozo: Int
    for(i in 1..10){
        segedValtozo = IntRange(0,100).random()
        tomb.add(segedValtozo)
    }

    tomb.forEach{println("$it")}
    println("\n")
    /**---------------b) alpont--------------------------**/
    println("b) alpont: ")
    print("Sorted Array in Ascending Order: ")
    tomb.sort()
    tomb.forEach{print("$it ")}
    println("\n")

    /**------------------c) alpont--------------------------**/

    /*megoldas1:

    if(tomb.filter { it % 2 == 0 }.isNotEmpty()){
        println("The array contains even numbers!")
    }
    else{
        println("The array doesn't contain even numbers!")
    }
    */

    //megoldas2:
    print("c) alpont: ")

    if(tomb.any { it%2==0 }){
        println("The array contains atleast an even number!")
    }
    else{
        println("The array doesn't contain even numbers!")
    }

    /**-------------d) alpont---------------------------**/
    /*megoldas1
    if(tomb.filter { it % 2 !=0 }.isEmpty()){
        println("All numbers in array are even!")
    }
    else{
        println("The array contains atleast an odd number!")
    }
    */

    //megoldas2
    print("d) alpont: ")

    if(tomb.all { it%2==0 }){
        println("All numbers in array are even!")
    }
    else{
        println("The array contains atleast an odd number!")
    }

    /**----------e) alpont--------------------------**/
    println("e) alpont: ")
    segedValtozo = 0
    tomb.forEach{segedValtozo+=it}
    print("The average of generated numbers is : ")
    if(segedValtozo !is Int){
        println("${(segedValtozo/tomb.size)}")
    }
    else{
        println("${(segedValtozo.toDouble()/tomb.size)}")
    }

    /*
    megoldas2:

    println("The average of generated numbers is : ${tomb.average()}")
    */
}

fun main(){
    println("------------------Feladat1----------------------------")
    feladat1()
    println("------------------Feladat2----------------------------")
    feladat2()
    println("------------------Feladat3----------------------------")
    feladat3()
    println("------------------Feladat5----------------------------")
    feladat5()
    println("------------------Feladat6----------------------------")
    feladat6()
    println("------------------Feladat7----------------------------")
    feladat7()
    println("------------------Feladat8----------------------------")
    feladat8()


}