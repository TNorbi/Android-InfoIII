package dates

import java.time.format.DateTimeFormatter
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

            Months.February.name,Months.February.ordinal -> if (!this.isLeapYear()) {
                if (this.day.matches(Regex("^(0[1-9]|1[0-9]|2[0-8])$"))) return true
            } else {
                if (this.day.matches(Regex("^(0[1-9]|1[0-9]|2[0-9])$"))) return true
            }

            Months.April.name,Months.April.ordinal, Months.June.name,Months.June.ordinal,
            Months.September.name,Months.September.ordinal, Months.November.name,Months.November.ordinal
            -> if (this.day.matches(Regex("^(0[1-9]|[1,2][0-9]|30)$"))) return true
        }
    }

    return false
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
}