package dates
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Date(
    val year: String= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy")),
    val month: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM")),
    val day: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))
)
