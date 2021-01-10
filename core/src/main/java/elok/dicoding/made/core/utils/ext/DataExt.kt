package elok.dicoding.made.core.utils.ext

import java.text.SimpleDateFormat
import java.util.*

fun String?.changeDateFormat(sourceFormat: String?, newFormat: String?): String {
    return if (this.isNullOrBlank()) {
        this.toString()
    } else {
        val sourceFormatter  = SimpleDateFormat(sourceFormat, Locale.getDefault())
        val sourceDate = sourceFormatter.parse(this) ?: Date()

        val newFormatter  = SimpleDateFormat(newFormat, Locale.getDefault())
        newFormatter.format(sourceDate)
    }
}