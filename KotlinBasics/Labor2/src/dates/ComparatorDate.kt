package dates

class ComparatorDate:Comparator<Date> {
    override fun compare(o1: Date?, o2: Date?): Int {
        if(o1 != null && o2 != null){
            if(o1.year > o2.year){
                return 1
            }

            if(o1.year == o2.year){
                //honapok osszehasonlitasa
                if(o1.month > o2.month){
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