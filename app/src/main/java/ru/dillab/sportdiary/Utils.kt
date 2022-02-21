package ru.dillab.sportdiary

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun Long?.formatLongToDateString(): String? {
    return if (this == null) {
        null
    } else {
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH)
        formatter.format(this)
    }
}

// fun isDateValid(date: String) : Boolean {
//     return try {
//         val formattedDate = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).parse(date)
//         !formattedDate.before(Date())
//     } catch(ignored: java.text.ParseException) {
//         false
//     }
// }

fun String.formatDateStringToLong(): Long {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH)
    return formatter.parse(this).time
}

// TODO Implement change of id in 3 a.m. (now its just plain date)

// e.g. 1644958129000L to 220215
fun Long.generateIdFromLong(): Int {
    return SimpleDateFormat("yyMMdd", Locale.US).format(this).toInt()
}

// e.g. "15.02.2022 23:48:49" to 220215
fun String.generateIdFromString(): Int {
    return (this.substring(8, 10) + this.substring(3, 5) + this.substring(0, 2)).toInt()
}
