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
    daysOfWeek.forEach{print("$it ")}
    println()

    for(item in daysOfWeek){
        print("$item ")
    }
    println()

    /**------b) alpont------------------------------------------------**/
    daysOfWeek.filter { it.startsWith("T") }.forEach{print("$it ")}
    println()

    /**--------------c) alpont----------------------------------------**/
    daysOfWeek.filter { it.contains('e') }.forEach{print("$it ")}
    println()

    /**------------------d) alpont-----------------------------------------------------**/
    daysOfWeek.filter{it.length==6}.forEach{print("$it ")}
    println()
}

fun feladat3(){
    /**For masik hasznalata(range modszer)**/
    for(i in 2..500){
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
    val listOfNumbers = listOf(1,2,3,4,5)
    listOfNumbers.map { it*2 }.forEach{print("$it ")}
    println()

    /**---------b) alpont--------------------**/
    val daysOfWeek = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    daysOfWeek.map { it.toUpperCase() }.forEach{print("$it ")}
    println()

    /**------c) alpont---------------------------------**/
    daysOfWeek.map { it.capitalize() }.forEach{println("$it---->${it.decapitalize()[0]}")}
    println()

    /**------------------d) alpont-----------------------**/
    daysOfWeek.map{it.capitalize()}.forEach{println("$it : ${it.length}")}
    println()

    /**-------------------e) alpont-------------------------**/
    var osszeg = 0.00
    daysOfWeek.map{it.length}.forEach{osszeg+=it}
    println("The average length of days (in number of characters) is : ${osszeg/daysOfWeek.size}")
}

fun feladat7(){
    var daysOfWeek = ("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday").MutableList
    for(elem in daysOfWeek){
        if(elem.contains('n')){
            daysOfWeek.removeAt()
        }
    }

    daysOfWeek.forEach{ print("$it ")}

    println()

}

fun main(){
    /*feladat1()
    println()
    feladat2()
    println()
    feladat3()
    println()
    feladat5()
    println()
    feladat6()
    println()*/
    feladat7()


}