package com.jimmy.avowsstore.core.util

import android.text.format.DateUtils
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.capitalizeWords(): String {
    return this.lowercase().split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
}

fun String.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))
}


fun LocalDateTime.toServerDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format(formatter)
}

fun LocalDateTime.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.getDefault())
    return format(formatter)
}
fun LocalDateTime.toFormattedDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())
    return format(formatter)
}
fun LocalDateTime.toFormattedTime(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    return format(formatter)
}
fun LocalDateTime.toFormattedTimeReadable(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    return format(formatter)
}
fun LocalDate.toFormattedDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())
    return format(formatter)
}
fun LocalDate.toFormattedDate(pattern: String): String {
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    return format(formatter)
}
fun LocalTime.toFormattedTime(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    return format(formatter)
}

fun Double.toFormattedCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("en", "US"))
    return formatter.format(this)
}

fun Double.toFormattedPoint(): String {
    val formatter = DecimalFormat("#") // No decimal places if not needed
    return formatter.format(this)
}

fun Int.toSimpleReadableCount(): String {
    return when {
        this > 9999 -> "10K+"
        this > 999 -> "${(this/1000)}K+"
        else -> this.toString()
    }
}

private fun Long.getTimeElapsedText(): String {
    val now = System.currentTimeMillis()
    val time = this

    return DateUtils.getRelativeTimeSpanString(
        time, now, 0L, DateUtils.FORMAT_ABBREV_TIME
    ).toString()
}