package dates
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Date(
    val year: String= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")),
    val month: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM")),
    val day: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))
):Comparable<Date>,Comparator<Date> {
    override fun compareTo(other: Date): Int {
        if(this.year > other.year){
            return 1
        }

        return -1
    }

    override fun compare(o1: Date?, o2: Date?): Int {
        if(o1 != null && o2 != null){
            if(o1.year > o2.year){
                return 1
            }

            if(o1.year == o2.year){
                //honapok osszehasonlitasa
                if(this.month > o2.month){
                    return 1
                }

                if(o1.month == o2.month){
                    //napok osszehasonlitasa
                    if(o1.day > o2.day){
                        return 1
                    }

                    if(o1.day == o2.day){
                        return 0
                    }
                }
            }
            return -1
        }

        return 0
    }
}
