package dates

import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.reflect.jvm.internal.impl.types.DynamicType

fun Date.isLeapYear(): Boolean {
    val year = this.year.toInt()

    if (year % 100 == 0) {
        return year % 400 == 0
    }

    if (year % 4 == 0) {
        return true
    }

    return false
}

fun Date.isADate(): Boolean {
    if (this.year.matches(Regex("[0-9]{1,4}"))) {
        var honap: Any?
        if (this.month.matches(Regex("^(0[1-9]|1[0-2])$"))) {
            honap = this.month.format(DateTimeFormatter.ofPattern("MM")).toInt() - 1
        } else {
            honap = this.month
        }
        when (honap) {
            Months.January.name, Months.January.ordinal, Months.March.name, Months.March.ordinal, Months.May.name, Months.May.ordinal, Months.July.name, Months.July.ordinal,
            Months.August.name, Months.August.ordinal, Months.October.name, Months.October.ordinal, Months.December.name, Months.December.ordinal ->
                if (this.day.matches(Regex("^(0[1-9]|[1,2][0-9]|3[0,1])$"))) return true

            Months.February.name, Months.February.ordinal -> if (!this.isLeapYear()) {
                if (this.day.matches(Regex("^(0[1-9]|1[0-9]|2[0-8])$"))) return true
            } else {
                if (this.day.matches(Regex("^(0[1-9]|1[0-9]|2[0-9])$"))) return true
            }

            Months.April.name, Months.April.ordinal, Months.June.name, Months.June.ordinal,
            Months.September.name, Months.September.ordinal, Months.November.name, Months.November.ordinal
            -> if (this.day.matches(Regex("^(0[1-9]|[1,2][0-9]|30)$"))) return true
        }
    }

    return false
}

fun generateDateList(): ArrayList<Date> {
    val dateList = ArrayList<Date>()
    var db = 0
    val random = Random
    var month: String?
    var day: String?
    while (db != 10) {

        val year = random.nextInt(0,99999)
        val monthInt = random.nextInt(1,20)
        val dayInt = random.nextInt(1,40)

        if(monthInt in 1..9){
            month = "0$monthInt"
        }
        else{
            month = "$monthInt"
        }

        if(dayInt in 1..9){
            day = "0$dayInt"
        }
        else{
            day = "$dayInt"
        }

        val date = Date(year.toString(), month, day)

        if (date.isADate()) {
            dateList.add(date)
            db++
        } else {
            println("${date.year}-${date.month}-${date.day} is not a valid date")
        }
    }

    return dateList
}

fun main() {
    val datum = Date()
    println("Current date is: ${datum.year}-${datum.month}-${datum.day}")
    if (datum.isLeapYear()) {
        println("The year of the date is a leap year")
    } else {
        println("The year of the date is not a leap year")
    }

    if (datum.isADate()) {
        println("${datum.year}-${datum.month}-${datum.day} is a valid date")
    } else {
        println("${datum.year}-${datum.month}-${datum.day} is not a valid date")
    }
    println()
    val dateList = generateDateList()

    println("\nThe received date list:")
    dateList.forEach{println("${it.year}-${it.month}-${it.day}")}

    println("\nThe date list natural order(Comparable):")
    dateList.sort()
    dateList.forEach{println("${it.year}-${it.month}-${it.day}")}

    println("\nThe natural sorted date list reversed:")
    dateList.reverse()
    dateList.forEach{println("${it.year}-${it.month}-${it.day}")}

    Collections.sort(dateList,ComparatorDate())
    println("\nThe date list custom order(Comparator):")
    dateList.forEach{println("${it.year}-${it.month}-${it.day}")}
}